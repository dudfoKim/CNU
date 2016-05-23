/*201102435 박민수*/
 
package hw11_2;
 
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
	//pre-orde를 실행하는 메소드
	public void preOrder(){
		
 		if(this.root != null){
 			System.out.print(this.root+ " ");
 			if(this.left != null)
 				this.left.preOrder();
 			if(this.right != null)
 				this.right.preOrder();
		}
	
		else
			return ;
	}
	//post-order를 실행하는 메소드
	public void postOrder(){
		
 		if(this.root != null){
 			if(this.left != null)
 				this.left.postOrder();
 			if(this.right != null)
 				this.right.postOrder();
 			System.out.print(this.root+ " ");
 			
		}
	
		else
			return ;
 		
	}
	//in-order를 실행하는 메소드
	public void inOrder(){
		if(this.root != null){
 			if(this.left != null)
 				this.left.inOrder();
 			System.out.print(this.root+ " ");
 			if(this.right != null)
 				this.right.inOrder();
 			
 			
		}
	
		else
			return ;
	}
 
 
}