package sub2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * 날짜 : 2023.09.18
 * 내용 : 스프링 컨테이너 실습
 * 
 * DI(Dependency Injection)
 *  - 의존성 주입 의미로 객체를 생성하는 방식이 아니라 컨테이너로부터 주입 하는 방식
 *  - constructor, setter, filed 3가지 주입 방식이 있음
 *  - @Component 선언으로 객체를 컨테이너에 관리 / 등록
 *  	-> @Component는 @Controller, @Service, @Repository로 세분화 
 *  
 */

public class SpringContainerTest {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx 
			= new GenericXmlApplicationContext("application.xml");
		
		// @Component("com")를 선언 했기때문에 bean에서 com으로 가져올 수 있는 거임
		// -> Component에서 com 선언 안해놓으면 computer 이렇게 다 써줘야됨
		Computer com = (Computer) ctx.getBean("com");
		com.show();
		
	}
}
