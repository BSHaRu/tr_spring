package kr.ch07.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor	// 모든 필드값을 사용하는 생성자
@NoArgsConstructor	// 기본 생성자
@Builder			// 자기가 필요한 필드값만 사용해서 초기화 할 수 있는 생성자
public class UserDTO {
	
	private String uid;
	private String name;
	private String hp;
	private int age;
	
}
