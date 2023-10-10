package kr.co.sboard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.sboard.entity.UserEntity;
import kr.co.sboard.repository.UserRepository;

/*
 * 여기서 DB 체크하고 MyUserDetails에서 인증을 한다고 함 
 * -> UserDetailsService가 DB로 검색해서 사용자 정보를 조회 하고
 * 그 정보의 데이터를 반환하는 메서드를 정의
 * -> 반환된 데이터를 가지고 UserDetails에서 인증 및 권한 부여함
 * => UserDetailsService : DB에서 데이터 가져옴
 * 	UserDetails : 그 데이터로 통해 인증 및 권한 부여함
 */
@Service
public class SecurityUserSerivce implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		/* repository에서 uid랑 pass 2개를 받는데 id만 쓰는 이유  
			- username으로 사용자 정보를 데이터베이스에서 검색하고 반환
		 	-> 패스워드에 대한 검사는 이후 컴포넌트(AuthenticationProvider)에서 
		 		검사하고 검증 실패하면 아래 던진 exception 발생하고 성공하면 정상처리 됨
		 	=> username id값을 가지고, 사용자가 입력 할 떄까지 기다렸다가 입력하면 
		 	db에 있는 id, pass, 권한 등을 비교를 AuthenticationProvider에서 하는데
		 	검증 실패 하면 무조건 exception 발생시킴
	 		
	 		get()을 한 이유는 Optional을 통해 null 체크 때문에 받는거임
	 		-> 그냥 get()으로 바로 받는거 보다
	 		 	ifPresent()나, orElse()로 체크해서 받는게 좋음
 		 	=> get()으로 바로 받으면 null일 경우 NoSuchElementException 발생
 		 	-> 근데 기근쌤이 어쨋든 둘다 null일 경우 동일한 exception 발생한다다고함
		*/
		UserEntity user = repo.findById(username).orElse(null);
		
		// 이렇게 처리하면 Spring Security에서 해당 exception을 처리해줌
		if(user == null) {
			throw new UsernameNotFoundException("not found username : " + username);
		}
		
		/* 사용자 인증 및 권한 부여를 할 수 있는 객체를 생성하는거임
		 *  - Spring Security는 UserDetails 객체를 사용하여 
		 *  	사용자 이름, 암호, 계정이 만료되었는지 여부, 계정이 잠겼는지 여부 등을 확인
		 *  - UserDetails 객체에서 가져온 권한 정보로 Spring Security에서 권한 정보를 검사
		 *  - Spring Security에서 UserDetails 객체를 사용하여 사용자의 세션을 관리
		 *  - UserDetails 객체 기반으로 인증 및 logout 처리 
		 */
		UserDetails userDetails = MyUserDetails.builder()
									.user(user)
									.build();
		
		return userDetails;
	}

}
