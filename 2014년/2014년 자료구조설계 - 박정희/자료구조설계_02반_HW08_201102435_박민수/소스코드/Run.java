package HW08;

import java.util.Random;

public class Run {
	private static int LENGTH = 100000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array1 = new int[LENGTH];
		int[] array2 = new int[LENGTH];
		int[] array3 = new int[LENGTH];
	
		Random rand = new Random( );
		BubbleSort b;
		QuickSort q;
		
		CountSort  c;

		
		for(int i=0; i<array1.length; i++){
			array1[i] = rand.nextInt(1000);
		}
		System.arraycopy(array1, 0, array2, 0, array2.length);
		System.arraycopy(array1, 0, array3, 0, array3.length);
		
		
		b = new BubbleSort(array1);
		q = new QuickSort(array2,0,array2.length);
	
	
		long t1 = System.nanoTime( );
		b.startSorting('a');
		long t2 = System.nanoTime( );
		System.out.println("버블정렬");
		System.out.println(t2-t1);
		
		
		
		long t3 = System.nanoTime( );
		q.startSorting('a');
		long t4 = System.nanoTime( );
		System.out.println("퀵정렬");
		System.out.println(t4-t3);
	
			
		System.out.println("계수정렬");
		long t7 = System.nanoTime( );
		c = new CountSort(array3);
		long t8 = System.nanoTime( );
		System.out.println(t8-t7);
	
	}

}
