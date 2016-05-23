package PLHW1_2;

public class RecursionLinkedList {
	private Node head;
	private static char UNDEF = Character.MIN_VALUE;
	
	/**
	* 새롭게 생성된 노드를 리스트의 처음으로 연결
	*/
	
	private void linkFirst(char element) {
		head = new Node(element, head);
	}
	
	/**
	* 과제 (1) 주어진 Node x 의 마지막으로 연결된 Node 의 다음으로 새롭게 생성된 노드를 연결
	*
	* @param element
	* 데이터
	* @param x
	* 노드
	*/
	private void linkLast(char element, Node x) {
		if (x.next == null) 
			x.next = new Node(element,null);//다음 원소로 연결
		else
			linkLast(element,x.next);//다음 노드 방문 recursion
	}
	
	/**
	* 이전 Node 의 다음 Node 로 새롭게 생성된 노드를 연결
	*
	* @param element
	* 원소
	* @param pred
	* 이전노드
	*/
	private void linkNext(char element, Node pred) {
		Node next = pred.next;
		pred.next = new Node(element, next);
	}
	/**
	* 리스트의 첫번째 원소 해제(삭제)
	*
	* @return 첫번째 원소의 데이터
	*/
	private char unlinkFirst() {
		Node x = head;
		char element = x.item;
		head = head.next;
		x.item = UNDEF;
		x.next = null;
		return element;
	}
	
	/**
	* 이전 Node 의 다음 Node 연결 해제(삭제)
	*
	* @param pred
	* 이전노드
	* @return 다음노드의 데이터
	*/
	private char unlinkNext(Node pred) {
		Node x = pred.next;
		Node next = x.next;
		char element = x.item;x.item = UNDEF;
		x.next = null;
		pred.next = next;
		return element;
	}
	
	/**
	* 과제 (2) x 노드에서 index 만큼 떨어진 Node 반환
	*/
	private Node node(int index, Node x) {
		// 채워서 사용, index 를 줄여가면서 다음 노드 방문
		if(index == 0) return x;
		return node(index-1, x.next);
	}
	
	/**
	* 과제 (3) 노드로부터 끝까지의 리스트의 노드 갯수 반환
	*/
	private int length(Node x) {
		// 채워서 사용, recusion 사용
		if(x.next == null) return 1;
		return 1+length(x.next);
	}
	
	/**
	* 과제 (4) 노드로부터 시작하는 리스트의 내용 반환
	*/
	private String toString(Node x) {
		// 채워서 사용, recusion 사용
		if(x.next == null) return x.item+"";
		return x.item+" "+toString(x.next);
	}
	
	/**
	* 현재 노드의 이전 노드부터 리스트의 끝까지를 거꾸로 만듬
	* ex)노드가 [s]->[t]->[r]일 때, reverse 실행 후 [r]->[t]->[s]
	* @param x
	* 현재 노드
	* @param pred
	* 현재노드의 이전 노드
	*/
	private void reverse(Node x, Node pred) {
		// 채워서 사용, recuison 사용
		if (x == head){
			Node temp = new Node(x.item,x.next);
			reverse(x.next,x);
			temp.next.next = new Node(temp.item,null);
		}
		else if (x.next != null) {    
			reverse(x.next,x);
			x.next = pred;
		}
		else
		{
			head.item = x.item;
			head.next = pred;
		}
	}
	
	/**
	* 원소를 리스트의 마지막에 추가
	*/
	public boolean add(char element) {
		if (head == null) {
			linkFirst(element);
		} else {
			linkLast(element, head);
		}
		return true;
	}
	
	/**
	* 원소를 주어진 index 위치에 추가
	*
	* @param index
	* 리스트에서 추가될 위치
	* @param element
	* 추가될 데이터
	*/
	public void add(int index, char element) {
		if (!(index >= 0 && index <= size()))
			throw new IndexOutOfBoundsException("" + index);
		if (index == 0)
			linkFirst(element);
		else
			linkNext(element, node(index - 1, head));
	}
	
	/**
	* 리스트에서 index 위치의 원소 반환
	*/
	public char get(int index) {
		if (!(index >= 0 && index < size()))
			throw new IndexOutOfBoundsException("" + index);
		return node(index, head).item;
	}
	
	/**
	* 리스트에서 index 위치의 원소 삭제
	*/
	public char remove(int index) {
		if (!(index >= 0 && index < size()))
			throw new IndexOutOfBoundsException("" + index);
		if (index == 0) {
			return unlinkFirst();
		}
		return unlinkNext(node(index - 1, head));
	}
	
	/**
	* 리스트를 거꾸로 만듬
	*/
	public void reverse() {
		reverse(head, null);
	}
	/**
	* 리스트의 원소 갯수 반환
	*/
	public int size() {
		return length(head);
	}
	
	public String toString() {
		if (head == null)
			return "[]";
		return "[" + toString(head) + "]";
	}
	
	/**
	* 리스트에 사용될 자료구조
	*/
	private static class Node {
		char item;
		Node next;
		Node(char element, Node next) {
			this.item = element;
			this.next = next;
		}
	}

}
