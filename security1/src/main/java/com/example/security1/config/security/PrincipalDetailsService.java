package com.example.security1.config.security;

import com.example.security1.entity.User2Entity;
import com.example.security1.repository.User2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/* SecurityConfig에서 loginProcessingUrl("/login")설정을 함
    -> /login 요청이 오면 자동으로 UserDetailsService 타입으로
    IoC(@Service로 생성됨) 되어 있는 loadUserByUsername 함수가 실행
    => UserDetailsService가 DB에서 데이터 조회하고 
    UserDetails가 그 데이터를 통해 인증 및 권한을 부여함
 */
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private User2Repository repo;

    /* loadUserByUsername 매개변수의 기본값은 username임
     SecurityConfig -> usernameParameter값을 주면 그 값으로 사용 가능함
        => usernameParameter("uid"), input name="uid",
           loadUserByUsername(String uid), findByUid(uid) 동일해야됨
           다르면 동작은 하지만 매칭이 안되서 에러 발생할꺼임
     
     security session -> Authentication 객체이고 이는 UserDetails타입이 있어야함
    */
    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {

        User2Entity userEntity = repo.findByUid(uid);
        if(userEntity != null) {
            // return 할 때 userEntity를 같이 넘겨야 PrincipalDetails에서 활용하기 편함
            // -> 이렇게 리턴 되면 Authentication 내부 안에 UserDetails가 들어가게 됨
            // => 그럼 security session안에 해당 Authentication이 들어감
            // ∴ security session(내부 Authentication(내부 UserDetails)) 이렇게 됨
            return new PrincipalDetails(userEntity);
        }else{
            throw new UsernameNotFoundException("not found uid : " + uid);
        }
    }
}
