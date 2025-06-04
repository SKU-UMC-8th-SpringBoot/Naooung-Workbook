package com.umc.umcstudy.config.jwt;

import com.umc.umcstudy.apiPayload.code.status.ErrorStatus;
import com.umc.umcstudy.apiPayload.exception.handler.MemberHandler;
import com.umc.umcstudy.config.properties.Constants;
import com.umc.umcstudy.config.properties.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;
import java.util.Collections;

// JWT 토큰을 생성 & 검증 & 인증 객체를 반환하는 역할을 수행하는 클래스
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  private final JwtProperties jwtProperties;

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
  }

  // 인증 정보를 받아 JWT 토큰 반환
  public String generateToken(Authentication authentication) {
    String email = authentication.getName();

    return Jwts.builder()
        .setSubject(email)
        .claim("role", authentication.getAuthorities().iterator().next().getAuthority())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration().getAccess()))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  // JWT 토큰이 유효한지 검증
  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(getSigningKey())
          .build()
          .parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }

  // JWT 토큰에서 인증 정보를 추춘해서 Authentication 객체로 반환
  public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();

    String email = claims.getSubject();
    String role = claims.get("role", String.class);

    User principal = new User(email, "", Collections.singleton(() -> role));
    return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
  }

  public static String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(Constants.AUTH_HEADER);
    if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constants.TOKEN_PREFIX)) {
      return bearerToken.substring(Constants.TOKEN_PREFIX.length());
    }
    return null;
  }

  // HttpServletRequest에서 토큰 값을 추출하고 getAuthentication() 호출
  public Authentication extractAuthentication(HttpServletRequest request){
    String accessToken = resolveToken(request);
    if(accessToken == null || !validateToken(accessToken)) {
      throw new MemberHandler(ErrorStatus.INVALID_TOKEN);
    }
    return getAuthentication(accessToken);
  }
}

