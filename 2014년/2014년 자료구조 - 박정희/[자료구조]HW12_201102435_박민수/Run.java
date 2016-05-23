/*201102435 ¹Ú¹Î¼ö*/

package hw12;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BinarySearchTree tree = new BinarySearchTree();
		
		tree.treePrint(tree.root);
		
		System.out.println("tree.Add(10)");
		tree.Add(10);
		tree.treePrint(tree.root);
		System.out.println();
		
		System.out.println("tree.Add(5)");
		tree.Add(5);
		tree.treePrint(tree.root);
		System.out.println();
		
		System.out.println("tree.Add(7)");
		tree.Add(7);
		tree.treePrint(tree.root);
		System.out.println();
		
		System.out.println("tree.Add(12)");
		tree.Add(12);
		tree.treePrint(tree.root);
		System.out.println();
		
		System.out.println("tree.Add(4)");
		tree.Add(4);
		tree.treePrint(tree.root);
		System.out.println();
		
		System.out.println("tree.Add(11)");
		tree.Add(11);
		tree.treePrint(tree.root);
		System.out.println();
		
		System.out.println("tree.Add(15)");
		tree.Add(15);
		tree.treePrint(tree.root);
		System.out.println();
		
		
		System.out.print("Search(10) : ");
		System.out.println(tree.Search(10));
		
		System.out.print("Search(17) : ");
		System.out.println(tree.Search(17));
		
		System.out.print("PreOrder : ");
		tree.preOrder(tree.root);
		System.out.println();
		
		System.out.print("InOrder : ");
		tree.inOrder(tree.root);
		System.out.println();
		
		System.out.print("PostOrder : ");
		tree.postOrder(tree.root);
	

	}

}
