package UML;

public class TestSports {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Football f = new Football();
		Baseball b = new Baseball();
		Fencing fc = new Fencing();
		
		f.name = "Footbal";
		f.playerNum = 22;
		
		b.name = "Baseball";
		b.playerNum = 18;
		
		fc.name = "Fencing";
		fc.playerNum = 2;
		
		System.out.println(f.name+"의 플레이어 수는 "+f.playerNum+"명 입니다.");
		f.UsingBall();
		
		System.out.println(b.name+"의 플레이어 수는 "+b.playerNum+"명 입니다.");
		b.UsingTool();
		
		System.out.println(fc.name+"의 플레이어 수는 "+fc.playerNum+"명 입니다.");
		fc.UsingSword();
	}

}
