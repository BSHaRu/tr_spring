package kr.ch10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ch10.entity.User1Entity;
import kr.ch10.service.User1Service;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class User1Controller {

	@Autowired
	private User1Service us;
	
	// @ResponseBody : 페이지 이동이 아니라 JSON 데이터로 출력
	@ResponseBody
	@GetMapping("user1")
	public List<User1Entity> list() {
		List<User1Entity> user1 = us.selectUser1s(); 
		
		return user1;
	}
	
	
	// @PathVariable : URL 경로에 변수를 넣어줌
	// -> @GetMapping("user1/{uid}")에서 {uid}를 쓰기 위해서
	// @PathVariable("uid")를 써주면 URL에서 {uid}값을 받을 수 있다.
	@ResponseBody
	@GetMapping("user1/{uid}")
	public User1Entity user1(@PathVariable("uid") String uid) {
		log.info("btnUserSelect : "+us.selectUser1(uid));
		return us.selectUser1(uid);
	}
	
	@ResponseBody
	@PostMapping("user1")
	public void register(User1Entity user1) {
		us.insertUser1(user1);
	}
	
	@ResponseBody
	@PutMapping("user1")
	public void modify(User1Entity user1) {
		us.updateUser1(user1);
	}
	
	@ResponseBody
	@DeleteMapping("user1/{uid}")
	public void delte(@PathVariable("uid") String uid) {
		us.deleteUser1(uid);
	}
	
}
