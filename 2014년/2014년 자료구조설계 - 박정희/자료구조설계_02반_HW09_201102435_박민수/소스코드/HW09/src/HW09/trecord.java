package HW09;

public class trecord{
	trecord lchild;
	trecord rchild;
	char alphabet;
	String code;
	int freq;
	
	trecord(char alpha, int freq, trecord right, trecord left){
		this.alphabet = alpha;
		this.freq = freq;
		this.lchild = left;
		this.rchild = right;
		this.code = "";
	}
	
	trecord(){
		this.lchild = this.rchild = null;
		this.code = "";
	}
}