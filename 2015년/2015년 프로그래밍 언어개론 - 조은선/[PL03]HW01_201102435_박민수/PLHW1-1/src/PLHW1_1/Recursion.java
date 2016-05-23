package PLHW1_1;

public class Recursion {
	public static int factorial(int n){
		if(n==1)	return 1;
		return n*factorial(n-1);
	}
	
	public static int sum(int n){
		if(n==1) return 1;
		return n+sum(n-1);
	}
	
	public static int combination(int n, int r){
		if(n == r) return 1;
		if(r == 1) return n;
		return combination(n-1,r)+combination(n-1,r-1);
	}
	
	public static int fibonacci(int n){
		if(n == 1) return 1;
		else if (n == 2) return 1;
		return fibonacci(n-1)+fibonacci(n-2);
	}
	public static void main(String[] args) {
		System.out.println(factorial(10));
		System.out.println(sum(100));
		System.out.println(combination(10,3));
		System.out.println(fibonacci(10));
	}
}
