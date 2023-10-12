package co.kr.ch12.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
 *   			=> UserDetails에서 권한 부여 할 때 "ROLE_"을 추가해주면 됨
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
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // :: - 메서드 참조 연산자로 람다식을 간결하게 표현
        http
                // 기본 HTTP 인증방식 비활성화
                .httpBasic(HttpBasicConfigurer::disable)
                // 인가 권한 설정
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                                .requestMatchers("/manage/**").hasAnyAuthority("ADMIN", "MANAGER")
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/**").permitAll()
                )
                .formLogin(
                        config -> config.loginPage("/user/login")
                                .defaultSuccessUrl("/")
                                .usernameParameter("uid")
                                .passwordParameter("pass")
                )
                .logout(
                        config -> config.logoutUrl("/user/logout")
                                .logoutSuccessUrl("/")
                )
                // OAuth 설정
                .oauth2Login(
                        config -> config.loginPage("/user/login")
                               .defaultSuccessUrl("/")
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
