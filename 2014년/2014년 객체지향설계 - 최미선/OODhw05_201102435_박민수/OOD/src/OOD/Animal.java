package OOD;

public class Animal {
	protected int legs;
	
	public Animal(){
		
	}
	public Animal(int leg){
		this.legs = leg;
	}
	
	public void eat(){
		
	}
	
	public void walk(){
		System.out.println("이 동물은 "+this.legs+"개의 다리로 걷습니다.");
	}
}
