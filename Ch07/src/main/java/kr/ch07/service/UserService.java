package kr.ch07.service;

import java.util.List;

import kr.ch07.dao.UserMapper;
import kr.ch07.dto.UserDTO;

public class UserService {

	private UserMapper mapper;
	
	public void insertUser(UserDTO dto) {
		mapper.insertUser(dto);
	}
	public UserDTO selectUser(String uid) {
		return mapper.selectUser(uid);
	}
	public List<UserDTO> selectUsers() {
		return mapper.selectUsers();
	}
	public void updateUser(UserDTO dto) {
		mapper.updateUser(dto);
	}
	public void deleteUser(String uid) {
		mapper.deleteUser(uid);
	}
}
