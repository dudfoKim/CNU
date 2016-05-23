/*201102435 박민수*/
 
package hw11_2;
 
public class Run {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BinaryTree treeC = new BinaryTree("C");
		BinaryTree treeE = new BinaryTree("E");
		BinaryTree treeD = new BinaryTree("D",treeC,treeE);
		BinaryTree treeA = new BinaryTree("A");
		BinaryTree treeB = new BinaryTree("B",treeA,treeD);
		BinaryTree treeG = new BinaryTree("G");
		BinaryTree treeF = new BinaryTree("F",treeB,treeG);
		treeG.right = new BinaryTree("I");
		treeG.right.left = new BinaryTree("H");
		

		
		//pre-order를 출력
		System.out.print("Preorder : ");
		treeF.preOrder();
		System.out.println();
		
		//post-order를 출력
		System.out.print("Postorder : ");
		treeF.postOrder();
		System.out.println();
		
		//in-order를 출력
		System.out.print("inorder : ");
		treeF.inOrder();
	
		
	}
 
}