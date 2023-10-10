package kr.ch11.jwt;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import kr.ch11.entity.UserEntity;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Component
public class JwtProvider {

	/*
	 * jjwt 라이브러리 0.11.5 버전 사용
	 * 0.12.x 버전은 최신버전이라 Deprecated 되서 안되는게 많음 
	 */
	
	private String issuer;
	private SecretKey secretKey;
	
	// @Value("${jwt.issuer}")
	// - application에서 설정한 jwt를 가져오는거임
	// issuer : 발급자 | secret : key 번호(https://acte.ltd/utils/randomkeygen 여기서 랜덤값 가져옴)
	public JwtProvider(@Value("${jwt.issuer}") String issuer,
					@Value("${jwt.secret}") String secret) {
		
		this.issuer = issuer;
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public String createToken(UserEntity user, int days) {
		
		// 토큰 발급일
		Date issuedDate = new Date();
		// 오늘 날짜 기준으로 days를 더하는데 시간으로 더해줌
		// -> days가 7이면 유효기간이 일주일이라는 소리임
		Date expireDate 
			= new Date(issuedDate.getTime() + Duration.ofDays(days).toMillis());
		
		
		// 클레임 생성(paylode에 저장될 정보)
		// - tokent에는 최소한의 정보만 있어야되기 때문에 id랑 role만 넣어줌
		Claims claims = Jwts.claims();
		claims.put("uid", user.getUid());
		claims.put("role", user.getRole());
		
		// 토큰 생성
		String token = Jwts.builder()
						.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
						.setIssuer(issuer)
						// Payload - Claims 설정
						.addClaims(claims)
						// Signature 설정
						.signWith(secretKey, SignatureAlgorithm.HS256)
						.setIssuedAt(issuedDate)
						.setExpiration(expireDate)
						.compact();
		
		return token;
	}
	
	// jwt 토큰을 입력으로 받아와서 토큰에 저장된 클레임 정보로
	// Security에서 인증하기 위한 Authentication객체 생성
	public Authentication getAuthentication(String token) {
		
		// 입력 받은 token을 가지고 claims정보 조회
		Claims claims = getClaims(token);
		String uid = (String) claims.get("uid");
		String role = (String) claims.get("role");
		
		// 여기서 DB를 확인하고 권한 부여가 가능
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
		
		// UserDetails에 있는 User를 import 하는거임
		User principal = new User(uid, "", authorities);
		
		// 결국 return 되는건 사용자 정보(uid, role)와
		// jwt를 가진 Authentication객체가 return 되는거임
		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}
	
	// 토큰 유효한지 안한지 체크
	public boolean validateToken(String token) {
		
		try {
			Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token);
			
			return true;
			
		}catch (SecurityException | MalformedJwtException e) {
			log.error("잘못된 JWT 서명 입니다." + e.getMessage());
		}catch (ExpiredJwtException e) {
			log.error("만료된 JWT 서명 입니다." + e.getMessage());
		}catch (UnsupportedJwtException e) {
			log.error("지원하지 않는 JWT 토큰입니다." + e.getMessage());
		}catch (IllegalArgumentException e) {
			log.error("JWT 토근이 잘못되었습니다." + e.getMessage());
		}
		
		return false;
	}
	
	// jwt 토큰을 생성하고 검증하여 인증 및 권한 부여를 관리
	public Claims getClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}
