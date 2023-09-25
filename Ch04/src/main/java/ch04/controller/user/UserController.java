package ch04.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	
	@GetMapping("/user/register")
	public String register() {
		return "/user/register";
	}
	
	@PostMapping("/user/register")
	public String register(@RequestParam("uid") String uid, 
							@RequestParam("name") String name, 
							@RequestParam("hp") String hp, 
							@RequestParam("age") int age) {
		
		System.out.println("uid : " + uid);
		System.out.println("name : " + name);
		System.out.println("hp : " + hp);
		System.out.println("age : " + age);
		
		return "redirect:/index";
	}
	
	
}
