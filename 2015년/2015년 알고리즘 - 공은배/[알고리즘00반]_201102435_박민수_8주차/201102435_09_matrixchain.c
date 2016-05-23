#include <stdio.h>
#include <stdlib.h>

typedef struct coordinates {
	int x;
	int y;
}coordinates;

void Matirix_chain(int* p, int n);
void print_optimal_parens(int** s, int i, int j);
int search(int* p, int n,int size);
int** m;
int** s;

int main(){
	FILE* fp;
	FILE* fop;
	coordinates* data= (coordinates*)malloc(sizeof(coordinates));
	double min = 0;
	int cnt = 0;
	int size = 0;
	int i = 0;
	int j = 0;
	int* m_array = (int*)malloc(sizeof(int)); 
	
	fp = fopen("week9.txt","rt");
	fop = fopen("00_201102435_Matrixchain.txt","wt");

	if(fp == NULL)
	{
		printf("**** Input File open Error **** \n");
		exit(1);
	}
	// 파일에서 읽어서 구조체 배열에 저장
	while(!feof(fp)){
		data = (coordinates*)realloc(data, sizeof(coordinates)*(cnt+1));
		fscanf(fp, "(%d,%d)", &data[cnt].x,&data[cnt].y);
		cnt++;
	}
	for(i=0;i<cnt-1;i++){
		if(search(m_array,data[i].x,size)) {
			m_array = (int*)realloc(m_array, sizeof(int)*(size+1)); 
			m_array[size] = data[i].x;
			size++;
		}
		if(search(m_array,data[i].y,size)) {
			m_array = (int*)realloc(m_array, sizeof(int)*(size+1));
			m_array[size] = data[i].y;
			size++;
		}
	}
	m = (int **)malloc(sizeof(int*)*size);
	for(i=0; i<size; i++){
		(m[i])=(int *)malloc(sizeof(int)*size);
	}
	s = (int **)malloc(sizeof(int*)*size);
	for(i=0; i<size; i++){
		(s[i])=(int *)malloc(sizeof(int)*size);
	}
	for(i=0; i<size;i++){
		for(j=0;j<size;j++)
			s[i][j] = 0;
	}
	for(i=0; i<size;i++){
		for(j=0;j<size;j++)
			m[i][j] = 0;
	}

	Matirix_chain(m_array,size);
	print_optimal_parens(s,1,size-1);
	printf("\n");

	fclose(fp);
	fclose(fop);
	return 0;
}

void Matirix_chain(int* p, int n) {
	int i = 0;
	int l = 0;
	int j = 0;
	int k = 0;
	int q = 0;

	for(i = 1; i < n; i++)
		m[i][i] = 0;

	for(l = 2; l < n; l++) {
		for(i=1; i <= n-l;i++){
			j = i+l-1;

			m[i][j] = 9999;

			for (k=i;k<=j-1;k++){

				q = m[i][k] + m[k+1][j]+ p[i-1] * p[k] * p[j];

				if(q<m[i][j]){
					m[i][j] = q;
					s[i][j] = k;
				}

			}
		}
	}
}
void print_optimal_parens(int** s, int i, int j) {
	if(i==j){
		printf("A%d",i);
	}
	else{
		printf("(");
		print_optimal_parens(s,i,s[i][j]);
		print_optimal_parens(s,s[i][j]+1,j);
		printf(")");
	}
}
int search(int* p, int n,int size) {
	int i = 0;
	
	for(i=0; i<size; i++){
		if(p[i] == n)
			return 0;
	}
	return 1;
}