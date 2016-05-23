/*201102435	¹Ú¹Î¼ö*/


package hw10;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree treeB = new BinaryTree("B");
		BinaryTree treeD = new BinaryTree("D");
		BinaryTree treeE = new BinaryTree("E");
		BinaryTree treeC = new BinaryTree("C",treeD,treeE);;
		BinaryTree treeA = new BinaryTree("A",treeB,treeC);
		
		treeA.ToString();
		System.out.println();
		System.out.println("isFull() : "+treeA.isFull());
		System.out.println("count(\"A\") : "+treeA.count("A"));
		System.out.println("numLeaves() : "+treeA.numLeaves(treeA));
		System.out.println("size() : "+treeA.size());
		System.out.println("height() : "+treeA.height());
		System.out.println();
		
		System.out.println("==============treeB¿¡ ÁÂ¿ì¿¡ Y¿Í AÃß°¡==============");
		treeB.left = new BinaryTree("Y");
		treeB.right = new BinaryTree("A");
		
		treeA.ToString();
		System.out.println();
		System.out.println("isFull() : "+treeA.isFull());
		System.out.println("count(\"A\") : "+treeA.count("A"));
		System.out.println("numLeaves() : "+treeA.numLeaves(treeA));
		System.out.println("size() : "+treeA.size());
		System.out.println("height() : "+treeA.height());
		
	}

}