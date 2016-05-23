#include <stdio.h>
#include <stdlib.h>
#include <math.h>

typedef struct coordinates {
	double x;
	double y;
}coordinates;

double Closest_Pair(coordinates* arr,int low, int high);
double Min(double a,double b);
int compareX(const void* a,const void* b);
int compareY(const void* a,const void* b);

void main() {
	FILE* fp;
	FILE* fop;
	coordinates* data= (coordinates*)malloc(sizeof(coordinates));
	double min = 0;
	int cnt = 0;
	
	fp = fopen("data05.txt","rt");
	fop = fopen("00_201102435_ClosestPair.txt","wt");

	if(fp == NULL)
	{
		printf("**** Input File open Error **** \n");
		exit(1);
	}
	// 파일에서 읽어서 구조체 배열에 저장
	while(!feof(fp)){
		data = (coordinates*)realloc(data, sizeof(coordinates)*(cnt+1));
		fscanf(fp, "%lf %lf,", &data[cnt].x,&data[cnt].y);
		cnt++;
	}
	printf("배열의 크기 : %d\n",cnt);

	//x에 대해서 sort
	qsort(data, cnt, sizeof(coordinates), compareX);
	min = Closest_Pair(data,0, cnt-1);
	printf("최종 최단거리 = %.3lf\n",min);
	fprintf(fop,"%.3lf ", min);

	fclose(fp);
	fclose(fop);
}

//Closest_Pair 를 구하는 함수로 재귀적으로 구현.
double Closest_Pair(coordinates* arr,int low, int high){
	coordinates* tmp;
	int mid = 0;
	int i = 0;
	int j = 0;
	int new_index_low = 0;
	int new_index_high = 0;
	int new_index_size = 0;
	double close_left = 0;
	double close_right = 0;
	double min = 0;
	double tmp_min = 0;
	double test = 0;
	
	if(high -low <= 1){
		return sqrt(pow(arr[high].x-arr[low].x,2.0)+pow(arr[high].y-arr[low].y,2.0));
	}
	
	// 중간을 기준으로 좌우의 closest-pair를 구하고, 그 두 값중 작은 값을 찾음
	mid = (high+low)/2;
	close_left =Closest_Pair(arr,low,mid);
	close_right =Closest_Pair(arr,mid,high);
	min = Min(close_left,close_right);
	
	//새로운 배열에 값을 넣어준다. 이때 기준선을 기준으로 min만큼에 범위에 존재하는 점들만 추가.
	new_index_low = low;
	new_index_high = high;
	i = mid ;
	while(i>=low){
		if((arr[mid].x - arr[i].x)>min){
			new_index_low = i;
			break;
		}
		i--;
	}
	i = mid ;
	while(i<high){
		if((arr[i].x - arr[mid].x)>min){
			new_index_high = i;
			break;
		}
		i++;
	}
	new_index_size  = new_index_high - new_index_low;
	tmp = (coordinates*)malloc(sizeof(coordinates)*(new_index_size+1));
	for(i = new_index_low;i<=new_index_high; i++){
		tmp[j].x = arr[i].x;
		tmp[j].y = arr[i].y;
		j++;
	}


	//y에 대해서 sort
	qsort(tmp, new_index_size, sizeof(coordinates), compareY);

	//y값을 기준으로 최단거리를 구한다. 이중포문을 이용하여 각점에서 각점까지의 거리를 구함.
	for(i = 0; i<new_index_size+1; i++){
		for(j = i+1; j<new_index_size+1; j++){
			// 거리가 min 이상일때는 더이상 찾지않고 반복문에서 탈출.
			if((tmp[j].y - tmp[i].y) > min)
				break;
			tmp_min = sqrt(pow(tmp[i].x-tmp[j].x,2.0)+pow(tmp[i].y-tmp[j].y,2.0));
			if (min > tmp_min) {
				min = tmp_min;
			}
		}
	}

	return min;
	
}
// 두 값중 최소값을 찾아서 그 값을 리턴.
double Min(double a,double b){
	return (a<b)?a:b;
}
// X값을 기준으로 비교할때 사용하는 함수
int compareX(const void* a,const void* b){
	coordinates *p1 = (coordinates *)a, *p2 = (coordinates *)b;
	return (p1->x - p2->x);
}
// Y값을 기준으로 비교할때 사용하는 함수
int compareY(const void* a,const void* b){
	coordinates *p1 = (coordinates *)a, *p2 = (coordinates *)b;
	return (p1->y - p2->y);
}