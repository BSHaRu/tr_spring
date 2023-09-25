package ch05.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ch05.dto.UserDTO;

@Repository
public class UserDAO {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	public void insertUser(UserDTO dto) {
		String sql = "INSERT INTO user1 VALUES(?,?,?,?)";
		Object[] params = {
				dto.getUid(),
				dto.getName(),
				dto.getHp(),
				dto.getAge()
		};
		
		// update() 메서드로 select 빼고 다 실행함
		jdbc.update(sql, params);
	}
	
	public UserDTO selectUser(String uid) {
		String sql = "SELECT * FROM user1 WHERE uid = ?";
		UserDTO dto 
			= jdbc.queryForObject(sql, new UserRowMapper(), uid);
		
		return dto;
	}
	
	public List<UserDTO> selectUsers() {
		String sql = "SELECT * FROM user1";
		List<UserDTO> list 
			= jdbc.query(sql, new UserRowMapper());
		
		return list;
	}
	
	public void updateUser(UserDTO dto) {
		String sql = "UPDATE user1 SET"
				+ " name = ?, "
				+ " hp = ?, "
				+ " age = ?"
				+ " WHERE uid = ?";
		Object[] params = {
				dto.getName(),
				dto.getHp(),
				dto.getAge(),
				dto.getUid()
		};
		
		jdbc.update(sql, params);
	}
	
	public void deleteUser(String uid) {
		String sql = "DELETE FROM user1 WHERE uid = ?";
		jdbc.update(sql, uid);
	}
}
