package kr.ch07.mapper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.ch07.dao.User6Mapper;
import kr.ch07.dto.User6DTO;
import kr.ch07.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class User6MapperTest {

	@Autowired
	private User6Mapper mapper;

	//@Test
	public void insertUser() {
		User6DTO user1 = User6DTO.builder()
								.uid("p101")
								.name("강아지")
								.birth("1999-01-01")
								.gender("M")
								.age(57)
								.addr("부산")
								.hp("010-1234-5484")
								.build();
		
		mapper.insertUser(user1);
	}
	
	//@Test
	public void selectUsers() {
		List<User6DTO> users = mapper.selectUsers();
		
		for(User6DTO user : users) {
			log.info("user : " + user);
		}
		
	}
	
	//@Test
	public void updateUser() {
		User6DTO user1 = User6DTO.builder()
				.name("강아지")
				.birth("1999-01-01")
				.gender("F")
				.age(60)
				.addr("광주")
				.hp("010-0000-1234")
				.uid("p101")
				.build();

		mapper.updateUser(user1);
	}
	
	@Test
	public void deleteUser() {
		String user1 = "p101";
		mapper.deleteUser(user1);
	}
}
