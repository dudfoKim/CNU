/*201102435 ¹Ú¹Î¼ö*/

package hw07;
 
public class Factorial {
 
	public static long Facto(int n){
		if(n==0) return 1;
		else return n*Facto(n-1);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Factorial(1) = "+Facto(1));
		System.out.println("Factorial(2) = "+Facto(2));
		System.out.println("Factorial(3) = "+Facto(3));
		System.out.println("Factorial(4) = "+Facto(4));
		System.out.println("Factorial(5) = "+Facto(5));
		System.out.println("Factorial(6) = "+Facto(6));
		
	}
 
	
}