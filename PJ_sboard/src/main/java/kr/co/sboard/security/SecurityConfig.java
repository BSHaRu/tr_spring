package kr.co.sboard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
	
		// :: - 메서드 참조 연산자로 람다식을 간결하게 표현
		// 	-> 이걸 제대로 안알려주고 우리보고 찾아봐라네 ㅋㅋㅋ;
		http
			// 인가 권한 설정
			.authorizeHttpRequests(
				authorizeHttpRequests -> authorizeHttpRequests
				.requestMatchers("/admin/**").hasAuthority("ADMIN")
				.requestMatchers("/manage/**").hasAnyAuthority("ADMIN","MANAGER")
				.requestMatchers("/").permitAll()
				.requestMatchers("/**").permitAll()				
			)
			// 토큰 방식으로 로그인 처리 방식이라 폼방식 비활성화
			.formLogin(
				config -> config.loginPage("/user/login")
								.defaultSuccessUrl("/list")
								.failureUrl("/user/login?success=100")
								.usernameParameter("uid")
								.passwordParameter("pass")
			)
			// 로그 아웃 설정
			.logout(
				config -> config.logoutUrl("/user/logout")
								.invalidateHttpSession(true)
								.logoutSuccessUrl("/user/login?success=200")
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
