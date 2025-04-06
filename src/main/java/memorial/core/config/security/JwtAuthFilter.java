package memorial.core.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService;

    /**
     * OncePerRequestFilter는 매 요청마다 한번의 필터링의 수행을 보장하는 필터이다.
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // JWT 토큰을 헤더에서 추출.
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String jwtToken = authHeader.substring(7);

            try {
                String username = jwtTokenProvider.extractUsername(jwtToken);

                // 토큰이 유효하고, SecurityContext에 인증 정보가 없는 경우
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    // 1. DB에서 사용자 정보 조회 방식
                    // UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    // 2. jwtToken 자체에서 사용자 정보 조회 방식
                    UsernamePasswordAuthenticationToken authentication = jwtTokenProvider.getAuthentication(jwtToken);

                    // 인증 정보를 SecurityContext에 저장
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                // 토큰이 유효하지 않거나 만료된 경우 예외 처리 로직 (로그 기록, 사용자에게 오류 메시지 반환 등)
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
            }
        }

        // 다음 필터로 요청 전달.
        filterChain.doFilter(request, response);

    }
}
