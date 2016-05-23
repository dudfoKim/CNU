/*201102435 ¹Ú¹Î¼ö*/

package hw07;
 
public class SumDigits {
	
	public static long sumDigits(int n){
		if(n/10 == 0) return n;
		else return (n%10)+sumDigits(n/10);
	}
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("sumDigits(112) = "+sumDigits(112));
		System.out.println("sumDigits(72) = "+sumDigits(72));
		System.out.println("sumDigits(512) = "+sumDigits(512));
		System.out.println("sumDigits(1357) = "+sumDigits(1357));
		System.out.println("sumDigits(1150) = "+sumDigits(1150));
		System.out.println("sumDigits(34569) = "+sumDigits(34569));
 
	}
 
}