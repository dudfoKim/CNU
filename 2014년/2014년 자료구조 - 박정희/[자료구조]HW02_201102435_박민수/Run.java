/****************************
 * 학번 : 201102435 이름 : 박민수   *
 * **************************/
package hw02_1;
 
public class Run {
 
	public static void main(String[] args) {
		
		System.out.println("A1 리스트 생성 ");
		LinkedList A1 = new LinkedList();
		
		System.out.println("A1 리스트 출력 : ");
		A1.printList();
		
		System.out.println("A1 리스트 입력 ");
		A1.insert(13);
		A1.insert(23);
		A1.insert(3);
		A1.insert(4);
		A1.insert(33);
		
		System.out.println("A1 리스트 출력 : ");
		A1.printList();
		
		System.out.println("\nA1 리스트 갯수 : " +A1.getSize());
		System.out.println("A1 리스트 중 33을 가진 노드의 갯수: " +A1.countNodebyitem(33));
		System.out.println("A1 리스트 중 4 -> 7로 변경 ");
		A1.replace(4, 7);
		System.out.println("A1 리스트 출력 : ");
		A1.printList();
		
		System.out.println("\nA1 리스트중 3노드 삭제 ");
		A1.delete(3);
		
		System.out.println("A1 리스트 출력 : ");
		A1.printList();
		
		System.out.println("\nA2 리스트 생성 ");
		LinkedList A2 = new LinkedList();
		
		System.out.println("A2 리스트에 A1리스트의 모든노드 삽입 ");
		A2.insertList(A1);
		
		System.out.println("A1 리스트 출력 : ");
		A1.printList();
		System.out.println("\nA2 리스트 출력 : ");
		A2.printList();
		
		System.out.println("\nA3 리스트 생성 ");
		LinkedList A3 = new LinkedList();
		System.out.println("A3 리스트 입력 ");
		A3.insert(11);
		A3.insert(9);
		A3.insert(15);
		A3.insert(27);
		A3.insert(68);
		System.out.println("A3 리스트 출력 : ");
		A3.printList();
		
		System.out.println("\nA4 리스트 생성 ");
		LinkedList A4 = new LinkedList();
		
		System.out.println("A4 리스트에 A1을 Append ");
		A4.append(A1);
		System.out.println("A4 리스트 출력 : ");
		A4.printList();
		
		System.out.println("\nA1 리스트중 33노드 삭제 ");
		A1.delete(33);
		System.out.println("A1 리스트 출력 : ");
		A1.printList();
		System.out.println("\nA4 리스트 출력 : ");
		A4.printList();
		System.out.println("\nA2 리스트 출력 : ");
		A2.printList();
		
		
		
		System.out.println("\n A1과 A3 merged ");
	
		LinkedList.merged(A1, A3);
		System.out.println("A1과 A3 merged 리스트 출력 : ");
		A1.printList();
		
		
	}
 
}