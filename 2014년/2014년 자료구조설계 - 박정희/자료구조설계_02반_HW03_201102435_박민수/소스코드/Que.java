package TopologicalSorting;


public class Que {
	int size = 0;
	Node front  = null;
	Node rear = null;
	
	//큐를 추가하는 메소드
	public void Add(int a){
		
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
	public int Remove(){
		int temp = this.front.item;
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
	
	
	public int size(){
		return this.size;
	}
	
	public static class Node{
		int item;
		Node next;
		Node(int item){
			this.item = item;
			this.next = null;
		}	
	} 

}
