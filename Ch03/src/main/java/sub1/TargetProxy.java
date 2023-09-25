package sub1;

public class TargetProxy implements Target {

	private Target target;
	
	public TargetProxy(Target target) {
		this.target = target;
	}
	
	public void before() {
		System.out.println("부가 기능 실행 before ============");
	}
	
	public void after() {
		System.out.println("부가 기능 실행 after ============");
	}
	
	@Override
	public void doBusiness() {
		before();
		target.doBusiness();
		after();
	}

}
