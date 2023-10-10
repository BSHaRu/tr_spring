package kr.ch11.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kr.ch11.filter.JwtAuthenticationFiler;
import kr.ch11.jwt.JwtProvider;


@Configuration
public class SecurityConfig {

	@Autowired
	private JwtProvider jwtProvider;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
	
		// :: - 메서드 참조 연산자로 람다식을 간결하게 표현
		// 	-> 이걸 제대로 안알려주고 우리보고 찾아봐라네 ㅋㅋㅋ;
		http
			// 기본 HTTP 인증방식 비활성화
			.httpBasic(HttpBasicConfigurer::disable)
			// 인가 이전 필터 적용 -> jwt필터로 토큰 검증 이후 권한 부여가 됨
			.addFilterBefore(new JwtAuthenticationFiler(jwtProvider), UsernamePasswordAuthenticationFilter.class)
			// 인가 권한 설정
			.authorizeHttpRequests(
				authorizeHttpRequests -> authorizeHttpRequests
				.requestMatchers("/admin/**").hasAuthority("ADMIN")
				.requestMatchers("/manage/**").hasAnyAuthority("ADMIN","MANAGER")
				.requestMatchers("/").permitAll()
				.requestMatchers("/**").permitAll()				
			)
			// 토큰 방식으로 로그인 처리 방식이라 폼방식 비활성화
			.formLogin(FormLoginConfigurer::disable)
			// 토큰기반 인증 방식이기 때문에 세션을 사용안함
			// -> 실제로는 사용하지만 여기 예제에서는 세션 사용 안할꺼래
			.sessionManagement(
				config -> config.sessionCreationPolicy(
							SessionCreationPolicy.STATELESS
						)
			)
			// 사용자가 악의적은 요청(공격)을 보내는 것을 방지하는 걸 비활성화
			.csrf(CsrfConfigurer::disable);
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// BCrypt 알고리즘(소수) 이용한 암호화 방식 
		// 이게 security 오지게 강력한 비번 만드는거래
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager am(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
