package co.kr.ch12.oauth2;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;
@NoArgsConstructor
@AllArgsConstructor
public class KakaoInfo {

    private Map<String, Object> attributes;

    public String getId(){
        return attributes.get("id").toString();
    }

    public String getProvider(){
        return "kakao";
    }

    public String getEmail(){
        return ((Map<?,?>) attributes.get("kakao_account")).get("email").toString();
    }

    public String getNickName(){
        return ((Map<?,?>) attributes.get("properties")).get("nickname").toString();
    }
}
