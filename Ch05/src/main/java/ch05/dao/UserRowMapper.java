package ch05.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ch05.dto.UserDTO;

public class UserRowMapper implements RowMapper<UserDTO> {

	@Override
	public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserDTO dto = new UserDTO();
		dto.setUid(rs.getString(1));
		dto.setName(rs.getString(2));
		dto.setHp(rs.getString(3));
		dto.setAge(rs.getInt(4));
		
		return dto;
	}

}
