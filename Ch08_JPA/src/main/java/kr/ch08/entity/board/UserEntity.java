package kr.ch08.entity.board;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="BoardUser")
public class UserEntity {

	@Id
//	@Column(nullable = false)
	private String uid;
	private String name;
	private String hp;
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy.MM.dd HH:mm")
	private LocalDateTime regDate;
	
}
