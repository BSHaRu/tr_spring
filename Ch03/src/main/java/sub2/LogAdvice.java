package sub2;

import org.springframework.stereotype.Component;

@Component
public class LogAdvice {

	public void beforeLog() {
		System.out.println("===============================");
		System.out.println("중간에 가로 채기! beforeLog");
	}
	
	public void afterLog() {
		System.out.println("중간에 가로 채기! afterLog");
		System.out.println("===============================");
	}
}
