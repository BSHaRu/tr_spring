package kr.ch08.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.ch08.dao.User6DAO;
import kr.ch08.entity.User6Entity;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {
	
	@Autowired
	private User6DAO dao;
	
	@GetMapping(value = {"/","/index"})
	public String index() {
		return "/index";
	}
	
	@GetMapping("/query1")
	public String query1() {
		User6Entity entity = dao.findUser6EntityByUid("abc");
		log.info("query1 : "+entity);
		return "redirect:/";
	}
	
	@GetMapping("/query2")
	public String query2() {
		List<User6Entity> entity = dao.findUser6EntityByName("운영자");
		
		log.info("query2 : "+entity);
		return "redirect:/";
	}
	
	@GetMapping("/query3")
	public String query3() {
		List<User6Entity> entity = dao.findUser6EntityByNameNot("초콜릿");
		
		log.info("query3 : "+entity);
		return "redirect:/";
	}
	
	@GetMapping("/query4")
	public String query4() {
		User6Entity entity = dao.findUser6EntityByUidAndName("admin", "운영자");
		log.info("query4 : "+entity);
		
		return "redirect:/";
	}
	
	@GetMapping("/query5")
	public String query5() {
		List<User6Entity> entity = dao.findUser6EntityByUidOrName("admin","초콜릿");
		
		log.info("query5 : "+entity);
		return "redirect:/";
	}
	
	@GetMapping("/query6")
	public String query6() {
		return "redirect:";
	}
	
	@GetMapping("/query7")
	public String query7() {
		return "redirect:";
	}
	
	@GetMapping("/query8")
	public String query8() {
		return "redirect:";
	}
	
	@GetMapping("/query9")
	public String query9() {
		return "redirect:";
	}
	
	@GetMapping("/query10")
	public String query10() {
		return "redirect:";
	}
	
	@GetMapping("/query11")
	public String query11() {
		return "redirect:";
	}
	
	@GetMapping("/query12")
	public String query12() {
		return "redirect:";
	}
	
	@GetMapping("/query13")
	public String query13() {
		return "redirect:";
	}
	
	@GetMapping("/query14")
	public String query14() {
		return "redirect:";
	}
	
	@GetMapping("/query15")
	public String query15() {
		return "redirect:";
	}
	
	@GetMapping("/query16")
	public String query16() {
		return "redirect:";
	}
	
	@GetMapping("/query17")
	public String query17() {
		return "redirect:";
	}
	
	@GetMapping("/query18")
	public String query18() {
		return "redirect:";
	}
	
	@GetMapping("/query19")
	public String query19() {
		return "redirect:";
	}
	
	@GetMapping("/query20")
	public String query20() {
		return "redirect:";
	}
	
	@GetMapping("/query21")
	public String query21() {
		return "redirect:";
	}
	
	@GetMapping("/query22")
	public String query22() {
		return "redirect:";
	}

	@GetMapping("/query23")
	public String query23() {
		return "redirect:";
	}
	
	
}
