package kr.ch07.mapper;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.ch07.dao.UserMapper;
import kr.ch07.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class UserMapperTest {

	@Autowired
	private UserMapper mapper;

	//@Test
	public void insertUser() {
		UserDTO user1 = UserDTO.builder()
								.uid("p101")
								.name("장보고")
								.hp("010-1234-5484")
								.age(57)
								.build();
		
		mapper.insertUser(user1);
	}
	
	public UserDTO selectUser(String uid) {
		return null;
	}
	
	@Test
	public void selectUsers() {
		List<UserDTO> users = mapper.selectUsers();
		
		for(UserDTO user : users) {
			log.info("user : " + user);
		}
		
	}
	
	public void updateUser(UserDTO dto) {
		
	}
	public void deleteUser(String uid) {
		
	}
}
