/*201102435 박민수*/

package hw07;

public class ArrayCount {
	public static int arrayCount(int[] a,int find,int from){
		int cnt = 0;
		if(from< a.length){
			int temp = a[from];
			if(temp == find)
				cnt = 1+arrayCount(a,find,from+1);
			else
				cnt = arrayCount(a,find,from+1);
		}

		return cnt;	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {1,2,3,3,4,5};
		
		System.out.println("배열 {1,2,3,3,4,5}에서 3의 개수는 "+arrayCount(array,3,0)+"개 입니다.");
		System.out.println("배열 {1,2,3,3,4,5}에서 2의 개수는 "+arrayCount(array,2,0)+"개 입니다.");
		System.out.println("배열 {1,2,3,3,4,5}에서 0의 개수는 "+arrayCount(array,0,0)+"개 입니다.");
	}

}
