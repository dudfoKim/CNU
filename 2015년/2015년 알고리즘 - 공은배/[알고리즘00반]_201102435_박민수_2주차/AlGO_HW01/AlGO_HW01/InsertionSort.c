#include <stdio.h>
#include <stdlib.h>

void main() {
	FILE* fp;
	FILE* fop;
	int* data = (int*)malloc(sizeof(int));
	int cnt = 0;
	int i=0;
	int j=1;
	int key=0;

	fp = fopen("data02.txt","rt");
	fop = fopen("00_201102435_insertion.txt","wt");

	if(fp == NULL)
	{
		printf("**** Input File open Error **** \n");
		exit(1);
	}

	while(!feof(fp)){
		data= (int*)realloc(data, sizeof(int)*(cnt+1));
		fscanf(fp, "%d, ", &data[cnt++]);
	}
	// Insertion Sort 알고리즘.
	for(;j<cnt; j++){
		key = data[j];
		i = j-1;

		while(i>=0 && data[i]>key){
			data[i+1] = data[i];
			i--;
		}
		data[i+1] = key;
	}
	for(i=0;i<cnt; i++){
		fprintf(fop,"%d, ", data[i]);
	}

	fclose(fp);
	fclose(fop);
}																													   