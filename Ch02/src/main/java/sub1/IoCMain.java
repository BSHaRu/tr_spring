package sub1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/* 
 *  날짜 : 2023.09.18
 *  내용 : Spring IoC 실습
 *  
 *  Ioc
 *   - Inversion of Control(제어의 역전) 의미로
 *   	객체의 생성, 소멸 등 관리를 container에서 담당
 *   - 일반적으로 container는 Ioc 컨테이너 즉, Spring Container를 의미
 */

public class IoCMain {

	public static void main(String[] args) {
		
		// spring context(container) 객체 생성
		ApplicationContext ctx 
			= new GenericXmlApplicationContext("application.xml");
		
		LGTV lg = (LGTV) ctx.getBean("lg");
		lg.powerOn();
		lg.soundUp();
		lg.soundDown();
		lg.powerOff();

		
	}

}
