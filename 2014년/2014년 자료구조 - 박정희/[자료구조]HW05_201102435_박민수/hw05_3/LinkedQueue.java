/*201102435 ¹Ú¹Î¼ö*/

package hw05_3;



public class LinkedQueue {

	int size = 0;
	Node head = new Node(null);
	
	public void Add(String a){
		if(head.next == null){
			Node temp =new Node(a,head);
			temp.next = head;
			head.next = temp;
			head.prev = temp;
		}
		else
		{
			Node temp = head.next;
			while(temp.next != head)
			{
				temp = temp.next;
			}
			temp.next = new Node(a,temp);
			temp.next.next = head;
			head.prev = temp.next;
		}
		this.size++;
	}
	public Node Remove(){
		Node temp = this.head.next;
		if(this.head.next.next == head){
			this.head.next = null;
			this.head.prev = null;
		}
		else
		{
			this.head.next = this.head.next.next;
			this.head.next.prev = this.head;
		}
		this.size--;
		return temp;
	}
	public int size(){
		return this.size;
	}
	public void PrintQueue(){
		System.out.print("Print Que > ");
		Node temp = this.head;
		if(this.size == 0)
		{
			System.out.println("The Queue is Empty! ");
		}
		while(temp.next != head&&this.size!=0)
		{
			System.out.print(temp.next.item+" ");
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
