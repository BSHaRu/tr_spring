package sub2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 그냥 com으로 이름을 줘서 사용함
@Component("com")
public class Computer {

	private CPU cpu;
	private RAM ram;
	
	// DI - Field Inject
	@Autowired
	private HDD hdd;
	
	
	// DI - Constructor Inject
	// -> Autowired 선언 해주면 new 생성자로 직접 생성하는게 아니라
	// spring에서 di를 통해서 알아서 주입해주는거임
	@Autowired
	public Computer(CPU cpu) {
		this.cpu = cpu;
	}
	
	// ID - Setter Inject
	@Autowired
	public void setRam(RAM ram) {
		this.ram = ram;
	}
	
	
	public void show() {
		cpu.show();
		ram.show();
		hdd.show();
	}
	
}
