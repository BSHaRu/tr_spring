package kr.ch07.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.ch07.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {

	@GetMapping(value= {"/", "/index" })
	public String index(Model model) {
	
		String str1 = "스프링 부트";
		String str2 = "타임 리프";
		
		// 생성자를 이용한 초기화
		UserDTO user1 = new UserDTO("p101", "김춘추", "010-4441-2321", 23);
		
		// setter를 이용한 초기화
		UserDTO user2 = new UserDTO();
		user2.setUid("p102");
		user2.setName("강아지");
		user2.setHp("010-7474-4882");
		user2.setAge(85);
		
		
		// Builder를 이용한 초기화
		UserDTO user3 = UserDTO.builder()
								.uid("p103")
								.name("장보고")
								.hp("010-1234-5484")
								.age(57)
								.build();
		
		UserDTO user4 = null;

		
		// List 생성
		List<UserDTO> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		
		
		model.addAttribute("str1", str1);
		model.addAttribute("str2", str2);
		
		model.addAttribute("user1", user1);
		model.addAttribute("user2", user2);
		model.addAttribute("user3", user3);
		model.addAttribute("user4", user4);
		model.addAttribute("users", users);
		
		log.info("user1 : " + user1);
		log.info("user2 : " + user2);
		log.info("user3 : " + user3);
		
		return "/index";
	}
	
	@GetMapping("/include")
	public String include(String name, int age) {
		
		log.info("include name : " +name);
		log.info("include age : " +age);
		return "/include";
	}
	
	@GetMapping("/layout")
	public String layout() {
		return "/layout";
	}
}
