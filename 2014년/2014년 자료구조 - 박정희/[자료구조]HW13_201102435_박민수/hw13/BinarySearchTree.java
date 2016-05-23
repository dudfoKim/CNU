/*201102435 박민수*/


package hw13;



public class BinarySearchTree {

	BST_Node root;

	public BinarySearchTree() {

	}

	public BinarySearchTree(int item) {
		this.root = new BST_Node(item);
	}

	//트리를 출력하는 메소드
	public void treePrint(BST_Node tree){
		if(root == null) System.out.println("This tree is Empty!");
		
		if(tree == null) return ;
	
		System.out.print("(");
		
		
		if(tree.left == null && tree.right != null)
		{
			System.out.print("null ");
		}
		
		treePrint(tree.left);
		
		
		if(tree.left!=null && tree.right == null) System.out.print(", ");
		else if(tree.left==null && tree.right != null) System.out.print(", ");
		else if(tree.left!=null && tree.right == null) System.out.print(", ");
		else if(tree.left!=null && tree.right != null) System.out.print(", ");
		
		System.out.print(tree.value);
		
		if(tree.left!=null && tree.right == null) System.out.print(", ");
		else if(tree.left==null && tree.right != null) System.out.print(", ");
		else if(tree.left!=null && tree.right == null) System.out.print(", ");
		else if(tree.left!=null && tree.right != null) System.out.print(", ");
		
		
		if(tree.left != null && tree.right == null)
		{
			System.out.print(" null");
		}
		
		treePrint(tree.right);
		
		
		System.out.print(")");
	}
	// 트리를 생성하는 메소드
	public void Add(int item) {
		BST_Node temp;
		temp = root;

		while (true) {
			if (this.root == null) {
				this.root = new BST_Node(item);
				break;
			} else if (temp.value < item) {

				if (temp.right != null)
					temp = temp.right;
				else {
					temp.right = new BST_Node(item,temp);
					break;
				}
			} else if (temp.value > item) {

				if (temp.left != null)
					temp = temp.left;
				else {
					temp.left = new BST_Node(item,temp);
					break;
				}
			} else {
				System.out.println("중복되는 값이 입력되었습니다.");
				break;
			}
		}
	}

	// 트리속에 특정값이 있는지 검색하는 메소드
	public boolean Search(int item) {

		BST_Node temp;
		temp = root;

		while (temp != null) {
			if (temp.value == item) {
				return true;
			}

			else if (temp.value > item)
				temp = temp.left;
			else if (temp.value < item)
				temp = temp.right;
		}
		return false;

	}

	//트리가 비어있는지 판단하는 메소드
	public boolean isEmpty(BST_Node tree)
	{
		if (tree == null) {
			return true;
		} else {
			return false;
		}
	}

	//전위탐색메소드
	public void preOrder(BST_Node tree)
	{
		if (isEmpty(tree) == true) {
			return;
		}

		System.out.print(tree.value + " ");
		preOrder(tree.left);
		preOrder(tree.right);
	}

	//중위탐색메소드
	public void inOrder(BST_Node tree) 
	{
		if (isEmpty(tree) == true) {
			return;
		}

		inOrder(tree.left);
		System.out.print(tree.value + " ");
		inOrder(tree.right);
	}

	//후위탐색메소드
	public void postOrder(BST_Node tree) 
	{
		if (isEmpty(tree) == true) {
			return;
		}

		postOrder(tree.left);
		postOrder(tree.right);
		System.out.print(tree.value + " ");
	}
	//최소값을 찾는 메소드
	public int get_min(){
		BST_Node temp;
		temp = root;
		int min = 0;
		
		while(temp.left != null)
			temp = temp.left;
		
		min = temp.value;
		
		return min;
	}
	//최대값을 찾는 메소드
	public int get_max(){
		BST_Node temp;
		temp = root;
		int max = 0;
		
		while(temp.right != null)
			temp = temp.right;
		
		max = temp.value;
		
		return max;
	}
	//특정값을 가지는 트리를 삭제하는 메소드
	public void Delete(int item){
		BST_Node search,smallestInRightside;
		search = root;
		
		//반복문을 통해 삭제할 값을 찾는다.
		while (search != null) {
			if (search.value == item) {
				break;
			}

			else if (search.value > item)
				search = search.left;
			else if (search.value < item)
				search = search.right;
		}
		
		//삭제할값이 없으면 메소드를 종료한다.
		if(search == null){
			System.out.println("찾는 값은 트리에 존재하지 않습니다.");
			return ;
		}
		
		//삭제할 값의 좌우 트리가 존재하지 않을때
		if(search.left == null && search.right == null){
			//삭제할 값이 루트와 같으면 루트를 null만들고 종료한다.
			if(search == root){
				root = null;
				return ;
			}
			if(search.parent.left == search){
				search.parent.left = null;
			}
			else
				search.parent.right = null;
		}
		//삭제할 값의 오른쪽 트리만 존재할시
		else if(search.left == null && search.right != null){
			
			//삭제할 값이 루트값과 같을경우 루트의 오른쪽과 바꾼후 종료.
			if(search == root){
				root = root.right;
				return ;
			}
			if(search.parent.left == search){
				search.parent.left = search.right;
				search.right.parent = search.parent;
			}
			else{
				search.parent.right = search.right;
				search.right.parent = search.parent;
			}
		}
		//삭제할 값의 왼쪽 트리만 존재할시
		else if(search.right == null && search.left != null){
			
			//찾는값이 루트값과 같을경우 루트의 왼쪽과 바꾼후 종료.
			if(search == root){
				root = root.left;
				return ;
			}
			if(search.parent.left == search){
				search.parent.left = search.left;
				search.left.parent = search.parent;
			}
			else{
				search.parent.right = search.left;
				search.left.parent = search.parent;
			}
		}
		//삭제할 값이 좌우트리가 둘다 존재할시
		else{
			
			//삭제할 값의 오른쪽의 최소값을 찾는다.
			smallestInRightside = search.right;
			
			while(smallestInRightside != null){
				if(smallestInRightside.left == null) break;
				smallestInRightside = smallestInRightside.left;
			}
			
			//삭제할값을 삭제할값의 오른쪽의 최소값과 바꾸어준다.
			search.value = smallestInRightside.value;
			
			if(smallestInRightside.parent.left == smallestInRightside)
				smallestInRightside.parent.left = smallestInRightside.right;
			else
				smallestInRightside.parent.right = smallestInRightside.right;
			
		}
	}

}
