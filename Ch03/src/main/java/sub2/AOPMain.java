package sub2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * 날짜 : 2023.09.19
 * 내용 : 스프링 XML 기반 AOP 실습
 */

public class AOPMain {

	public static void main(String[] args) {
		
		// 스프링 컨텍스트 객체 생성(컨테이너)
		ApplicationContext ctx 
			= new GenericXmlApplicationContext("application.xml");
		
		// Service 객체 가져오기
		Service service = (Service) ctx.getBean("service1");
		
		service.insert();
		
	}

}
