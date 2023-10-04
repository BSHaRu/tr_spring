package kr.ch08.entity.board;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
// exclude안해주면 article이 여기서도 정의되고 
// ArticleEntity에서 toString이 정의되서 무한 참조가 됨
// -> stackoverflow 발생
@ToString(exclude = "article")
@Builder
@Entity
@Table(name="BoardComment")
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cno;
	private String content;
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy.MM.dd HH:mm")
	private LocalDateTime rdate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "writer")
	private UserEntity user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bno")
	private ArticleEntity article;
}
