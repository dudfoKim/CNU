/*201102435 박민수*/

package hw08_2;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {5,4,3,2,1,9,8,7,6};
		InsertionSort B = new InsertionSort(array);
		
		System.out.println("==========삽입정렬==========");
		
		System.out.print("정렬에 사용할 데이터 : ");
		for(int i = 0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
		
		System.out.print("\n오름 차순 정렬 : ");
		B.startSorting('a');
		for(int i = 0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
		
		System.out.print("\n내림 차순 정렬 : ");
		B.startSorting('b');
		for(int i = 0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}

}