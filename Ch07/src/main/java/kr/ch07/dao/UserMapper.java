package kr.ch07.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ch07.dto.UserDTO;

@Mapper
public interface UserMapper {
	
	public void insertUser(UserDTO dto);
	public UserDTO selectUser(String uid);
	public List<UserDTO> selectUsers();
	public void updateUser(UserDTO dto);
	public void deleteUser(String uid);
}
