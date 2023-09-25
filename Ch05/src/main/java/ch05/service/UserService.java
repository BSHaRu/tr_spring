package ch05.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch05.dao.UserDAO;
import ch05.dto.UserDTO;

@Service
public class UserService {

	@Autowired
	private UserDAO dao;

	public void insertUser(UserDTO dto) {
		dao.insertUser(dto);
	}

	public UserDTO selectUser(String uid) {
		return dao.selectUser(uid);
	}

	public List<UserDTO> selectUsers() {
		return dao.selectUsers();
	}

	public void updateUser(UserDTO dto) {
		dao.updateUser(dto);
	}

	public void deleteUser(String uid) {
		dao.deleteUser(uid);
	}
}
