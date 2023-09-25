package sub1;

/*
 * 날짜 : 2023.09.19
 * 내용 : proxy기반 AOP 실습
 */

public class ProxyTest {

	public static void main(String[] args) {
		Target target = new TargetImpl();
		Target prxoy = new TargetProxy(target);
		
		prxoy.doBusiness();
		
	}

}
