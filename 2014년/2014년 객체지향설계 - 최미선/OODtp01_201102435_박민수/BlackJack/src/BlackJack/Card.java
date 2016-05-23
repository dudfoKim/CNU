package BlackJack;

public class Card {
	private int vlaue;
	private String name;
	private String suit;
	
	//value getter & setter
	public int getVlaue() {
		return vlaue;
	}
	public void setVlaue(int vlaue) {
		this.vlaue = vlaue;
	}
	
	//name getter & setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//Suit getter & setter
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	public String toString(){
		return suit+"/"+name;
	}
}
