package com.example.security1.config;

import com.example.security1.config.oauth.Oauth2UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity // spring security filter가 spring filterChain에 등록됨
public class SecurityConfig {

    @Autowired
    private Oauth2UserSevice oauth2UserSevice;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(CsrfConfigurer::disable)
            .authorizeHttpRequests(
                config -> config
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/user/**").hasAnyRole("ADMIN","USER")
                    .anyRequest().permitAll()
            )
            .formLogin(
                    config -> config
                            .loginPage("/login")
                            /* loginProcessingUrl
                                - /login 주소가 호출되면 security에서 낚아채서 대신 로그인 진행
                                -> controller에서 따로 login 처리를 안만들어 됨
                             */
                            .loginProcessingUrl("/login")
                            /* defaultSuccessUrl
                                - loginPage로 요청 하면 defaultSuccessUrl로 페이지 이동 시키고
                                특정 page에서 login하면 그 특정 page의 Url로 날라감
                             */
                            .defaultSuccessUrl("/")
                            .failureUrl("/login?success=100")
                            .usernameParameter("uid")
                            .passwordParameter("pass")
            )
            .oauth2Login(
                    config -> config
                           .loginPage("/login")
                    /* 여기 까지 구글 로그인이고 그 이후 후처리가 있어야 제대로 로그인이 됨
                        1. 코드 받기 - 코드를 통해서 구글 사용자 인증 할 수 있음
                        2. 엑세스 토큰 - 토큰을 받으면 사용자 접근에 대한 권한 생김
                        3. 사용자 프로필 정보를 가져옴
                        4-1. 그 정보를 토대로 회원가입을 자동으로 진행
                        4-2. 추가적인 구성이 필요하게 되면 추가 해줘야함
                            - 구글 정보에

                        ※ 구글 로그인을 하게 되면 엑세스 토큰 + 사용자 프로필 정보를 같이 가져옴
                    */
                            .userInfoEndpoint(
                                    userinfo -> userinfo
                                            .userAuthoritiesMapper(grantedAuthoritiesMapper())
                            )
//                            .userService(oauth2UserSevice)
            );

        return http.build();
    }

    // https://docs.spring.io/spring-security/reference/5.8/migration/servlet/oauth2.html
    private GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            authorities.forEach((authority) -> {
                GrantedAuthority mappedAuthority;

                if (authority instanceof OidcUserAuthority) {
                    OidcUserAuthority userAuthority = (OidcUserAuthority) authority;
                    mappedAuthority = new OidcUserAuthority(
                            "ROLE_USER", userAuthority.getIdToken(), userAuthority.getUserInfo());
                } else if (authority instanceof OAuth2UserAuthority) {
                    OAuth2UserAuthority userAuthority = (OAuth2UserAuthority) authority;
                    mappedAuthority = new OAuth2UserAuthority(
                            "ROLE_USER", userAuthority.getAttributes());
                } else {
                    mappedAuthority = authority;
                }

                mappedAuthorities.add(mappedAuthority);
            });

            return mappedAuthorities;
        };
    }

    @Bean
    public BCryptPasswordEncoder encryptPass(){
        return new BCryptPasswordEncoder();
    }
}
