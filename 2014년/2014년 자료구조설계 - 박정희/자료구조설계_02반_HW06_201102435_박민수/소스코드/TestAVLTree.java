package hw07;

public class TestAVLTree {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AVLTree a = new AVLTree(3);
		a.add(5);
		a.add(1);
		a.add(8);
		a.add(6);
		a.add(2);
		a.add(11);
		a.add(4);
		a.add(10);
		a.add(9);
		a.add(7);

		System.out.println("***** 중위순회 *****");
		a.inorder();
		
		System.out.println();
		
		a.delete(3);
		a.delete(5);
		a.delete(1);
		a.delete(8);
		a.delete(6);
		
		System.out.println("***** 3, 5, 1, 8, 6 을 삭제후 출력 *****");
		a.inorder();
	}

}
