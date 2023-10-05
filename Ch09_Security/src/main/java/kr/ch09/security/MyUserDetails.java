package kr.ch09.security;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/*
 * SecurityUserService에서 DB 체크를 하면 여기서 인증을 해줌
 *  -> 사용자 인증 및 권한 부여를 위한 정보를 나타내는 객체
 *  => Spring Security가 이 객체를 사용하기 때문에
 *  db에 대한 필드값과 getter가 필요함
 *  
 *  UserDeatails vs Entity vs DTO
 *   - UserDeatails : 사용자 인증과 권한 관리를 위한 객체
 *   		-> 인증과 권한을 부여하기 때문에 불변 객체로 정의하는 것이 일반적
 *   - Entity : DB와 상호작용하기 위한 객체
 *   		-> 일관성 유지하기 위해서 불변 객체로 정의하는 것이 일반적
 *   - DTO : DB 데이터 전송하는 객체
 *   		-> 데이터를 전송하고 수정해줘야 해서 불변 객체로 만들면 불편함
 */

@Getter
@Builder
@ToString
public class MyUserDetails implements UserDetails {
	private static final long serialVersionUID = -7577960023531610089L;
	
	private String uid;
	private String pass;
	private String name;
	private int age;
	private String hp;
	private String role;
	private LocalDateTime regDate;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 계정이 갖는 권한 목록
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

	@Override
	public String getPassword() {
		// 계정이 갖는 비밀번호
		return pass;
	}

	@Override
	public String getUsername() {
		// 계정이 갖는 아이디
		return uid;
	}

	@Override
	public boolean isAccountNonExpired() {
		/* 계정 만료 여부
		 * 	- true : 만료 안됨
		 * 	- false : 만료 (만료 되면 해당 계정으로 로그인 안됨) 
		 */
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		/* 계정 잠김 여부 (휴면 계정을 말하는듯)
		 *  - true : 잠김 안됨
		 *  - false : 잠김
		 */
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		/* 계정 비밀번호 만료 여부
		 *  - true : 만료 안됨
		 *  - false : 잠김
		 */
		return true;
	}

	@Override
	public boolean isEnabled() {
		/* 계정 활성화 여부 - isAccountNonLocked랑 비슷한데 다르데
		 *  - true : 활성화
		 *  - false : 비활성화
		 */
		return true;
	}
}
