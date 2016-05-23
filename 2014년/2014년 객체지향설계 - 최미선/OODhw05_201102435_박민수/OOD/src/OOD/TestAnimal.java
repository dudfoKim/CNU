package OOD;

public class TestAnimal {

	public static void main(String[] args) {
		
		System.out.println("**Fish의 메소드 호출");
		Fish a = new Fish("붕어싸만코");
		System.out.println("물고기의 이름은 "+a.getName()+"입니다.");
		a.play();
		a.eat();
		a.walk();
		
		System.out.println("**Cat의 메소드 호출");
		Cat c = new Cat();
		c.setName("나비");
		System.out.println("고양이의 이름은 "+c.getName()+"입니다.");
		c.setName("페르시안");
		System.out.println("고양이의 이름을 "+c.getName()+"으로 바꿨습니다.");
		c.play();
		c.eat();
		c.walk();
		
		System.out.println("**Animal Fish의 메소드 호출");
		Animal b = new Fish();
		b.eat();
		b.walk();
		
		System.out.println("**Animal Spdier의 메소드 호출");
		Animal e = new Spider();
		e.eat();
		e.walk();
		
		System.out.println("**Pet Cat의 메소드 호출");
		Pet f = new Cat();
		f.play();
		f.setName("맥킨토시");
		System.out.println("애완 동물의 이름을 "+f.getName()+"(으)로 변경합니다.");
		
		System.out.println("**객체에 대한 강제 타입변환");
		Animal q = new Fish();
		Fish ff = (Fish)q;
		ff.eat();
		ff.walk();
		ff.play();
		
		
		System.out.println("**다형성 테스트");
		Animal[] k = new Animal[3];
		k[0] = new Spider();
		k[1] = new Cat();
		k[2] = new Fish();
		k[0].walk();
		k[0].eat();
		k[1].walk();
		k[1].eat();
		k[2].walk();
		k[2].eat();
		}

}
