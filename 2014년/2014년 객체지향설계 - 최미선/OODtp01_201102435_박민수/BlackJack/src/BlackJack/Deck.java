package BlackJack;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> mDeck = new ArrayList<Card>();
	private int size = 0;
	private Card c = new Card();
	
	//생성자
	Deck(){
		createHearts();
		createDiamonds();
		createSpades();
		createClovers();
		
		Collections.shuffle(mDeck);
	}
	
	// 덱을 초기화 시키는 메소드.
	public void resetDeck(){
		mDeck = null;
		size = 0;
		
		createHearts();
		createDiamonds();
		createSpades();
		createClovers();
		
		Collections.shuffle(mDeck);
	}
	
	//덱에 다음 번카드를 딜러와 플레이어에게 나누어주는 메소드
	public Card getNextCard(){
		Card rCard = mDeck.get(52-size);
		size--;		
		return rCard;
	}
	
	private void createHearts(){
		for(int i =1 ; i<14 ; i++){
			c = new Card();
			c.setSuit("Hearts");
			if(i == 1){
				c.setName("ACE");
				c.setVlaue(i);
			}
			else if(i == 11){
				c.setName("J");
				c.setVlaue(10);
			}
			else if(i == 12){
				c.setName("Q");
				c.setVlaue(10);
			}
			else if(i == 13){
				c.setName("K");
				c.setVlaue(10);
			}
			else{
				c.setName(""+i+"");
				c.setVlaue(i);
			}
			mDeck.add(c);
			size++;
		}
	}
	private void createDiamonds(){
		for(int i =1 ; i<14 ; i++){
			c = new Card();
			c.setSuit("Diamonds");
			if(i == 1){
				c.setName("ACE");
				c.setVlaue(i);
			}
			else if(i == 11){
				c.setName("J");
				c.setVlaue(10);
			}
			else if(i == 12){
				c.setName("Q");
				c.setVlaue(10);
			}
			else if(i == 13){
				c.setName("K");
				c.setVlaue(10);
			}
			else{
				c.setName(""+i+"");
				c.setVlaue(i);
			}
			mDeck.add(c);
			size++;
		}
	}
	private void createSpades(){
		for(int i =1 ; i<14 ; i++){
			c = new Card();
			c.setSuit("Spades");
			if(i == 1){
				c.setName("ACE");
				c.setVlaue(i);
			}
			else if(i == 11){
				c.setName("J");
				c.setVlaue(10);
			}
			else if(i == 12){
				c.setName("Q");
				c.setVlaue(10);
			}
			else if(i == 13){
				c.setName("K");
				c.setVlaue(10);
			}
			else{
				c.setName(""+i+"");
				c.setVlaue(i);
			}
			mDeck.add(c);
			size++;
		}
	}
	private void createClovers(){
		for(int i =1 ; i<14 ; i++){
			c = new Card();
			c.setSuit("Clovers");
			if(i == 1){
				c.setName("ACE");
				c.setVlaue(i);
			}
			else if(i == 11){
				c.setName("J");
				c.setVlaue(10);
			}
			else if(i == 12){
				c.setName("Q");
				c.setVlaue(10);
			}
			else if(i == 13){
				c.setName("K");
				c.setVlaue(10);
			}
			else{
				c.setName(""+i+"");
				c.setVlaue(i);
			}
			mDeck.add(c);
			size++;
		}
	}
	
}
