#include <stdio.h>
#include <stdlib.h>

void merge_sort(int* data, int start, int end,int size);

void main() {
	FILE* fp;
	FILE* fop;
	int* data = (int*)malloc(sizeof(int));
	int cnt = 0;
	int i=0;
	int j=1;
	int key=0;

	fp = fopen("data02.txt","rt");
	fop = fopen("00_201102435_merge.txt","wt");

	if(fp == NULL)
	{
		printf("**** Input File open Error **** \n");
		exit(1);
	}

	while(!feof(fp)){
		data= (int*)realloc(data, sizeof(int)*(cnt+1));
		fscanf(fp, "%d, ", &data[cnt++]);
	}

	merge_sort(data,0,cnt-1,cnt);

	for(i=0;i<cnt; i++){
		fprintf(fop,"%d, ", data[i]);
	}

	fclose(fp);
	fclose(fop);
}	
//merger sort 알고리즘.
void merge_sort(int* data, int start, int end,int size) {
	int mid = (start+end) / 2;	
	int i = start; 
	int j = mid +1;
	int k = 0;
	int n = 0;
	int m = start;

	int* tmp = (int*)malloc(sizeof(int)*size);
	

	if(size != 1){

		merge_sort(data,start,mid,(mid -start+1));

		merge_sort(data,mid+1,end,(end-mid));

		while(i<=mid && j<=end){
			if(data[i] < data[j])
				tmp[k++] = data[i++];
			else
				tmp[k++] = data[j++];
		};
		if(i<=mid){
			while(k<size){
				tmp[k++] = data[i++];
			}
		}
		else {
			while(k<size){
				tmp[k++] = data[j++];
			}
		}

		for(; m<=end; m++){
			data[m] = tmp[n++];
		}
	}
};