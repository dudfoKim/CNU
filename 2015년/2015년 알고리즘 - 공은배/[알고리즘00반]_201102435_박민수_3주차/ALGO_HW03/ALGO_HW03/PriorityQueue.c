#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void insert(int** S, int x);
int H_max(int** S);
int extract_max(int** S);
void increase_key(int** S, int x,int k);
void H_delete(int** S, int x);
void Build_Max_Heap(int** S);
void complete_heap_sort(int** S);
void Max_heapify(int** S,int i);
int sizeof_ar(int* S);


void main() {
	FILE* fp;
	FILE* fop;
	int* data = (int*)malloc(sizeof(int));
	char* arr = (char*)malloc(sizeof(char));

	int i;
	int cnt = 0;

	fp = fopen("data03.txt","rt");
	fop = fopen("00_201102435_output.txt","wt");

	if(fp == NULL)
	{
		printf("**** Input File open Error **** \n");
		exit(1);
	}

	while(!feof(fp)){
		data= (int*)realloc(data, sizeof(int)*(cnt+1));
		arr= (char*)realloc(arr, sizeof(char)*(cnt+1));
		fscanf(fp, "%c.%d, ", &(arr[cnt]),&data[cnt]);
		cnt++;
	}

	
	for(i=0;i<cnt; i++){
		int result = data[i];
		int counter = 0;
		int k=0;
		int m;
		int* tmp = (int*)malloc(sizeof(int));
		int* reverce;

		while(result/10 != 0){
			tmp= (int*)realloc(tmp, sizeof(int)*(counter+1));
			tmp[counter] = result%10;
			result = result/10;
			counter++;
		}
		tmp= (int*)realloc(tmp, sizeof(int)*(counter+1));
		tmp[counter] = result%10;
		counter++;
		m = counter -1;
		reverce = (int*)malloc(sizeof(int)*counter);


		for(k=0;k<counter;k++){
			reverce[k] = tmp[m--];
		}
		
		free(tmp);
		// 들어온 알파벳에 따라 해당하는 함수를출력함.
		switch(arr[i]){
		case 'a':
			insert(&reverce,9);
			counter++;
			break;
		case 'b':
			H_max(&reverce);
			break;
		case 'c':
			extract_max(&reverce);
			counter--;
			break;
		case 'd':
			increase_key(&reverce,1,5);
			break;
		case 'e':
			H_delete(&reverce,5);
			counter--;
			break;
		case 'f':
			Build_Max_Heap(&reverce);
			break;
		case 'g':
			complete_heap_sort(&reverce);
			break;
		}
		for(k=0;k<counter;k++){
			fprintf(fop,"%d", reverce[k]);
		}
		fprintf(fop,", ");
		free(reverce);
	}
	free(data);
	free(arr);
	fclose(fp);
	fclose(fop);
}			

void insert(int** S, int x){
	int size = sizeof_ar(*S);
	*S =  (int*)realloc(*S, sizeof(int)*(size+1));
	(*S)[size] = x;
}
int H_max(int** S){
	Build_Max_Heap(S);
	return (*S)[0];
}
int extract_max(int** S){
	int result =0;
	int i=0;
	int size = sizeof_ar(*S);
	size -= 1;

	Build_Max_Heap(S);
	result = (*S)[0];
	(*S)[0] = (*S)[size];
	*S =  (int*)realloc(*S, sizeof(int)*(size));
	
	Build_Max_Heap(S);
	
	return result;
}
void increase_key(int** S, int x,int k){
	if((*S)[x-1] < k)
	{
		(*S)[x-1] = k;
	}
	Build_Max_Heap(S);
}
void H_delete(int** S, int x){
	int size = sizeof_ar(*S);
	int i = x-1;
	size = size-1;
	
	for(; i<size;i++){
		(*S)[i] = (*S)[i+1];
	}	
	*S =  (int*)realloc(*S, sizeof(int)*(size));
}
void Build_Max_Heap(int** S){
	int size = sizeof_ar(*S);
	int i=size/2;

	while(i>=0)
	{
		Max_heapify(S,i);
		i = i--;
	}
}
void complete_heap_sort(int** S){
	int size = sizeof_ar(*S);
	int* tmp = (int*)malloc(sizeof(int)*size);
	int i = 0;
	int j = 0;
	
	for(i=0;i<size;i++)
	{
		tmp[i] = (*S)[i];
	}
	while(size > 0){
		Build_Max_Heap(&tmp);
		size = size-1;
		(*S)[j] = tmp[0];
		tmp[0] = tmp[size];
		tmp =  (int*)realloc(tmp, sizeof(int)*(size));
		j++;
	}
	free(tmp);
}
void Max_heapify(int** S,int i){
	int size = sizeof_ar(*S);
	int left_child = 2*i;
	int right_child = 2*i+1;
	int largest = 0;

	if(left_child<=size && (*S)[left_child] > (*S)[i])
	{
		largest = left_child;
	}
	else
	{
		largest = i;
	}
	if(right_child<=size && (*S)[right_child] > (*S)[largest])
	{
		largest = right_child;
	}
	if(largest != i){
		int tmp = (*S)[largest];
		(*S)[largest] = (*S)[i];
		(*S)[i] = tmp;
		Max_heapify(S,largest);
	}
}
int sizeof_ar(int* S){
	int size;
	size = *(S - sizeof(int));
	return size/4;
}