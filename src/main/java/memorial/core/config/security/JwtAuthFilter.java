package memorial.core.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    /**
     * OncePerRequestFilter는 매 요청마다 한번의 필터링의 수행을 보장하는 필터이다.
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // JWT 토큰을 검증하는 로직
        String jwtToken = request.getHeader("Authorization");

        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(7);
            // JWT 검증 로직 추가
            // 예: JwtUtil.validateToken(jwtToken);
        }

        // 다음 필터로 요청을 전달합니다.
        filterChain.doFilter(request, response);

    }
}
