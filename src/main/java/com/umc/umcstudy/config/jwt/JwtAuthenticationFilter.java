package com.umc.umcstudy.config.jwt;

import com.umc.umcstudy.config.properties.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;

  // HttpServlet에서 받아온 요청 객체에서 순수 토큰 추출
  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {

    String token = resolveToken(request);

    if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
      Authentication authentication = jwtTokenProvider.getAuthentication(token);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    // 다음 필터, 컨트롤러 등에 요청 전달
    filterChain.doFilter(request, response);
  }

  // 순수 토큰 반환
  private String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(Constants.AUTH_HEADER);
    if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constants.TOKEN_PREFIX)) {
      return bearerToken.substring(Constants.TOKEN_PREFIX.length());
    }
    return null;
  }
}
