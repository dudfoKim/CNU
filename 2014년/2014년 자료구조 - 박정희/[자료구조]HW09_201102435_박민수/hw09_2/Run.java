/*201102435 ¹Ú¹Î¼ö*/

package hw09_2;


public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {15,36,40,31,22,51,19,28,37,36};
		QuickSortbyIndex B = new QuickSortbyIndex(array,0,array.length);
		
		System.out.println("==========QuickSort By Index==========");
		
		System.out.println("[ AscendSorting ] ");
		System.out.println();
		
		System.out.println("[ Init ] ");
		for(int i = 0;i<array.length;i++){
			System.out.print("["+i+"]");
		}
		System.out.println();
		for(int i = 0;i<array.length;i++){
			System.out.print(" "+array[i]);
		}
		System.out.println();
		System.out.println("[ QuickSort By Index ] ");
		B.startSorting('a');
		for(int i = 0;i<array.length;i++){
			System.out.print("["+B.index[i]+"]");
		}
		System.out.println();
		for(int i = 0;i<array.length;i++){
			System.out.print(" "+array[B.index[i]]);
		}
		System.out.println();
		System.out.println();
		
		System.out.println("[ DescendSorting ] ");
		System.out.println();
		System.out.println("[ Init ] ");
		for(int i = 0;i<array.length;i++){
			System.out.print("["+i+"]");
		}
		System.out.println();
		for(int i = 0;i<array.length;i++){
			System.out.print(" "+array[i]);
		}
		System.out.println();
		System.out.println("[ QuickSort By Index ] ");
		B.startSorting('b');
		for(int i = 0;i<array.length;i++){
			System.out.print("["+B.index[i]+"]");
		}
		System.out.println();
		for(int i = 0;i<array.length;i++){
			System.out.print(" "+array[B.index[i]]);
		}



	}

}
