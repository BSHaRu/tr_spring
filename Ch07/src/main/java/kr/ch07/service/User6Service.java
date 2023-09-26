package kr.ch07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ch07.dao.User6Mapper;
import kr.ch07.dto.User6DTO;

@Service
public class User6Service {

	@Autowired
	private User6Mapper mapper;
	
	public void insertUser(User6DTO dto) {
		mapper.insertUser(dto);
	}
	public User6DTO selectUser(String uid) {
		return mapper.selectUser(uid);
	}
	public List<User6DTO> selectUsers() {
		return mapper.selectUsers();
	}
	public void updateUser(User6DTO dto) {
		mapper.updateUser(dto);
	}
	public void deleteUser(String uid) {
		mapper.deleteUser(uid);
	}
}
