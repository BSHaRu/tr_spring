package com.example.security1.config.security;

import com.example.security1.entity.User2Entity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* security가 /login 주소 요청 오면 security에서 로그인 대신 처리 해줌
    (SecurityConfig에서 loginProcessingUrl을 만들어 놨기 떄문.)
    로그인 진행이 완료 되면 security session을 만듬(Security ContextHoleder에 key값 저장)
    Security ContextHoleder에는 Authentication 타입의 객체가 들어감
    Authentication 안에는 User 정보가 있어야 됨
        -> 이때 User 타입은 UserDetails 타입 객체이어야 함
        
    즉, Security Session에는 Authentication 객체가 들어가고
    이 Authentication안에는 UserDetails(PrincipalDetails)이 존재함
*/
@Data
public class PrincipalDetails implements UserDetails {

    // Composition
    // - 기존 클래슬를 확장하는 대신 새로은 클래스를 만들고
    // private 필드로 기존 클래스의 인스턴스를 참조하는 방법을 통해 기능 확장
    private User2Entity user2Entity;

    public PrincipalDetails(User2Entity user2Entity) {
        this.user2Entity = user2Entity;
    }


    // user 권한을 리턴 하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+ user2Entity.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user2Entity.getPass();
    }

    @Override
    public String getUsername() {
        return user2Entity.getUid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    
    // 휴먼계정 처리 할 때 false 해주는거임
    // -> 현재시간 - 로그인 시간
    // => 휴먼계정 기간 일 경우에 false 해줌
    @Override
    public boolean isEnabled() {
        return true;
    }
}
