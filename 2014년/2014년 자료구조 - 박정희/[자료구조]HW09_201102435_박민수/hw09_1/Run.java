/*201102435 박민수*/

package hw09_1;

public class Run {

	public static void main(String[] args) {
		int[] array = {15,36,40,31,22,51,19,28,37,36};
		QuickSort B = new QuickSort(array,0,array.length);
		
		System.out.println("==========QuickSort==========");
		
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
