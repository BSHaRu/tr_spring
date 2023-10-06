package kr.ch10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ch10.entity.User3Entity;
import kr.ch10.repository.User3Repository;

@Service
public class User3Service {

	@Autowired
	private User3Repository repo;
	
	public void insertUser3(User3Entity user3) {
		repo.save(user3);
	}
	public User3Entity selectUser3(String uid) {
		return repo.findById(uid).get();
	}
	public List<User3Entity> selectUser3s() {
		return repo.findAll();		
	}
	public void updateUser3(User3Entity user3) {
		repo.save(user3);
	}
	public void deleteUser3(String uid) {
		repo.deleteById(uid);
	}
}
