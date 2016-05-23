package Question2;

public class LinkedQueue {
	int size = 0;
	Node front  = null;
	Node rear = null;
	
	//큐를 추가하는 메소드
	public void Add(String a){
		
		if(this.size == 0){
			this.front = new  Node(a);
			this.rear = this.front;
		}
		else{
			Node temp = this.front;
			while(temp.next != null)
			{
				temp = temp.next;
			}
			temp.next = new Node(a);
			this.rear = temp.next;
		}
		this.size++;
	}
	
	//큐를 삭제하는 메소드(삭제한 큐의 item값을 리턴)
	public String Remove(){
		String temp = this.front.item;
		if(this.front.next == null)
		{			
			this.front = null;
		}
		else{
			this.front = this.front.next;
		}
		this.size--;
		return temp;
	}
	
	//입력받은 String a와 일치하는 que의 item이 존재하는지 찾는 메소드(존재하면 true, 그렇지않으면 false)
	public boolean Search(String a){
		Node temp = this.front;
		for (; temp != null; temp = temp.next)
			if (temp.item.equals(a)) return true;
		return false;
	}
	public int size(){
		return this.size;
	}
	
	public static class Node{
		String item;
		Node next;
		Node(String item){
			this.item = item;
			this.next = null;
		}	
	} 
 
}