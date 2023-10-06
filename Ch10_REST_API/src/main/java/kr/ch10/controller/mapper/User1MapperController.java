package kr.ch10.controller.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.ch10.dto.User1DTO;
import kr.ch10.service.mapper.User1MapperService;

@Controller
public class User1MapperController {
	
	@Autowired
	private User1MapperService ums;
	
	@GetMapping("user1/list")
	public String list(Model model) {
		List<User1DTO> users = ums.selectUser1s();
		model.addAttribute("users",users);
		return "user1/list";
	}
	
	@GetMapping("user1/register")
	public String register() {
		return "user1/register";
	}
	
	@PostMapping("user1/register")
	public String register(User1DTO dto) {
		ums.insertUser1(dto);
		
		return "redirect:list";
	}
	
	@GetMapping("user1/modify")
	public String modify(Model model, String uid) {
		User1DTO dto = ums.selectUser1(uid);
		model.addAttribute("user", dto);
		return "user1/modify";
	}
	
	@PostMapping("user1/modify")
	public String modify(User1DTO dto) {
		ums.updateUser1(dto);
		return "redirect:list";
	}
	
	@GetMapping("user1/delete")
	public String delete(String uid) {
		ums.deleteUser1(uid);
		return "redirect:list";
	}
}
