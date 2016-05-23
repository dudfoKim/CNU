/*201102435 ¹Ú¹Î¼ö*/

package hw07;
 
public class Fibonacci {
	
	public 
	static long Fib(int n){
		if(n==0) return 0;
		else if(n==1) return 1;
		else{
			return Fib(n-1)+Fib(n-2);
		}
 
 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Fibo(0) = "+Fib(0));
		System.out.println("Fibo(1) = "+Fib(1));
		System.out.println("Fibo(2) = "+Fib(2));
		System.out.println("Fibo(3) = "+Fib(3));
		System.out.println("Fibo(4) = "+Fib(4));
		System.out.println("Fibo(5) = "+Fib(5));
		System.out.println("Fibo(6) = "+Fib(6));
		System.out.println("Fibo(7) = "+Fib(7));
		System.out.println("Fibo(8) = "+Fib(8));
		System.out.println("Fibo(9) = "+Fib(9));
		System.out.println("Fibo(10) = "+Fib(10));
		System.out.println("Fibo(11) = "+Fib(11));
	
		
	}
 
}