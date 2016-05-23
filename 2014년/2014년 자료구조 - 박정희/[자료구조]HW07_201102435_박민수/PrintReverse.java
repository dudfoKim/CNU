/*201102435 ¹Ú¹Î¼ö*/

package hw07;
 
public class PrintReverse {
 
	public static void printReverse(String a, int from){
		if(from< a.length()){
			printReverse(a,from+1);
			System.out.print(a.charAt(from));
		}
	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
 
		System.out.print("Print String : CNUCSE, Print Reverse : ");
		printReverse("CNUCSE",0);
		System.out.println();
		System.out.print("Print String : Hello World!, Print Reverse : ");
		printReverse("Hello World!",0);
	}
 
}