package kr.co.sboard.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.sboard.entity.TermsEntity;
import kr.co.sboard.service.UserSevice;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/user/*")
@Controller
@Log4j2
public class UserController {

	@Autowired
	private UserSevice us;
	
	@GetMapping("login")
	public String login() {
		return "/user/login";
	}
	
	@GetMapping("terms")
	public String terms(Model model) {
		TermsEntity terms = us.findByTerms();
		model.addAttribute("terms", terms);
		return "/user/terms";
	}
	
	
}
