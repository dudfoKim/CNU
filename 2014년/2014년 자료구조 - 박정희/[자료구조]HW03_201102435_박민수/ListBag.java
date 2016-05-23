/*학번 201102435
    이름 박민수*/

package hw02_3;

public class ListBag {
	
		Node top;
		int size;
		ListBag(){
			this.top = null;
			this.size = 0;
		}
		//Bag에 새로운 Coin을 삽입
		public void Add(Coin coin){
			//Coin의 값은 10,50,100,500,1000만 입력받는다.
			if(coin.item == 10 ||coin.item == 50 ||coin.item == 100 ||coin.item == 500 ||coin.item == 1000 )
			{
				//Bag이 비어있을때
				if(this.size == 0){
					this.top =new Node(coin);
				}
				//Bag이 비어있지않을대
				else
				{
					Node temp = this.top;
					while(temp.next != null){
						temp = temp.next;
					}
					temp.next = new Node(coin);
				}
				this.size++;
			}
			//잘못된 Coin값을 입력받았을시
			else
				System.out.println("잘못된 급액을 입력하셨습니다");
		}
		//Bag에서 파라미터로 입력 받은 코인을 삭제(없는 경우에 하지않음)
		public void removeAt(Coin coin){
			Node cNode = this.top;
			Node pNode = null;
			if(this.size == 0) System.out.println("이 Bag은 비어있습니다");
			else{
				while(true){
					if(cNode.item.getCoin() == coin.item){
						if(cNode == this.top)
							this.top = this.top.next;
						else if(cNode.next == null)
							pNode.next=null;
						else
							pNode.next = cNode.next;
						this.size--;
						break;
					}
					if(cNode.next == null) break;
					pNode = cNode;
					cNode = cNode.next;
				}
			}
		}
		//Bag에서 파라미터로 입력받은 코인을 모두 삭제(중복된 동전 삭제)
		public void remove(Coin coin){
			Node cNode = this.top;
			Node pNode = null;
			if(this.size == 0) System.out.println("이 Bag은 비어있습니다");
			else{
				while(true){
					if(cNode.item.getCoin( ) == coin.item){
						if(cNode == this.top)
							this.top = this.top.next;
						else if(cNode.next == null)
							pNode.next=null;
						else
							pNode.next = cNode.next;
						this.size--;
					}
					if(cNode.next == null) break;
					pNode = cNode;
					cNode = cNode.next;
				}
			}
		}
		//Bag에서 모든 Coin 객체를 삭제
		public void removeAll(){
			this.top = null;
			this.size = 0;
		}
		//Bag에 해당 coin이 있는지 확인
		public boolean isExist(Coin coin){
			Node serch = new Node(this.top.item,this.top.next);
			boolean exist = false;
			//Bag이 비어있으면
			if(this.size == 0) return exist;
			while(true)
			{
				//찾은 값이있으면 true값을 넣어줌
				if(serch.item.getCoin() == coin.item){
					exist = true;
					break;
				}
				if(serch.next == null) break;
				serch = serch.next;
			}
			return exist;
		}
		//Bag에 해당 coin이 몇개 있는지 출력
		public void numOfCoin(Coin coin){
			Node serch = new Node(this.top.item,this.top.next);
			int num = 0;
			
			while(true){
				if(serch.item.getCoin() == coin.item){
					num++;
				}
				if(serch.next == null) break;
				serch = serch.next;
			}
			System.out.println(coin.getCoin()+"코인은 "+num+"개 있습니다.");
		}
		//Bag에 있는 coin을 출력
		public void printBag(){
			if(this.size == 0) System.out.println("이 Bag은 비어있습니다.");
			else
			{
				Node serch = new Node(this.top.item, this.top.next);
				int cnt_10 =0;
				int cnt_50 =0;
				int cnt_100 =0;
				int cnt_500 =0;
				int cnt_1000 =0;
				
				while(true)
				{
					if(serch.item.getCoin() == 10) cnt_10++;
					else if(serch.item.getCoin() == 50) cnt_50++;
					else if(serch.item.getCoin() == 100) cnt_100++;
					else if(serch.item.getCoin() == 500) cnt_500++;
					else if(serch.item.getCoin() == 1000) cnt_1000++;
					
					if(serch.next == null) break;
					serch = serch.next;
				}
				System.out.println("10코인 : "+cnt_10+"\n50코인 : "+cnt_50+"\n100코인 : "+cnt_100+"\n500코인 : "+cnt_500+"\n1000코인 : "+cnt_1000);
			}
		}
		public class Node {
			Coin item;
			Node next;
			
			Node(int item)
			{
				this.item = new Coin(item);
				this.next = null;
			}
			Node(Coin coin)
			{
				this.item = coin;
				next = null;
			}
			Node(Coin item, Node next)
			{
				this.item = item;
				this.next = next;
			}
		}
}
