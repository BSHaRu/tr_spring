package co.kr.ch12.oauth2;

import co.kr.ch12.entity.UserEntity;
import co.kr.ch12.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Service
public class OAuth2UserSevice extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // kakao에서 날라온 access token
        // -> 인증 코드에서 발급된 acessToken code
        String accessToken = userRequest.getAccessToken().getTokenValue();
        log.info("accessToken : {}", accessToken);
        
        // provider : 여기선 kakao를 의미 / naver, google, facebook, etc.
        String provider = userRequest.getClientRegistration().getRegistrationId();
        log.info("provider : {}", provider);
        
        // kakao 리소스를 넘어오는 user 객체
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("oAuth2User : {}", oAuth2User);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        KakaoInfo kakaoInfo = new KakaoInfo(attributes);
        log.info("kakaoInfo : {}", kakaoInfo);

        // 회원가입 처리
        // id : kakao에서 사용 하는 email
        String id = kakaoInfo.getId();
        String nickName = kakaoInfo.getNickName();
        String email = kakaoInfo.getEmail();

        Optional<UserEntity> result = userRepository.findById(id);

        UserEntity user = null;
        if(result.isEmpty()) {
            // 최초 소셜 로그인 동의 체크 후 가입
            // -> db에 kakao data가 저장됨
            user = UserEntity.builder()
                            .uid(provider+"_"+id)
                            .name(nickName)
                            .nickName(nickName)
                            .provider(provider)
                            .regDate(LocalDateTime.now())
                            .email(email)
                            .role("USER")
                            .build();

            userRepository.save(user);
        }else {
            // 회원가입 된 사용자 조회
            user = result.get();
            user.setNickName(nickName);
            userRepository.save(user);
        }

        // login시 해당 메서드 실행
        // return 값은 securit에 저장 되는 principal
        // -> userEntity를 의미함
        log.info("user : {}", user);
        return user;
    }

}
