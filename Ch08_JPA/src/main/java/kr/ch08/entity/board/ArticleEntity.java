package kr.ch08.entity.board;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name="BoardArticle")
public class ArticleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bno;
	private String title;
	private String content;
	
	//private String writer;
	/* @JoinColumn(name="writer")
	 	 - writer는 fk라서 그냥 선언해버리면 안되고 아래처럼 entity쓰고
		 join이기 때문에 어노테이션을 달아줘야함
	 	 - name값이 실제 table에 있는 칼럼명인듯
  		@ManyToOne
  		 - 방향성을 고려해서 Article에서 User를 참조해야 
	  		Article을 조회할 때 User가 동시에 참조되서 조회 됨
	  	
  		@Column(nullable = false)이건 왜 test에서 null을 못막지?
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="writer")
//	@Column(nullable = false)
	private UserEntity user;
	
	@OneToOne(fetch = FetchType.LAZY,
				mappedBy = "article")
	private FileEntity file;
	
	// @OneToMany는 List로 받아야되고 Join은 해당 entity에서 선언해줘야된다고 함
	// mappedBy : 양방향 관계에서 fk를 갖는 entity를 연결 해주는 주체(주인)를 설정
	@OneToMany(fetch = FetchType.LAZY, 
				mappedBy = "article")
	private List<CommentEntity> comments;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@CreationTimestamp
	private LocalDateTime rdate;
	
}
