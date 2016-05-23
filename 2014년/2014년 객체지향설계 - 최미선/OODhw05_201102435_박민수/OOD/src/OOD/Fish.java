package OOD;

public class Fish extends Animal implements Pet{

	private String name;
	
	public Fish() {
		super(0);
		// TODO Auto-generated constructor stub
	}
	
	public Fish(String name){
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void play() {
		System.out.println("물고기가 헤엄치며 놉니다.");
	}
	
	public void walk(){
		System.out.println("이 동물을 헤엄칩니다.");
	}
	
	public void eat(){
		System.out.println("물고기는 먹이를 먹습니다.");
	}

}
