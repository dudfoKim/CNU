package BlackJack;

import java.util.ArrayList;

public class Player {
	private ArrayList<Card> pHand = new ArrayList<Card>();
	private int ValueOfHand = 0;
	
	public int ValueOfHand(){
		return this.ValueOfHand;
	}
	
	//카드를 핸드로 받아오는 매소드
	public void getCard(Card c){
		pHand.add(c);
	}
	
	// 플레이어의 핸드에 있는 카드를 보여주는 메소드
	public void showHand(){
		System.out.print("플레이어의 현재 핸드 : ");
		for(int i =0 ; i<pHand.size(); i++){
			System.out.print(pHand.get(i)+" ");
		}
	}
	
	//플레이어의 핸드에있는 카드의 값을 모두 합하는 메소드
	public void getValueOfHand(){
		int ValueOfHand =0;
		int findAce = 0;
		for(int i = 0; i<pHand.size();i++){
			if (pHand.get(i).getName().equals("ACE")) {
				findAce++;
				continue;
			}
			ValueOfHand += pHand.get(i).getVlaue();
		}
		// 플레이어의 핸드에서 ACE는 나머지 핸드의 카드의 합이 10이하일경우 11로, 아닐경우 1로 계산한다.
		while(findAce > 0){
			if(ValueOfHand <= 10 )
				ValueOfHand += 11;			
			else 
				ValueOfHand += 1;
			findAce--;
		}
		this.ValueOfHand = ValueOfHand;
	}

}
