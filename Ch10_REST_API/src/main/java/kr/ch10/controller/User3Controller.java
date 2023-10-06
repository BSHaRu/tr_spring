package kr.ch10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ch10.entity.User3Entity;
import kr.ch10.service.User3Service;

@RestController
public class User3Controller {

	@Autowired
	private User3Service us;
	
	@GetMapping("user3")
	public List<User3Entity> list() {
		List<User3Entity> list =  us.selectUser3s();
		return list;
	}
	
	@GetMapping("user3/{uid}")
	public User3Entity select(@PathVariable("uid") String uid) {
		return us.selectUser3(uid);
	}
	
	@PostMapping("user3")
	public void register(User3Entity user3) {
		us.insertUser3(user3);
	}
	
	@PutMapping("user3")
	public void modify(User3Entity user3) {
		us.updateUser3(user3);
	}
	
	@DeleteMapping("user3/{uid}")
	public void delete(@PathVariable("uid") String uid) {
		us.deleteUser3(uid);
	}
}
