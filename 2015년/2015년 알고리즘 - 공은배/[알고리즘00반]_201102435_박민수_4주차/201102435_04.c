#include <stdio.h>
#include <stdlib.h>
#include <Windows.h>

void Quick_Sort(int *S,int p,int r);
int Partition(int *S,int p,int r);
int Randomized_partition(int *S,int p,int r);


void main(){
	FILE* fp;
	FILE* fop;

	LARGE_INTEGER start,end,freq;
	int* data = (int*)malloc(sizeof(int));
	int i = 0;
	int cnt = 0;

	fp = fopen("data04.txt","rt");
	fop = fopen("00_201102435_output.txt","wt");

	if(fp == NULL)
	{
		printf("**** Input File open Error **** \n");
		exit(1);
	}

	while(!feof(fp)){
		data= (int*)realloc(data, sizeof(int)*(cnt+1));
		fscanf(fp, "%d,", &data[cnt]);
		cnt++;
	}
	
	QueryPerformanceFrequency(&freq);
	QueryPerformanceCounter(&start);
	//Randomized_partition(data,0,cnt-1);
	Quick_Sort(data,0,cnt-1);
	QueryPerformanceCounter(&end);
	for(; i<cnt; i++){
		printf("%d ", data[i]);
		fprintf(fop,"%d,", data[i]);
	}
	printf("\n");
	printf("time = %.8f", (float)(end.QuadPart - start.QuadPart)/freq.QuadPart);
	printf("\n");
	free(data);
	fclose(fp);
	fclose(fop);
}

void Quick_Sort(int *S,int p,int r){
	int q;
	if(p<r){
		q = Partition(S,p,r);
		Quick_Sort(S,p,q-1);
		Quick_Sort(S,q+1,r);
	}
}
int Partition(int *S,int p,int r){
	int x = S[r];
	int i = p-1;
	int j;
	int tmp;
	for(j = p; j<=r-1; j++){
		if(S[j]<=x){
			i = i+1;
			tmp = S[i];
			S[i] = S[j];
			S[j] = tmp;
		}
	}
	
	i = i+1;
	tmp = S[i];
	S[i] = S[r];
	S[r] = tmp;

	return i;
}
int Randomized_partition(int *S,int p,int r){
	int tmp;
	int iPivot;

	int ptrCenter = (p+r)/2;

	if(!(S[p] < S[ptrCenter] ^ S[ptrCenter] < S[r]))
		iPivot = ptrCenter;
	else if(!(S[ptrCenter] < S[p] ^ S[p]< S[r]))
		iPivot = p;
	else
		iPivot = r;

	tmp = S[r];
	S[r] = S[iPivot];
	S[iPivot] = tmp;

	return Partition(S,p,r);
}