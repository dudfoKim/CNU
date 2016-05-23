#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

void LCS_LENGTH(char* X,char* Y, int** table);
void PRINT_LCS(int** table, char* X, int i, int j,FILE* f);

void main() {
	FILE* fp;
	FILE* fop;
	char* dataX= (char*)malloc(sizeof(char));
	char* dataY= (char*)malloc(sizeof(char));
	int length_X = 0;
	int length_Y = 0;
	int** table;
	int i = 0;
	int j = 0;
	int cnt = 1;
	
	fp = fopen("data07_2.txt","rt");
	fop = fopen("00_201102435_LCS.txt","wt");

	if(fp == NULL)
	{
		printf("**** Input File open Error **** \n");
		exit(1);
	}
	// 파일에서 읽어서 배열에 저장
	while(!feof(fp)){
		dataX = (char*)realloc(dataX, sizeof(char)*(cnt+1));
		fscanf(fp, "%c", &dataX[cnt-1]);
		if(dataX[cnt-1] == '\n')
		{
			dataX[cnt-1] = NULL;
			break;
		}
		dataX[cnt] = NULL;
		cnt++;
	}
	cnt = 1;
	while(!feof(fp)){
		dataY = (char*)realloc(dataY, sizeof(char)*(cnt+1));
		fscanf(fp, "%c", &dataY[cnt-1]);
		dataY[cnt] = NULL;
		cnt++;
	}
	length_X = strlen(dataX);
	length_Y = strlen(dataY);
	printf("X배열의 크기 : %d\n",length_X);
	printf("Y배열의 크기 : %d\n",length_Y);

	//LCS 테이블 생성
	table = (int **)malloc(sizeof(int*)*length_X+1);
	for(i=0; i<length_X+1; i++){
		(table[i])=(int *)malloc(sizeof(int)*length_Y+1);
	}
	//LCS Length 구하기
	LCS_LENGTH(dataX,dataY,table);

	for(i=0;i<length_X+1;i++){
		for(j=0;j<length_Y+1;j++){
			printf("%2d ",table[i][j]);
		}
		printf("\n");
	}

	//출력
	printf("LCS : ");
	PRINT_LCS(table,dataX,length_X,length_Y,fop);
	printf("\n");
	fclose(fp);
	fclose(fop);
}
void LCS_LENGTH(char* X,char* Y, int** table){
	int m = strlen(X)+1;
	int n = strlen(Y)+1;
	int i=0;
	int j=0;

	for(i=0; i<m; i++){
		table[i][0] = 0;
	}
	for(j=0; j<n; j++){
		table[0][j] = 0;
	}

	for(i=1;i<m;i++){
		for(j=1;j<n;j++){
			if(X[i-1] == Y[j-1]){
				table[i][j]=table[i-1][j-1]+1;
			}
			else if(table[i-1][j] >= table[i][j-1]){
				table[i][j]=table[i-1][j];
			}
			else{
				table[i][j]=table[i][j-1];
			}
		}
	}
}

void PRINT_LCS(int** table, char* X, int i, int j,FILE* f){
	if(i== 0 || j== 0){
		return;
	}
	if(table[i][j]-1 == table[i-1][j-1] && table[i][j]-1 == table[i][j-1] && table[i][j]-1 == table[i-1][j]){
		PRINT_LCS(table,X,i-1,j-1,f);
		printf("%c",X[i-1]);
		fprintf(f,"%c",X[i-1]);
	}
	else if(table[i][j] == table[i][j-1]){
		PRINT_LCS(table,X,i,j-1,f);
	}
	else if (table[i][j] == table[i-1][j]){
		PRINT_LCS(table,X,i-1,j,f);
	}
	else{
		PRINT_LCS(table,X,i,j-1,f);
	}
}