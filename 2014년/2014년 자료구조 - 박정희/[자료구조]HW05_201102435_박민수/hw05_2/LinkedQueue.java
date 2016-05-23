/*201102435 ¹Ú¹Î¼ö*/

package hw05_2;

public class LinkedQueue {
	int size = 0;
	Node front  = null;
	Node rear = null;
	
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
			temp.next = new Node(a,temp);
			this.rear = temp.next;
		}
		this.size++;
	}
	public Node Remove(){
		Node temp = this.front;
		if(this.front.next == null)
		{			
			this.front = null;
		}
		else{
			this.front.next.prev = null;
			this.front = this.front.next;
		}
		this.size--;
		return temp;
		
	}
	public int size(){
		return this.size;
	}
	public void PrintQueue(){
		System.out.print("Print Que > ");
		Node temp = this.front;
		if(this.front == null)
		{
			System.out.println("The Queue is Empty! ");
		}
		while(temp!= null){
			System.out.print(temp.item+" ");
			temp = temp.next;
		}
	}
	
	public static class Node{
		String item;
		Node next;
		Node prev;
		Node(String item){
			this.item = item;
			this.next = null;
			this.prev = null;
		}
		Node(String item, Node prev){
			this.item = item;
			this.next = null;
			this.prev = prev;
			}
		} 

}
