package sub1;

import org.springframework.beans.factory.annotation.Autowired;

public class LGTV {

	@Autowired
	private Speaker spk;
	
	public void powerOn() {
		System.out.println("===================LG TV powerON===============");
	}
	public void powerOff() {
		System.out.println("===================LG TV powerOff===============");
	}
	public void soundUp() {
		spk.soundUp();
	}
	public void soundDown() {
		spk.soundDown();
	}
}
