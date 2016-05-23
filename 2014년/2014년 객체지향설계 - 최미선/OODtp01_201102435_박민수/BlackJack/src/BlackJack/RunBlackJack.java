package BlackJack;

import java.util.Scanner;

public class RunBlackJack {

	private static Scanner sc;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Deck deck = new Deck();
		Dealer dealer = new Dealer();
		Player player = new Player();
		int select =0;
		sc = new Scanner(System.in);
		
		System.out.println("********** 게임을 시작 합니다. **********");
		
		System.out.println("카드를 두장씩 배분합니다.");
		dealer.getCard(deck.getNextCard());
		player.getCard(deck.getNextCard());
		dealer.getCard(deck.getNextCard());
		player.getCard(deck.getNextCard());
		dealer.getValueOfHand();
		player.getValueOfHand();
		
		dealer.showHand();
		System.out.println();
		player.showHand();
		System.out.println();
		System.out.println("플레이어의 핸드의 합 :"+ player.ValueOfHand());
		
		if(dealer.ValueOfHand() == 21 && player.ValueOfHand() == 21){
			dealer.finalshowHand();
			System.out.println();
			System.out.println("딜러의 핸드의 합 :"+ dealer.ValueOfHand());
			System.out.println("Dealder & Player are BlakJack!");
			System.out.println("무승부입니다!");
			return;
		}
		else if(dealer.ValueOfHand() == 21){
			dealer.finalshowHand();
			System.out.println();
			System.out.println("딜러의 핸드의 합 :"+ dealer.ValueOfHand());
			System.out.println("Dealder is BlakJack!");
			System.out.println("딜러가 승리하였습니다!");
			return;
		}
		else if(player.ValueOfHand() == 21){
			dealer.finalshowHand();
			System.out.println();
			System.out.println("딜러의 핸드의 합 :"+ dealer.ValueOfHand());
			System.out.println("Player is BlakJack!");
			System.out.println("플레이어가 승리하였습니다!");
			return;
		}
		
		while(true){
			if(player.ValueOfHand()<21){
				System.out.println("다음 진행할 행동을 선택합니다. (1. Stand 2.Hit)");
				select = sc.nextInt();
			}else{
				select = 1;
			}
			if (select == 1){
				System.out.println("[Stand]");
				System.out.println("딜러의 카드를 오픈합니다.");
				dealer.finalshowHand();
				System.out.println();
				while(dealer.ValueOfHand() < 17){
					System.out.println("딜러의 핸드의 합이 16이하여서 한장을 Hit합니다.");
					dealer.getCard(deck.getNextCard());
					dealer.getValueOfHand();
					dealer.finalshowHand();
					System.out.println();
				}
				player.showHand();
				System.out.println();
				
				System.out.println("플레이어의 핸드의 합 :"+ player.ValueOfHand());
				System.out.println("딜러의 핸드의 합 :"+ dealer.ValueOfHand());
				
				if(dealer.ValueOfHand()>21 && player.ValueOfHand()>21){
					System.out.println("Dealer & Player are Bust!");
					System.out.println("딜러가 승리했습니다!");
				}
				else if(dealer.ValueOfHand()>21 && player.ValueOfHand()<21){
					System.out.println("Dealer is Bust!");
					System.out.println("플레이어가 승리했습니다!");
				}
				else if(dealer.ValueOfHand()<21 && player.ValueOfHand()>21){
					System.out.println("Player is Bust!");
					System.out.println("딜러가 승리했습니다!");
				}
				else if(dealer.ValueOfHand() > player.ValueOfHand()){
					System.out.println("딜러가 승리했습니다!");
				}
				else if(dealer.ValueOfHand() < player.ValueOfHand()){
					System.out.println("플레이어가 승리했습니다!");
				}
				else{
					System.out.println("무승부입니다!");
				}
				return;		
			}
			if (select == 2){
				System.out.println("[HIT]");
				
				player.getCard(deck.getNextCard());
				player.getValueOfHand();
				
				dealer.showHand();
				System.out.println();
				player.showHand();
				System.out.println();
				System.out.println("플레이어의 핸드의 합 :"+ player.ValueOfHand());
			}
		}
	}

}
