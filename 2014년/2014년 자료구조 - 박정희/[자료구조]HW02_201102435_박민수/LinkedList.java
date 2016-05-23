/****************************
 * 학번 : 201102435 이름 : 박민수   *
 * **************************/
 
package hw02_1;
 
public class LinkedList {
	
	Node head;
 
	public LinkedList(){
		
	}
	
	public LinkedList(int item){
		this.head = new Node(item);
	}
 /* 삽입메소드*/
	public void insert(int item)
	{
		if(isEmpty())			 		//리스트가 비어있을때 
			this.head = new Node(item);
		else{							//리스트가 비어있지 않을때
			Node node = this.head;
			while(node.next != null){
				node = node.next;
			}
			node.next = new Node(item);
		}
	}
	/*리스트에 다른리스트의 모든 노드를 삽입하는 메소드*/
	public void insertList(LinkedList list){
		Node node2 =new Node(list.head.item, list.head.next);
		
		while(true){
			if(this.head== null){
				this.head =new Node(node2.item);		
			}
			else{
				Node node = this.head;
				while(node.next != null){
					node = node.next;
				}
				node.next = new Node(node2.item);
			}
			if(node2.next == null) break;
			node2 = node2.next;

		}
	
		
	}
	/*리스트에 다른리스트를 붙이는 메소드*/
	public void append(LinkedList list){
		Node temp = this.head;
		if(temp == null)
			this.head = list.head;
		else{
			while(true){
				temp = temp.next;
				if(temp.next == null) break;
			}
			temp.next = list.head;
		}
	}
	/*리스트가 비어있는지 확인하는 메소드*/
	public boolean isEmpty(){
		if(this.head == null)			//리스트가 비어있을때
			return true;
		else									//리스트가 비어있지 않을때
			return false;
	}
	/*특정데이터를 갖는 노드가 있는지 찾는 메소드*/
	public Node findNodebyitem(int item){
		
		Node node = this.head;
		while(node.next != null){
			if(node.item == item)			//노드속에 있는 데이터와 특정데이터가 일치할때
				return node;
			else{									//노드속에 있는 데이터와 특정데이터가 일치하지 않을때
				node = node.next;
			}
		}
		return null;
		
	}
	/*특정데이터를 갖는 노드의 갯수를 세는 메소드*/
	public int countNodebyitem(int item){
		int count =0;
		Node node = this.head;
		while(node != null ){			
			if(node.item == item)				//노드속의 데어터와 특정데이터가 일치할때
				count++;
			node = node.next;
		}
		return count;
	}
	/*리스트에서 노드의 갯수를 세는 메소드*/
	public int getSize(){
		int count =0;
		Node node = this.head;
		while(node !=null){
			count++;
			node = node.next;
		}
		return count;
	}
	/*리스트에서 특정 노드를 삭제하는 메소드*/
	public void delete(int item){
		Node cNode = this.head;
		Node pNode = null;
		boolean D = false;
		while(true)
		{
			if(cNode.item == item)
			{
				D = true;
				break;
			}
			
			if(cNode.next == null)	break;
			pNode = cNode;
			cNode = cNode.next;
		}
		if(D)
		{
			if(cNode.item == this.head.item)
			{
				this.head.item = this.head.next.item;
				this.head.next = this.head.next.next;
			}
			else
			{
				pNode.next = cNode.next;
			}
			
		}
	}
	/*리스트에서 특정노드의 데이터를 다른데이터로 교환하는 메소드*/
	public void replace(int item1,int item2){
		Node node = this.head;
		while(node.next != null){
			if(node.item == item1)		//노드속에 있는 데이터와 특정데이터가 일치할때
				node.item = item2;		//노드속데이터를 다른데이터랑 교환
			node = node.next;
		}
	}
	/*리스트내 노드의 데이터를 출력하는 메소드*/
	public void printList(){
		Node node = this.head;
		if(isEmpty())
			System.out.println(" 이 리스트는 비어있습니다.");
		else{
			while(node != null)
				{
					System.out.print(" " +node.item);
					node = node.next;
				}
		}
	}
	/*다른 두 리스트를 사용하여 새로운 리스트를 만들어내는 메소드*/
	public static LinkedList merged(LinkedList list1,LinkedList list2){
		Node node = list1.head;
		while(node.next != null){
			node = node.next;
		}
		node.next = list2.head;
		return list1;
	}
}
class Node{
	
	int item;
	Node next;
	
	Node()
	{		
	}
	Node(int item)
	{
		this.item = item;
	}
	Node(int item, Node next)
	{
		this.item = item;
		this.next = next;
	}		
	
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
 
}