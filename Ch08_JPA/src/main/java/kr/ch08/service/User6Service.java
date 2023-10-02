package kr.ch08.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ch08.dao.User6DAO;
import kr.ch08.dto.User6DTO;
import kr.ch08.entity.User6Entity;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class User6Service {

	@Autowired
	private User6DAO dao;
	
	public void insertUser6(User6DTO dto) {
		// DTO -> Entity로 변환
		User6Entity entity = dto.toEntity();
		
		dao.save(entity);
	}
	public User6DTO selectUser6(String uid) {
		// Optional : null값을 체크해주는 자료구조
		Optional<User6Entity> result = dao.findById(uid);
		
		// Entity -> DTO로 변환
		User6DTO dto = result.get().toDTO();
		
		return dto;
	}
	public List<User6DTO> selectUser6s() {
		// Entity -> DTO로 변환
		List<User6DTO> list = dao.findAll()
							.stream()
							.map(User6Entity::toDTO)
							.collect(Collectors.toList());
		
		log.info("selectUser6s : "+list);
		return list;
	}
	public void updateUser6(User6DTO dto) {
		User6Entity entity = dto.toEntity();
		dao.save(entity);
	}
	public void deleteUser6(String uid) {
		dao.deleteById(uid);
	}	
}
