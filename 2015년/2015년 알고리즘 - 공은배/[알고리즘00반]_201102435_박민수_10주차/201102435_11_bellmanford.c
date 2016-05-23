#include <stdio.h>
#include <stdlib.h>
 
// 무한대로 사용할 상수 선언.
#define INFINITY ((1 << (sizeof(int)*8-2))-1)
 
// 간선의 정보를 담는 구조체.
typedef struct Edge{
	// 시작점
	int source;
	// 도착점
	int dest;
	// 가중치
	int weight;
}Edge;
 
// 벨먼-포드 알고리즘을 수행 하는 함수.
void BellmanFord(Edge edges[], int edgecount, int nodecount, int source, FILE* fop);
 
int main(){
	FILE* fp;
	FILE* fop;

	// 노드의 개수
	int node_size = 0;

	// 시작점
	int start_node = 0;

	// 간선의 개수.
	int cnt = 0;

	// 간선의 정보를 저장하는 구조체 배열.
	Edge* edge = (Edge*)malloc(sizeof(Edge));
	
	fp = fopen("data11.txt","rt");
	fop = fopen("bellman.txt","wt");

	// 노드의 갯수와 시작점을 따로 받아놓는다.
	fscanf(fp, "%d",&node_size);
	fscanf(fp, "%d",&start_node);

	//간선들의 정보를 파일에서 받아와 구조체에 저장.
	while(!feof(fp)){
		edge = (Edge*)realloc(edge, sizeof(Edge)*(cnt+1));
		fscanf(fp, "%d %d %d\n",&edge[cnt].source,&edge[cnt].dest,&edge[cnt].weight);
		cnt++;
	}

	// 밸먼-포드먼 알고리즘을 이용하여 시작점에서 각 노드로 가는 최단거리를 구한다.
	BellmanFord(edge, cnt ,node_size,start_node,fop);
 
	fclose(fp);
	fclose(fop);
	return 0;
}
 
// 간선의 정보를 담은 배열, 간선의 수, 노드의 수, 시작점, 작성할 파일을 매개변수로 받아옴.
void BellmanFord(Edge edges[], int edgecount, int nodecount, int source, FILE* fop) {
	int i,j ;
	// 최단거리를 저장하는 배열 선언.
	int* dist = (int*)malloc(sizeof(int)*nodecount);

	// 최단거리 배열을 초기화.
	for(i = 0; i < nodecount; i++) {
		if(i == source) dist[i] = 0;
		else dist[i] = INFINITY;
	}

	/* 각 노드로 가는 최단거리를 계산.
	다이스트라 알고리즘과의 차이는 다이스트라의 경우 간선의 가중치가 음수가 존재하면 구할수없으나
	밸먼-포드 알고리즘에 경우 음수여도 계산이 가능하다.*/
	for(i = 0; i < nodecount; i++) {
		for(j = 0; j < edgecount; j++) {
			if(dist[edges[j].dest] > dist[edges[j].source] + edges[j].weight) {
				dist[edges[j].dest] = dist[edges[j].source] + edges[j].weight;
			}
		}
	}

	/* 순환이 발생하는지 검사. 
	순환이 발생하면  에러매세지를 출력하고 함수를 종료한다.*/
	for(i = 0; i < edgecount; i++) {
		if(dist[edges[i].dest] > dist[edges[i].source] + edges[i].weight) {
			printf("순환이 발생하였습니다.");
			return ;
		}
	} 
	// 시작점에서 도착점에 대한 최단거리 출력.
	for(i = 0; i < nodecount; i++) {
		printf("%d -> %d (%d)\n", source, i, dist[i]);
		fprintf(fop,"%d -> %d (%d)\n", source, i, dist[i]);
	}
}