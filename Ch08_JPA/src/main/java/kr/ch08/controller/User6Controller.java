package kr.ch08.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.ch08.dto.User6DTO;
import kr.ch08.service.User6Service;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class User6Controller {

	@Autowired
	private User6Service us;
	
	@GetMapping("/user6/register")
	public String register() {
		return "/user6/register";
	}
	
	@PostMapping("/user6/register")
	public String registerPost(User6DTO dto) {
		us.insertUser6(dto);
		
		log.info("등록 완료 - " + dto);
		return "redirect:/user6/list";
	}
	
	@GetMapping("/user6/list")
	public String list(Model model) {
		List<User6DTO> users = us.selectUser6s();
		model.addAttribute("users", users);
		
		return "/user6/list";
	}
	
	@GetMapping("/user6/modify")
	public String modify(String uid, Model model) {
		
		User6DTO dto = us.selectUser6(uid);
		model.addAttribute("user", dto);
		
		return "/user6/modify";
	}
	
	@PostMapping("/user6/modify")
	public String modify(User6DTO dto) {
		us.updateUser6(dto);
		log.info("수정 되었습니다 - " + dto);
		return "redirect:/user6/list";
	}
	
	@GetMapping("/user6/delete")
	public String delete(String uid) {
		us.deleteUser6(uid);
		
		log.info("삭제 되었습니다.");
		return "redirect:/user6/list";
	}
	
}
