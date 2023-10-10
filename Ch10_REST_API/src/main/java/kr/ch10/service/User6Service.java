package kr.ch10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ch10.dto.User6DTO;
import kr.ch10.entity.User6Entity;
import kr.ch10.mapper.User6Mapper;
import kr.ch10.repository.User6Repository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class User6Service {

	@Autowired
	private User6Repository repo;
	
	@Autowired
	private User6Mapper mapper;
	
	public void insertUser6(User6DTO user6) {
		// DTO -> Entity
		User6Entity entity = mapper.toEntity(user6);
		log.info("insert entity : " + entity);
		repo.save(entity);
		log.info("insert repo : " + repo.save(entity));
	}
	public User6DTO seleteUser6(String uid) {
		// Entity -> DTO
		User6DTO dto 
			= mapper.toDTO(repo.findById(uid).get());
		return dto; 
	}
	public List<User6DTO> seleteUser6s() {
		// Entity -> DTO로 변환
		List<User6Entity> entity = repo.findAll();
		return entity.stream().map(mapper::toDTO).toList();
	}
	public void updateUser6(User6DTO user6) {
		// DTO -> Entity
		User6Entity entity = mapper.toEntity(user6);
		repo.save(entity);
	}
	public void deleteUser6(String uid) {
		repo.deleteById(uid);
	}
	
}
