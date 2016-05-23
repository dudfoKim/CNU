/*201102435 ¹Ú¹Î¼ö*/


package hw13;


public class BST_Node {

	int value;
	BST_Node left,right,parent;
	
	
	public BST_Node(int item) {
		this.value = item;
		this.left = null;
		this.right = null;
		this.parent = null;
	}

	public BST_Node(int item, BST_Node par) {
		this.value = item;
		this.left = null;
		this.right = null;
		this.parent = par;
	}
}
