package HW08;

public class CountSort {
	public CountSort(){
		
	}
	public CountSort(int[] a) {
		int n = a.length;
		int m = size(a); // the number of distinct values of a[]
		int[] c = new int[m]; // step 1
		for (int i = 0; i < n; i++)// step 2
			++c[a[i]];
		for (int k = 1; k < m; k++)// step 3
			c[k] += c[k - 1];
		int[] b = new int[n]; // step 4
		for (int i = n - 1; i >= 0; i--)// step 5
			b[--c[a[i]]] = a[i];
		System.arraycopy(b, 0, a, 0, n); // step 6
	}

	private int size(int[] array){
		int max = array[0];
		
		for(int i =0; i<array.length; i++){
			if (max < array[i])
				max = array[i];
		}
		return max+1;
	}
}
