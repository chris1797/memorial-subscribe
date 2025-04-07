package memorial.core.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/*
 * JWT 유틸리티 클래스
 * 역할: JWT 생성, 검증, 파싱, 클레임 추출 등의 기능을 제공
 */
@Component
public class JwtTokenProvider {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    private final long EXPIRATION_MS = 1000 * 60 * 60 * 24; // 1일

    /**
     * JWT 토큰을 생성
     * @param authentication 인증 정보
     * @return 생성된 JWT 토큰
     */
    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Claims claims = Jwts.claims()
                .subject(userDetails.getUsername()) // 토큰의 주체 (사용자의 id, 이메일 등)
                .build();

        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS)) // 1일
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    /**
     * JWT 토큰에서 클레임을 추출, 유효성 검사
     * @param token JWT 토큰
     * @return 토큰 유효성 검사 결과 (true: 유효, false: 만료 또는 변조됨)
     */
    public boolean validateToken(String token) {
        try {
            Claims claims = extractAllClaims(token);

            // 토큰이 만료되었는지 확인
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims claims = extractAllClaims(token);
        String username = claims.getSubject();

        if (username != null) {
            // 사용자 정보, 비밀번호 정보, 권한 정보
            return new UsernamePasswordAuthenticationToken(username, null, null);
        }

        return null;
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(this.secretKey.getBytes());

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
