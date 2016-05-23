#include <stdio.h>
#define INFINITY 999

// Dijkstra 알고리즘을 실행하는 함수.
void dijkstra(int n, int v, int cost[10][10], int dist[]);

void main()
{
	// 사용할 변수 선언.
	int n, v, i, j, cost[10][10], dist[10];

	// 노드의 수를 입력받아 변수 n에 저장.
	printf("Enter the number of nodes : ");
	scanf("%d", &n);

	// 각 노드간의 거리를 반복문을 이용하여 받아서 저장.
	printf("\nEnter the cost matrix : \n");
	
	for(i=1; i<=n; i++)
	{
		for(j=1; j<=n; j++)
		{
			scanf("%d", &cost[i][j]);

			// 간선이 존재하지 않는 경우 거리는 INFINITY값을 가짐.
			if(cost[i][j]==0)
			{
				cost[i][j] = INFINITY;
			}
		}
	}

	
	// 알고 싶은 노드의 인덱스를 입력 받은 후 변수 v에 저장
	printf("\nEnter the source matrix : ");
	scanf("%d", &v);

	// Dijkstra 알고리즘을 이용하여 최단거리를 구함.
	dijkstra(n, v, cost, dist);

	// 반복문을 이용하여 최단경로를 출력.
	printf("\nShortest path : \n");

	for(i=1; i<=n; i++)
	{
		if(i!=v)
			printf("%d->%d, cost => %d\n", v-1, i-1, dist[i]);
	}
}

void dijkstra(int n, int v, int cost[10][10], int dist[])
{
	int i, u, count, w, flag[10], min;

	// 초기화.
	for(i=1; i<=n; i++)
	{
		flag[i] = 0;
		dist[i] = cost[v][i];
	}

	count=2;

	// 최단거리를 구하기 위한 반복.
	// while문을 통해 시작점부터 연결된 노드를 하나씩 비교하여 가장 작은 쪽으로 연결.
	// 이 반복을 통해 dist배열에는 경로가 저장된다.
	while(count<=n)
	{
		// min 값을 초기화.
		min = 99;
		
		for(w=1; w<=n; w++)
		{
			// 거리가 min 값보다 작고, 방문한적이 없었다면
			if(dist[w]<min && !flag[w])
			{
				min = dist[w], u = w;
			}
		}

		// 최단거리를 방문했으니 flag를 1로 바꾸어 방문했다고 표시
		flag[u] = 1;
		count++;

		
		for(w=1; w<=n; w++)
		{
			// 위에서 구한 치단거리보다 새로 구한 최단거리가 더 작고, 방문한적이 없었다면 최단거리를 바꾸어준다.
			if((dist[u]+cost[u][w]<dist[w]) && !flag[w])
			{
				dist[w] = dist[u] + cost[u][w];
			}
		}
	}
}