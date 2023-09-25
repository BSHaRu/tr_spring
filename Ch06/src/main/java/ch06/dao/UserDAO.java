package ch06.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch06.dto.UserDTO;

@Repository
public class UserDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertUser(UserDTO dto) {
		mybatis.insert("user.insertUser", dto);
	}
	
	public UserDTO selectUser(String uid) {
		UserDTO dto = mybatis.selectOne("user.selectUser", uid);
		
		return dto;
	}
	
	public List<UserDTO> selectUsers() {
		return mybatis.selectList("user.selectUsers");
	}
	
	public void updateUser(UserDTO dto) {
		mybatis.update("user.updateUser", dto);
	}
	
	public void deleteUser(String uid) {
		mybatis.delete("user.deleteUser",uid);
	}
}
