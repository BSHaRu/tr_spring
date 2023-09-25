package sub2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("service1")
public class Service {

	@Autowired
	private LogAdvice advice;
	
	public void insert() {
		advice.beforeLog();
		System.out.println("핵심 로직 insert start=======================");
		advice.afterLog();
	}
	public void select() {
		System.out.println("핵심 로직 select start=======================");
	}
	public void update(int no) {
		System.out.println("핵심 로직 update start=======================");
	}
	public void delete(int no, String name) {
		System.out.println("핵심 로직 delete start=======================");
	}
	
}
