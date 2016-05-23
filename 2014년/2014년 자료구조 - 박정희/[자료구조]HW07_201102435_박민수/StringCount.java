/*201102435 ¹Ú¹Î¼ö*/

package hw07;
 
public class StringCount {
 
	public static int stringCount(String a,String b){
		int cnt = 0;
		String temp ;
		if(a.length()>=b.length()){
			temp = a.substring(0, b.length());
			if(temp.equals(b))
			{
				cnt = 1 + stringCount(a.substring(b.length()),b);
			}
			else
			{
				cnt = stringCount(a.substring(1),b);
			}
		}
		return cnt;	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("stirngCount(applexapple,apple) = " +stringCount("applexapple","apple"));
		System.out.println("stirngCount(iiixii,i) = " +stringCount("iiixii","i"));
		System.out.println("stirngCount(GAMEgameGAME,GAME) = " +stringCount("GAMEgameGAME","GAME"));
		System.out.println("stirngCount(GAMEgameGAME,game) = " +stringCount("GAMEgameGAME","game"));
 
	}
 
}