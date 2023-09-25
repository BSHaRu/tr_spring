package ch04.controller.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ch04.dto.ModelDTO;

@Controller
public class AnnotationController {

	@RequestMapping(value="/annotation/param",method = RequestMethod.GET)
	public String param() {
		return "/annotation/param";
	}

	@RequestMapping(value="/annotation/param1",method = RequestMethod.POST)
	public String param1(@RequestParam("uid") String uid, Model model) {
		System.out.println("uid : " + uid);

		// model 참조를 통한 view 데이터 출력
		model.addAttribute("uid", uid);
		
		return "/result/param";
	}
	
	@PostMapping("/annotation/param2")
	// @RequestParam은 생략 가능
	public String param2(Model model, String uid, String name) {
		System.out.println("uid : " + uid);
		System.out.println("name : " + name);
		
		model.addAttribute("uid", uid);
		model.addAttribute("name", name);
		
		return "/result/param";
	}
	
	@RequestMapping(value="/annotation/param3",method = RequestMethod.POST)
	// 매개변수를 순서대로 안받아도 실행이 됨
	public String param3(String uid, int age, String name, String hp, Model model ) {
		System.out.println("uid : " + uid);
		System.out.println("age : " + age);
		System.out.println("name : " + name);
		System.out.println("hp : " + hp);
		
		model.addAttribute("uid", uid);
		model.addAttribute("age", age);
		model.addAttribute("name", name);
		model.addAttribute("hp", hp);
		
		return "/result/param";
	}
	
	@GetMapping("/annotation/model")
	public String model() {
		return "/annotation/model";
	}
	
	@PostMapping("/annotation/model1")
	public String model1(@ModelAttribute ModelDTO dto) {
		return "/result/model";
	}
	
	@PostMapping("/annotation/model2")
	public String model2(@ModelAttribute("user") ModelDTO dto) {
		return "/result/model";
	}
	
	@PostMapping("/annotation/model3")
	// @ModelAttribute 생략가능
	public String model3(ModelDTO dto) {
		return "/result/model";
	}
	
	
}
