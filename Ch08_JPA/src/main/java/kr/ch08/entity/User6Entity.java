package kr.ch08.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.ch08.dto.User6DTO;
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
@Entity
@Table(name="user6")
public class User6Entity {
	/* @Id = pk
	 -> Entity와 table을 선언하면 반드시 pk값이 있어야함
	 @GeneratedValue : 기본 키를 자동 생성해주는 어노테이션
 		-@GeneratedValue(strategy = GenerationType.IDENTITY)
 			: AUTO_INCREMENT일 때 선언 해주면 됨
		-@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "칼럼명")
			: 유일한 값을 순서대로 생성
			-> AUTO_INCREMENT를 지원 안하는 DB를 사용 할 때 해주면 됨(오라클, H2 등)
			=> SEQUENCE사용 하기 위해선 @SequenceGenerator도 같이 써줘야됨(노션 참고)
	*/
	@Id
	private String uid;
	private String name;
	private String birth;
	private String gender;
	private int age;
	private String addr;
	private String hp;
	
	// DTO 변환 메서드
	public User6DTO toDTO() {
		return User6DTO.builder()
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
