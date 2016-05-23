/*학번 201102435
    이름 박민수*/

package hw02_3;

public class Run {

	public static void main(String[] args) {
		//사용할 코인들
		
		Coin c1 = new Coin(50);
		Coin c2 = new Coin(10);
		Coin c3 = new Coin(100);
		Coin c4 = new Coin(500);
		Coin c5 = new Coin(1000);
		Coin c6= new Coin(10);
		Coin c7 = new Coin(55);
		Coin c8 = new Coin(10);
		Coin c9 = new Coin(100);
		Coin c10 = new Coin(50);
		
		//Bag에 c7코인을 제외한 코인 삽입
		System.out.println("사용할 코인값을 삽입합니다.");
		ListBag bag = new ListBag();
		bag.Add(c1);
		bag.Add(c2);
		bag.Add(c3);
		bag.Add(c4);
		bag.Add(c5);
		bag.Add(c6);
		bag.Add(c8);
		bag.Add(c9);
		bag.Add(c10);
		//Bag속에 코인들 출력
		bag.printBag();
		
		//잘못된 코인삽입
		System.out.println("잘못된 코인값 55삽입");
		bag.Add(c7);
		
		//해당코인의갯수찾기
		System.out.println("50코인의 개수");
		bag.numOfCoin(c1);
		System.out.println("10코인의 개수");
		bag.numOfCoin(c2); 
		
		//특정코인 하나만 삭제
		System.out.println("500코인중 하나 삭제");
		bag.removeAt(c4);
		bag.numOfCoin(c4);
		//특정코인 모두 삭제
		System.out.println("50코인 모두 삭제");
		bag.remove(c1);
		bag.numOfCoin(c1);
		
		//특정코인이있는지 확인
		System.out.println("10코인이 존재하는지 확인");
		if(bag.isExist(c2)) System.out.println(c2.getCoin()+"코인은 존재합니다.");
		else System.out.println(c2.getCoin()+"코인은 존재하지않습니다.");
		
		System.out.println("55코인이 존재하는지 확인");
		if(bag.isExist(c7)) System.out.println(c7.getCoin()+"코인은 존재합니다.");
		else System.out.println(c7.getCoin()+"코인은 존재하지않습니다.");
		
		System.out.println("500코인이 존재하는지 확인");
		if(bag.isExist(c4)) System.out.println(c4.getCoin()+"코인은 존재합니다.");
		else System.out.println(c4.getCoin()+"코인은 존재하지않습니다.");
		
		//Bag 속에 있는 Coin 모두 삭제
		System.out.println("Bag속에 존재하는 모든 코인삭제");
		bag.removeAll();
		bag.printBag();
	}

}
