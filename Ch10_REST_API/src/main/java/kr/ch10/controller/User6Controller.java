package kr.ch10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ch10.dto.User6DTO;
import kr.ch10.service.User6Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user6/*")
public class User6Controller {

	@Autowired
	private User6Service us;
	
	@GetMapping("list")
	public String list(Model model) {
		List<User6DTO> users = us.seleteUser6s();
		model.addAttribute("users", users);
		
		return "user6/list";
	}
	
	@GetMapping("register")
	public String register() {
		return "user6/register";
	}
	
	@PostMapping("register")
	public String register(User6DTO dto) {
		log.info("reg dto1 : " + dto);
		us.insertUser6(dto);
		return "redirect:list";
	}
}
