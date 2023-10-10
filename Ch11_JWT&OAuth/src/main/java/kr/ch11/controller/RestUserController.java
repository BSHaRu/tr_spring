package kr.ch11.controller;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.ch11.dto.UserRequestDTO;
import kr.ch11.entity.UserEntity;
import kr.ch11.jwt.JwtProvider;
import kr.ch11.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor // final로 선언된 모든 객체 주입(Autowired)해줌
@RestController
@Log4j2
public class RestUserController {

	// 이건 따로 been 등록해줘야 주입 가능 -> securityConfig에 등록함
	private final AuthenticationManager am;
	private final JwtProvider jwtProvider;
	
	// postman에서 test 할 때 context-type에 application/json값 줘야함
	@PostMapping("/login")
	public Map<String, String> login(@RequestBody UserRequestDTO dto) {
		try {
			// Security를 통해서 인증 처리
			// -> UserDetailsService의 lodaUserByUsername()이 실행됨
			UsernamePasswordAuthenticationToken at
				= new UsernamePasswordAuthenticationToken(dto.getUid(), dto.getPass());
			
			Authentication authentication = am.authenticate(at);
			MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
			
			UserEntity user = userDetails.getUser();
			
			// 토큰 발행 - 매개변수는 entity, days임
			String accessToken = jwtProvider.createToken(user, 1); // 하루
			String refreshToken = jwtProvider.createToken(user, 7); // 일주일

			// Map.of -> key - value로 put할 것이 많다면 Map.of를 써서 한꺼번에 처리 할 수 있다.
			Map<String, String> resultMap 
				= Map.of("grantType", "Bearer",
						"accessToken", accessToken,
						"refreshToken", refreshToken);

			return resultMap;
		} catch (AuthenticationException e) {
			Map<String, String> resultMap 
					= Map.of("grantType", "None",
							"message", e.getMessage());
			return resultMap;
		} catch(Exception e) {
			log.error("왜 에러 생김? " + e.getMessage());
			return null;
		}
		
	}
}
