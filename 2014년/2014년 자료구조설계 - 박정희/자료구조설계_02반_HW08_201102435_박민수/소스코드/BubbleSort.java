package HW08;

public class BubbleSort {

	int[] array;
	BubbleSort(){
		
	}
	BubbleSort(int[] array){
		this.array = array;
	}
	public void startSorting(char a){
		switch(a){
		case 'a': 
			Ascending();
			break;
		case 'b':
			Descending();
			break;
		
		}
	}
	//오름차순
	public int[] Ascending(){
		for(int i = 0; i<array.length;i++)
		{
			for(int j = i+1; j<array.length;j++)
			{
				if(array[i]>array[j])
				{
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		return array;
	}
	//내림차순
	public int[] Descending(){
		for(int i = 0; i<array.length;i++)
		{
			for(int j = i+1; j<array.length;j++)
			{
				if(array[i]<array[j])
				{
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		return array;
	}
	
}