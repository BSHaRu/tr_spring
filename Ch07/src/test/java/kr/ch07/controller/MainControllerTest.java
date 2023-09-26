package kr.ch07.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

/*
 *  - ~~All은 최초 한번만 실행 되는 AOP 함수이다.
 */

@WebMvcTest(controllers = MainController.class)
public class MainControllerTest {

	// MVC 테스트를 위한 가상 mvc 객체
	@Autowired
	private MockMvc mvc;
	
	
	@BeforeAll
	public static void beforeAll() {
		System.out.println("================ beforeAll test ================");
	}
	
	@BeforeEach
	public void beforeEach() {
		System.out.println("================ beforeEach test ================");
		
	}
	
	@AfterAll
	public static void AfterAll() {
		System.out.println("================ AfterAll test ================");
		
	}
	
	@AfterEach
	public void AfterEach() {
		System.out.println("================ AfterEach test ================");
		
	}
	
	
	//@Test
	public void index() throws Exception {
		System.out.println("================ index test ================");
		
		mvc
		.perform(get("/index"))				// index 요청 테스트(get방식)
		.andExpect(status().isOk())			// 요청 결과에 대한 상태 값 확인 
		.andExpect(view().name("/index"))	// 해당 결과에 대한 반환 되는 view 이름 테스트
		.andDo(print());
		
	}
	
	@Test
	public void include() throws Exception {
		System.out.println("================ include test ================");
		
		mvc.perform(
				get("/include")
				.param("name", "김춘추")
				.param("age", "23")
			)
			.andExpect(status().isOk())
			.andExpect(view().name("/include"))
			.andDo(print());
	}
	

	public void layout() {
		System.out.println("================ layout test ================");
	}
}
