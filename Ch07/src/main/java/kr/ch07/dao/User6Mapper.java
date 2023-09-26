package kr.ch07.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ch07.dto.User6DTO;

@Mapper
public interface User6Mapper {
	
	public void insertUser(User6DTO dto);
	public User6DTO selectUser(String uid);
	public List<User6DTO> selectUsers();
	public void updateUser(User6DTO dto);
	public void deleteUser(String uid);
}
