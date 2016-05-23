package hw07;

public class AVLTree {
	private int key, height;
	private AVLTree left, right;
	
	public static final AVLTree NIL = new AVLTree();
	
	public AVLTree(int key){
		this.key = key;
		left = right = NIL;
	}
	
	public boolean add(int key){
		int oldSize = size();
		grow(key);
		return size()>oldSize;
	}
	
	public AVLTree grow(int key){
		if (this == NIL) return new AVLTree(key);
		if (key == this.key) return this;
		if (key < this.key) left = left.grow(key);
		else right = right.grow(key);
		rebalance();
		height = 1 + Math.max(left.height,right.height);
		return this;
	}
	
	public int size(){
		if(this == NIL) return 0;
		return 1 + left.size()+right.size();
	}
	
	public String toString(){
		if (this == NIL) return "";
		return left + " " +  key + " " + right;
	}
	
	private AVLTree(){
		left = right = this;
		height = -1;
	}
	
	private AVLTree(int key, AVLTree left,AVLTree right){
		this.key = key;
		this.left = left;
		this.right =right;
		height = 1 + Math.max(left.height, right.height);
	}
	
	private void rebalance(){
		if(right.height > left.height +1){
			if(right.left.height > right.right.height) right.rotateRight();
			rotateLeft();		
		}
		else if (left.height > right.height +1){
			if (left.right.height > left.left.height) left.rotateLeft();
			rotateRight();
		}
	}
	
	private void rotateLeft(){
		left = new AVLTree(key, left, right.left);
		key = right.key;
		right = right.right;
	}
	private void rotateRight(){
		right = new AVLTree(key, left.right, right);
		key = left.key;
		left = left.left;
	}
	
	// 중위 탐색
	public void inorder(){
		if(this.left !=  NIL){
			this.left.inorder();
		}
		System.out.print(" "+this.key+" ");
		if(this.right !=  NIL){
			this.right.inorder();
		}
		
	}
	
	// 키 값을 삭제하는 메소드.
	public void delete(int key){
		if (this == NIL) return ;
		//key 값이 루트에 존재할때.
		if (key == this.key){
			// 루트값이외에 값이 없을때, 즉 노드가 삭제된다.
			if (this.left == NIL && this.right == NIL){
				this.left = this.right = this;
				this.key = 0;
				this.height = -1;
			}
			// 라이트는  NIL이지만 레프트가 존재한다면 루트는 레프트가 된다.
			else if (this.left != NIL && this.right == NIL){
				this.key = this.left.key;
				this.height = this.left.height;
				this.right = this.left.right;
				this.left = this.left.left;
			}
			// 나머지의 경우 루트의 오른쪽의 가장 작은값이 루트값이 된다.
			else{
				AVLTree temp;
				temp = this.right;
				if(temp.left == NIL){
					this.key = temp.key;
					this.right = NIL;
					return;
				}
				while(temp.left.left != NIL){
					temp = temp.left;
				}
				this.key = temp.left.key;
				temp.left = NIL;
				temp.height = 1+ Math.max(temp.left.height, temp.right.height);
			}
			
		}
		// 키값이 루트의 키값보다 작을때
		if (key < this.key){
			// 레프트와 키값이 같을 때, 레프트가 리프 노드이면 left는 NIL이 된다.
			if(left.key == key && left.left == NIL && left.right == NIL){
				left = NIL;
			}
			else{
				left.delete(key);
			}
		}
		else{
			// 라이트와 키값이 같을때, 라이트가 리프노드이면 라이트는 NIL이 된다.
			if(right.key == key && right.left == NIL && right.right == NIL){
				right = NIL;
			}
			else {
				right.delete(key);
			}
		}
		rebalance();
		height = 1 + Math.max(left.height,right.height);
	
	}
}