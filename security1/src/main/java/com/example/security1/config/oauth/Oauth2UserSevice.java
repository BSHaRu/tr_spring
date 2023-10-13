package com.example.security1.config.oauth;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class Oauth2UserSevice extends DefaultOAuth2UserService {

    // 구글로부터 받은 userRequest 데이터에 대한 후처리 되는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("userRequest : " + userRequest);
        // registrantID로 어떤 OAuth로 로그인 했는지 확인 가능
        log.info("getClientRegistration : " + userRequest.getClientRegistration());
        log.info("getAccessToken : " + userRequest.getAccessToken().getTokenValue());

        /* 구글 로그인 버튼 클릭 -> 구글 로그인 창 뜸 -> 로그인 완료
         -> OAuth-Client라이브러리가 code를 리턴해줌
         -> code를 통해서 Access Token을 요청
         => 이것들이 담겨 있는게 userRequest

         userRequest의 정보를 가지고 구글 회원 프로필을 받아함
         -> 이 때 loadUser함수가 사용됨
         => loadUser는 구글 회원 프로필을 리턴해줌
        */
        log.info("getAttributes : " + super.loadUser(userRequest).getAttributes());

        OAuth2User user = super.loadUser(userRequest);

        // getAttributes() 정보로 회원 가입 강제로 진행
        return super.loadUser(userRequest);
    }
}
