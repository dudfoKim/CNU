package HW08;

public class QuickSort {
	int[] array;
	int start ;
	int end;
	QuickSort(){
		
	}
	QuickSort(int[] array, int start, int end){
		this.array = array;
		this.start = start;
		this.end = end;
	}
	public void startSorting(char a){
		switch(a){
		case 'a': 
			Ascending(this.array, this.start, this.end);
			break;
		case 'b':
			Descending(this.array, this.start, this.end);
			break;
		
		}
	}
	//오름차순
	public void Ascending(int[] array,int start,int end){
		if(end - start <2) 
			return;
		int pivot = A_partition(array,start,end);
		Ascending(array,start,pivot);
		Ascending(array,pivot+1,end);
		
	}
	//내림차순
	public void Descending(int[] array,int start, int end){
		if(end - start <2) 
			return;
		int pivot = D_partition(array,start,end);
		Descending(array,start,pivot);
		Descending(array,pivot+1,end);
	}



	//오름차순
	public int A_partition(int[] array, int start, int end){
	
		int pivot = array[start];
		int i = start,j = end;
		
		while(i<j)
		{
			while(j > i && array[--j]>=pivot)
				;
			if(j>i) 
				array[i] = array [j];
			while(i<j && array[++i]<= pivot)
				;
			if(i<j) 
				array[j] = array[i];
		} 
		array[j] = pivot;
		
		return j;
	}
	//내림차순
	public int D_partition(int[] array, int start, int end){
		
		int pivot = array[start];
		int i = start,j = end;
		
		while(i<j)
		{
			while(j > i && array[--j]<=pivot)
				;
			if(j>i) 
				array[i] = array [j];
			while(i<j && array[++i]>= pivot)
				;
			if(i<j) 
				array[j] = array[i];
		} 
		array[j] = pivot;
		
		return j;
	}
}