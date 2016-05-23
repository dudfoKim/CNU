package BlackJack;

import java.util.ArrayList;

public class Dealer {
	private ArrayList<Card> dHand = new ArrayList<Card>();
	private int ValueOfHand = 0;
	
	public int ValueOfHand(){
		return this.ValueOfHand;
	}
	
	// 카드를 드로우하는 메소드
	public void getCard(Card c){
		dHand.add(c);
	}
	
	// 딜러의 핸드에 있는 카드를 보여주는 메소드(첫번째 카드는 숨겨짐)
	public void showHand(){
		System.out.print("딜러의 현재 핸드 (첫번째카드는 숨겨짐) : ");
		for(int i =1 ; i<dHand.size(); i++){
			System.out.print(dHand.get(i)+" ");
		}
	}
	
	// 최종 딜러의 핸드에 있는 모든 카드를 보여주는 메소드
	public void finalshowHand(){
		System.out.print("딜러의 최종핸드 : ");
		for(int i =0 ; i<dHand.size(); i++){
			System.out.print(dHand.get(i)+" ");
		}
	}
	
	//딜러의 핸드에있는 카드의 값을 모두 합하는 메소드
	public void getValueOfHand(){
		int ValueOfHand = 0;
		int findAce = 0;
		for(int i = 0; i<dHand.size();i++){
			if (dHand.get(i).getName().equals("ACE")) {
				findAce++;
				continue;
			}
			ValueOfHand += dHand.get(i).getVlaue();
		}
		// 딜러의 핸드에서 ACE는 무조건 11로 계산한다.
		while (findAce > 0){
				ValueOfHand += 11;			
		}
		this.ValueOfHand = ValueOfHand;
	}
}
