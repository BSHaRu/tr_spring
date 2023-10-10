package kr.ch11.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ch11.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Component
@Log4j2
public class JwtAuthenticationFiler extends OncePerRequestFilter {

	private final JwtProvider jwtProvider;
	
	// Header에 붙는거
	public static final String AUTH_HEADER = "Authorization";
	// 젤 앞단에 붙는 키워드
	public static final String TOKEN_PREFIX = "Bearer ";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.info("Jwt filter start");
		
		String header = request.getHeader(AUTH_HEADER);
		log.info("Jwt filter header : " + header);
		
		String token = getTokenFromHeader(header);
		log.info("Jwt filter token : " + token);
		
		// security 인증 처리
		if(token != null && jwtProvider.validateToken(token)) {
			log.info("Jwt filter token secturiry 처리 과정");
			// authentication 안에 principal(사용자 객체)가 있음
			Authentication authentication = jwtProvider.getAuthentication(token);
			
			// 이게 session에 uid 저장하는거랑 느낌 비슷한 친구래
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}

	public String getTokenFromHeader(String header) {
		if(header != null && header.startsWith(TOKEN_PREFIX)) {
			
			// Bearer이 붙은 걸 제거하기 때문에 순수한 token값만 return하게 됨
			return header.substring(TOKEN_PREFIX.length());
		}
		return null;
	}
}
