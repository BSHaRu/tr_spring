package kr.ch08.dao.board;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import kr.ch08.entity.board.ArticleEntity;
import kr.ch08.entity.board.CommentEntity;
import kr.ch08.entity.board.FileEntity;
import kr.ch08.entity.board.UserEntity;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BoardRepositoryTest {

	@Autowired
	private ArticleDAO articleDAO;
	@Autowired
	private CommentDAO commentDAO;
	@Autowired
	private FileDAO fileDAO;
	@Autowired
	private UserDAO userDAO;
	
	
//	@Test
	@DisplayName("=============insertUser Test=============")
	public void insertUser() {
		UserEntity user = UserEntity.builder()
							.uid("a104")
							.name("병아리")
							.hp("010-1111-4444")
							.build();
		
		userDAO.save(user);
	}
	
//	@Test
	@DisplayName("=============insertArticle Test=============")
	public void insertArticle() {
		UserEntity user = UserEntity.builder().uid("a101").build();
		
		ArticleEntity article = ArticleEntity.builder()
								.title("test title1")
								.content("test content1")
								.user(user)
								.build();
		articleDAO.save(article);
	}
	
//	@Test
	@DisplayName("=============insertComment Test=============")
	public void insertComment() {
		UserEntity user = UserEntity.builder().uid("a104").build();
		
		ArticleEntity article = ArticleEntity.builder()
								.bno(1)
								.build();
		
		CommentEntity comment = CommentEntity.builder()
								.content("comment test")
								.user(user)
								.article(article)
								.build();
		
		commentDAO.save(comment);
	}
	
//	@Test
	@DisplayName("=============insertFile Test=============")
	public void insertFile() {
		ArticleEntity article = ArticleEntity.builder()
				.bno(2)
				.build();
		FileEntity file = FileEntity.builder()
							.oName("test1.txt")
							.sName("UUID.txt")
							.article(article)
							.build();
		fileDAO.save(file);

	}
	
//	@Test
	/* @Transaction 
		- 양방향으로 처리 되는 entity관계에서 다수의 SELECT를 실행해야 하는데
		 한번씩 실행 후 종료되는걸 막아 줌(안해주면 no Session 에러 발생)
	 */
	@Transactional
	@DisplayName("=============selectArticles Test=============")
	public void selectArticles() {
		
		List<ArticleEntity> articles = articleDAO.findAll();
		log.info("selectArticles articles : " + articles);
		for(ArticleEntity article : articles) {
			log.info("selectArticles for :  "+article);
		}
	}
	
	@Test
	@Transactional
	@DisplayName("=============selectArticle Test=============")
	public void selectArticle() {
		Optional<ArticleEntity> result = articleDAO.findById(2);
		ArticleEntity article = result.orElseThrow();
		log.info("selectArticle article : " + article);
	}
	
}
