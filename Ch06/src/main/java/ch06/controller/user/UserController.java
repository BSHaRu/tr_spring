package ch06.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ch06.dto.UserDTO;
import ch06.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/user/list")
	public String list(Model model) {
		
		List<UserDTO> users = service.selectUsers();
		model.addAttribute("users", users);
		
		return "/user/list";
	}
	
	@GetMapping("/user/register")
	public String register() {
		return "/user/register";
	}
	
	@PostMapping("/user/register")
	public String register(UserDTO dto) {
		service.insertUser(dto);
		
		System.out.println("등록 완료 되었습니다.");
		return "redirect:/user/list";
	}
	
	@GetMapping("/user/modify")
	public String modify(String uid, Model model) {
		UserDTO dto = service.selectUser(uid);
		model.addAttribute("userDTO", dto);
		
		return "/user/modify";
	}

	
	@PostMapping("/user/modify")
	public String modify(UserDTO dto) {
		service.updateUser(dto);
		
		System.out.println("수정 되었습니다.");
		return "redirect:/user/list";
	}
	
	
	@GetMapping("/user/delete")
	public String delete(String uid) {
		service.deleteUser(uid);
		
		System.out.println("삭제 되었습니다.");
		return "redirect:/user/list";
	}
	
}
