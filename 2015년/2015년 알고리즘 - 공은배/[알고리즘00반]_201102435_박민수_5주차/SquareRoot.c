#include <stdio.h>
#include <Windows.h>

#define INTEGER_MAX 2147438647
#define INTEGER_MAX_SQUARE_ROOT 46340 

int slowWay(int x);
int fastWay(int x,int low, int high);

int main(){
	LARGE_INTEGER start,end,freq;
	int x;
	printf("input number : ");
	scanf("%d",&x);
	QueryPerformanceFrequency(&freq);
	QueryPerformanceCounter(&start);
	//printf("Fast way result : %d\n",fastWay(x,0,INTEGER_MAX_SQUARE_ROOT));
	printf("Slow way result : %d\n",slowWay(x));
	QueryPerformanceCounter(&end);
	printf("time = %.8f\n", (float)(end.QuadPart - start.QuadPart)/freq.QuadPart);
	//printf("Fast way result : %d\n",fastWay(x,0,INTEGER_MAX_SQUARE_ROOT));



	return 0;
}

int slowWay(int x){
	int i =0;
	while(i<INTEGER_MAX_SQUARE_ROOT){
		if(x>INTEGER_MAX){
			printf("찾으려는 값이 정수범위를 벗어났습니다.");
			return -1;
		}

		if(i*i>x)
			return i-1;
		i++;
	}
	return -1;
}

int fastWay(int x,int low, int high){
	int mid = (low+high) /2;

	if(x>INTEGER_MAX){
		printf("찾으려는 값이 정수범위를 벗어났습니다.");
		return -1;
	}
	
	if(low == high)
		return mid-1;
	if(mid*mid>x)
		return fastWay(x,low,mid);
	if(mid*mid<=x)
		return fastWay(x,mid+1,high);

}