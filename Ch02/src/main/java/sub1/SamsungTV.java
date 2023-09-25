package sub1;

public class SamsungTV {

	private Speaker spk;
	
	public void powerOn() {
		System.out.println("===================Samsung TV powerON===============");
	}
	public void powerOff() {
		System.out.println("===================Samsung TV powerOff===============");
	}
	public void soundUp() {
		spk.soundUp();
	}
	public void soundDown() {
		spk.soundDown();
	}
}
