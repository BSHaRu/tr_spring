package sub1;

public class TargetImpl implements Target {

	@Override
	public void doBusiness() {
		System.out.println("핵심 로직(core concern) 실행============");
	}

}
