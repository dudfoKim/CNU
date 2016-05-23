package Ds_HW07;


public class AVLTree {
	private String key;
	int value,height,count;
	private AVLTree left, right;
	
	public static final AVLTree NIL = new AVLTree();
	
	public AVLTree(String key){
		this.key = key;
		this.value = 1;
		left = right = NIL;
	}
	
	public boolean add(String key){
		int oldSize = size();
		grow(key);
		return size()>oldSize;
	}
	
	public AVLTree grow(String key){
		count++;
		if (this == NIL) return new AVLTree(key);
		if (this.key.equals(key)) {
			this.value++;
			return this;
		}
		if (this.key.compareTo(key) >0) left = left.grow(key);
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
	
	private AVLTree(String key, AVLTree left,AVLTree right,int value){
		this.key = key;
		this.value = value;
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
		left = new AVLTree(key, left, right.left,value);
		key = right.key;
		value = right.value;
		right = right.right;
	}
	private void rotateRight(){
		right = new AVLTree(key, left.right, right,value);
		key = left.key;
		value = left.value;
		left = left.left;
	}
	
	// ÁßÀ§ Å½»ö
	public void inorder(){
		if(this.left !=  NIL){
			this.left.inorder();
		}
		System.out.println(this.key+": "+this.value);
		if(this.right !=  NIL){
			this.right.inorder();
		}
		
	}
}