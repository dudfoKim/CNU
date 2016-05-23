#include <stdio.h>
#include <stdlib.h>

int binarySearch(int a[], int item, int low, int high);

void main() {
	FILE* fp;
	FILE* fop;
	int* data = (int*)malloc(sizeof(int));
	int cnt = 0;
	int i=0;
	int j=1;
	int location = 0;
	int key=0;

	fp = fopen("data02.txt","rt");
	fop = fopen("00_201102435_InsertionWithBinarySearch.txt","wt");

	if(fp == NULL)
	{
		printf("**** Input File open Error **** \n");
		exit(1);
	}

	while(!feof(fp)){
		data= (int*)realloc(data, sizeof(int)*(cnt+1));
		fscanf(fp, "%d, ", &data[cnt++]);
	}
	// insertion sort 알고리즘 with Binary Search
	for(;j<cnt; j++){
		key = data[j];
		i = j-1;
		location = binarySearch(data,key,0,i); 
		while(i >= location){
			data[i+1] = data[i];
			i--;
		}
		data[i+1] = key;
	}
	for(i=0;i<cnt; i++){
		printf("%d ", data[i]);
	}
	for(i=0;i<cnt; i++){
		fprintf(fop,"%d, ", data[i]);
	}

	fclose(fp);
	fclose(fop);
}			
int binarySearch(int a[], int item, int low, int high)
{
	int mid = (low + high)/2;
	
	if (high <= low)
        return (item > a[low])?  (low + 1): low;
    if(item == a[mid])
        return mid+1;
    if(item > a[mid])
        return binarySearch(a, item, mid+1, high);
    return binarySearch(a, item, low, mid-1);
}