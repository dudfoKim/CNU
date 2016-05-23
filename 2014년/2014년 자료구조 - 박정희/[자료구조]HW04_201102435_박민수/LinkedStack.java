/*학번 201102435
    이름 박민수*/

package hw04_3;

public class LinkedStack {
	Node top;
	int size = 0;
	
	LinkedStack(){
		
	}
	
	LinkedStack(Node item){
		this.top = item;
	}
	
	public void push(String item){
		top = new Node(item,top);
		++size;
	}
	
	public Object pop(){
		Object temp;
		temp=top.item;
		this.top = this.top.next;
		size--;
		return temp;
	}
	
	public int size(){
		return size;
	}
	
	public static class Node{
		String item;
		Node next;
		
		Node(String item,Node next){
			this.item = item;
			this.next = next;
		}
		
	}
		
}
