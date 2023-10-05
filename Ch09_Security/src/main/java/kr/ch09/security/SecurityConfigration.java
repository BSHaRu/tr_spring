package kr.ch09.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/* Spring Security
 *  - 인증과 인가(권한) 관련 처리와 보안 관련 처리
 *  - Servlet Filter 기반으로 동작, 다양한 기능들을 Filter 제공
 * 	- 3.2부터는 XML로 설정하지 않고 자바 config 설정으로 간단하게 설정할 수 있도록 지원
 *  
 *  접근 제한
 *   - authenticated : 로그인 여부 확인 (true : 인증됨 | false : 로그인x or 인증x)
 *   - permitAll : 모든 접근에 대해 허용
 *   - hasRole : 특정 역할(Role)에 대해서만 허용
 *   			-> 반드시 "ROLE_" 접두어를 붙여야 사용 가능
 *   - hasAuthority : 특정 권한(Permission)에 대해서만 허용
 *   - hasIpAddress : 특정 IP 접근 제어
 *   - hasAnyRole(Role1, Role2...) : 여러 개 지정 가능 
 *   - anonymous : 익명 사용자 허용
 *   - denyAll : 모든 접근 거부
 *   
 *   ※ hasRole vs hasAuthority
 *    - hasRole : 권한(Permission)의 집합체
 *    - hasAuthority : READ, WRITE, DELETE 등이 하나의 권한을 의미
 */

@Configuration
public class SecurityConfigration {

	@Autowired
	private SecurityUserSerivce service;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
	
		// 인가 설정(권한부여)
		/* 기존거는 duplicate 되었고, 그래서 람다식을 통해서 사용하면 정상작동 됨
			-> Spring Security 5버전 이상부터는 주석 처리 된 방법으로 안씀
			=> 지금 6.1.4버전인데 기존 주석 방식은 4버전까지 쓰던 방식이라는 소리임
			http.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
			http.authorizeHttpRequests().requestMatchers("/user/**").hasAnyRole("USER","MANAGER","ADMIN");
			http.authorizeHttpRequests().requestMatchers("/").permitAll();
		*/
		
		/*  위에서부터 아래로 실행되기 때문에 서순 엄청 중요!!
	 		-> 좁은 범위를 먼저, 넓은 범위가 아래로 지정하는게 보통 서순임 
		 	/ : root 경로에 대한 것만 처리
		 	/** : 모든 페이지
		 */
		http.authorizeHttpRequests(
				authorizeHttpRequests ->
				authorizeHttpRequests
					.requestMatchers("/admin/**").hasAuthority("ADMIN")
					.requestMatchers("/manager/**").hasAnyAuthority("MANAGER","ADMIN")
					.requestMatchers("/").permitAll()
					.requestMatchers("/**").authenticated()
		);
		
		/* CSRF(Cross-Site Request Forgery) 공격을 비활성화
	 		-> 사용자가 악의적인 요청을 보내는 것을 방지 하는 걸 비활성화함
	 		=> 보안상 안 좋은거지만, 비활성화 하는 이유는
	 			1. 테스트 환경 - CSRF 보호를 비활성화하면 테스트 케이스 작성이 더 간단
	 			2. 레거시 코드 -  CSRF 보호를 구현하고 통합하는 것이 복잡
	 			3. 개발 및 디버깅 - CSRF 보호를 비활성화하여 문제를 파악하고 해결
	 			-> CSRF 토큰을 처리가 너무 귀찮아서 이럴 땐 비활성화 하고 코드 작업하는게 편함
	 			but, 일반적으로 활성화 해두고, 예외적일 때만 비활성화 하는거임
		*/
//		http.csrf().disable();
		http.csrf(CsrfConfigurer::disable);
		
		// form 기반으로 로그인 설정
		/*
		http.formLogin()
			.loginPage("/user/login")
			.defaultSuccessUrl("/user/loginSucess")
			.failureUrl("/user/login?success=100")
			.usernameParameter("uid")
			.passwordParameter("pass");
		*/
		http.formLogin(
				login -> login
				.loginPage("/user/login")
				.defaultSuccessUrl("/user/success")
				.failureUrl("/user/login?success=100")
				.usernameParameter("uid")
				.passwordParameter("pass")
		);
		/*
		 * http.formLogin(withDefaults());
		 * -> 이렇게 하면 기본 값들을 form 데이터에 맞게 처리 해줌
		 * but 로그인 성공 여부에 대한 처리가 안됨
		 */
		
		// 로그아웃 설정
		/*
		http.logout()
			.invalidateHttpSession(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
			.logoutSuccessUrl("/user/login?success=200");
		*/
		
		/*
		 * logoutRequestMatcher 
		 * 	- 로그 아웃 경로 지정
		 * 		- AntPathRequestMatcher : 로그아웃 요청을 특정 URL에 매칭
		 * invalidateHttpSession(true)
		 *  - 로그 아웃 성공시 세션 제거 후 성공 페이지로 redirect 함
		 */
		http.logout(
				logout -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
				.logoutSuccessUrl("/user/login?success=200")
				.invalidateHttpSession(true)
		);
		
		/* 사용자 인증처리 컴포넌트 등록
		 *  - DB에서 사용자 정보 검색하고 인증하기 위해 UserDetailsService 필요
		 *  - UserDetailsService에서 id, email등으로 사용자 찾아서
		 *  권한 확인 후, security에 필요한 정보를 반환 
		 */
		http.userDetailsService(service);
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// BCrypt 알고리즘(소수) 이용한 암호화 방식 
		// 이게 security 오지게 강력한 비번 만드는거래
		return new BCryptPasswordEncoder();
	}
}
