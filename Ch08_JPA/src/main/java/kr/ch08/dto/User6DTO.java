package kr.ch08.dto;

import kr.ch08.entity.User6Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User6DTO {
	private String uid;
	private String name;
	private String birth;
	private String gender;
	private int age;
	private String addr;
	private String hp;
	
	
	// Entity 변환 메서드
	public User6Entity toEntity() {
		return User6Entity.builder()
				.uid(uid)
				.name(name)
				.birth(birth)
				.gender(gender)
				.age(age)
				.addr(addr)
				.hp(hp)
				.build();
	}
}
