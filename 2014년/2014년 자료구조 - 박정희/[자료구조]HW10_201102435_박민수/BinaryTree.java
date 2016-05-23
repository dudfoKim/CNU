/*201102435	박민수*/

package hw10;

public class BinaryTree {

	String root = null;
	BinaryTree left,right;
	
	public BinaryTree(String value)
	{
		this.setRoot(value);
	}
	public BinaryTree(String value, BinaryTree left, BinaryTree right)
	{
		this.setRoot(value);
		this.setRingt(right);
		this.setLeft(left);
		
	}
	public BinaryTree(BinaryTree tree){
		this.root = tree.getRoot();
		this.right = tree.getRingt();
		this.left = tree.getLeft();
	}
	
	//Gettter & Setter
	public String getRoot(){
		return this.root;
	}
	public void setRoot(String data){
		this.root = data;
	}
	public BinaryTree getLeft(){
		return this.left;
	}
	public void setLeft(BinaryTree data){
		this.left = data;
	}
	public BinaryTree getRingt(){
		return this.right;
	}
	public void setRingt(BinaryTree data){
		this.right = data;
	}
	
	//트리를 출력하는 메소드
	public void ToString(){
		System.out.print("(");
		if(this.left != null)
			this.left.ToString();
		if(this.left != null && this.right != null){
			System.out.print(", ");
			System.out.print(this.root);
			System.out.print(", ");
		}
		else
			System.out.print(this.root);
		if(this.right != null)
			this.right.ToString();
		System.out.print(")");
		
	}
	//트리에 특정문자열이 몇개있는지 반환하는 메소드
	public int count(String data){
		int cnt=0;
		if(this.left == null && this.right==null){
			if(this.root.equals(data))
				return 1;
			else
				return 0;
		}
		if(this.root.equals(data))
			cnt = 1+this.left.count(data)+this.right.count(data);
		else
			cnt = this.left.count(data)+this.right.count(data);
		return cnt;
	}
	//트리가 full인지 아닌지를 반환하는 메소드
	public boolean isFull(){
		//이진 트리의 포화일때 크기 n = 2^(height+1)-1
		if(this.size() == this.Bsquare(this.height()+1)-1)
			return true;
		else
			return false;
	}
	// 트리의 leaf노드 수를 반환하는 메소드
	public int numLeaves(BinaryTree tree){
		int num = 0;
		if(tree.left == null && tree.right == null)
			return 1;
		num = numLeaves(tree.left)+numLeaves(tree.right);
		
		return num;

	}
	//트리의 높이를 반환하는 메소드
	public int height(){
		
		int height = 0;
		int leftH = 0, rightH = 0;
		if(this.left != null) leftH = this.left.height()+1;		// 왼쪽의 height측정
		if(this.right != null) rightH = this.right.height()+1;	// 오른쪽의 height측정
		height = Math.max(leftH, rightH);						// 둘중 큰값을 height값으로 가진다
		return height;	
		
	}
	//트리에 포함된 string의 수를 반환하는 메소드
	public int size(){
		
		int size = 1;

		if(this.left != null) size = size + this.left.size();
		if(this.right != null) size = size + this.right.size();		
		
		return size;
		
	}
	//트리가 leaf인지 아닌지를 반환하는 메소드
	public boolean isLeaf(){
		if(this.left == null && this.right == null) return true;
		else
			return false;
	}
	
	//이진트리의 포화상태를 판단할때 2^n을 계산하기위한  메소드
	public int Bsquare(int n){
		if(n == 1 ) return 2;
		return 2*Bsquare(n-1);
	}
	


}