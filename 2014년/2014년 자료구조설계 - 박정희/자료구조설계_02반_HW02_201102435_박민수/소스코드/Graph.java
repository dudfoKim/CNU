package Week2;

public class Graph {
	// 그래프의 크기
	private int size;
	// 정점을 저장하는 배열
	private String[] vertices;
	// 해당 정점 이전의 정점을 저장하는 배열
	private int[] route;
	// 방문한적이 있는지 체크하는 배열
	private boolean[] visit;
	// 가중치가 저장된 인접행렬
	private int[][] a;
	// 두정점까지의 최소길이가 저장된 배열
	private int[] length;
	// 출발지
	private int startNum;

	public Graph(String[] args) {
		size = args.length;
		vertices = new String[size];
		route = new int[size];
		visit = new boolean[size];
		length = new int[size];

		System.arraycopy(args, 0, vertices, 0, size);
		a = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j)
					continue;
				/*자기자신을 가르치는곳을 제외하고 int형의 최대값을 설정
				 *(-200000은 오버플로우를 방지하기위해 사용)*/
				a[i][j] = Integer.MAX_VALUE - 200000;
			}
		}

	}

	public void add(String v, String w, int weight) {
		int i = index(v), j = index(w);
		a[i][j] = a[j][i] = weight;
	}

	private int index(String v) {
		for (int i = 0; i < size; i++)
			if (vertices[i].equals(v))
				return i;
		return a.length;
	}

	// 다이스트라 알고리즘
	public void Dijkstra(int startNum) {
		
		// 받아온 출발지를 설정
		this.startNum = startNum;
		
		/* 가중치를 기준으로 최소길이를 찾을때 가장 작은 가중치를 가진
		 * 간선의 인덱스를 받아올 min_index값을 선언하고 0으로 초기화*/
		int min_index = 0;

		// visit배열에서 출발지를 방문했다고 true로 마크
		visit[startNum] = true;
		
		/* 출발지를 기준으로 각 정점까지의 가중치를 인접행렬에서 복사하고
		 * 해당 정점의 이전 정점을 저장하는 route 배열을 출발지의 인덱스로 초기화*/
		for (int i = 0; i < size; i++) {
			length[i] = a[startNum][i];
			route[i] = startNum;
		}

		for (int i = 0; i < size; i++) {
			
			// visit[i]를 방문했다면 다음으로 넘어간다.
			if (visit[i]) continue;
			
			// 최소값을 찾기위해 비교할시 필요한 min을 선언하고 무한대값으로 초기화
			int min = Integer.MAX_VALUE - 200000;
			
			// 가중치의 최소값을 찾기위한 반복문
			for (int j = 0; j < size; j++) {
				// 이미 방문했던 곳이라면 비교하지않는다.
				if (visit[j]) continue;
				
				/* min값보다 가중치가 작다면 min을 바꿔주고 min값을 가지는 배열의 인덱스를 
				 * min_index에 저장한다.*/
				if (min > length[j]) {
					min = length[j];
					min_index = j;
				}
			}
			
			// 가장 짧은 거리를 찾기위한 반복문
			for (int k = 0; k < size; k++) {
				
				// 방문했던 곳이면 다음으로 넘어간다.
				if (visit[k]) continue;
				
				/* 출발지에서 k인덱스를 가지는 정점까지의 거리가 가중치가 
				 * 가장 작은 정점을 거쳐서 가는 것보다 크다면 거리를 바꾸어주고
				 * k를 인덱스로 가지는 배열의 전 정점을 min_index로 변경한다.*/
				if (length[k] > length[min_index] + a[min_index][k]) {
					length[k] = length[min_index] + a[min_index][k];
					route[k] = min_index;
				}
				// 방문했다고 마크
				visit[min_index] = true;
			}
		}
	}
	// 최단거리를 출력하는 메서드
	public void PrintFlight() {
		System.out.println("*****출발지 " + vertices[startNum]
				+ "에서 각각 정점까지의 최단거리*****");
		for (int i = 0; i < size; i++) {
			System.out.print(vertices[startNum] + " -> " + vertices[i]
					+ " 의 최단거리 : " + length[i]);
			System.out.println();
		}
	}
	// 경로를 출력하는 메서드
	public void Route(int Destination) {
		if (Destination == startNum) {
			System.out.print(vertices[Destination]);
			return;
		}
		Route(route[Destination]);

		System.out.print(" -> " + vertices[Destination]);
	}

	public String toString() {
		if (size == 0)
			return "{}";
		StringBuffer buf = new StringBuffer("{" + vertex(0));
		for (int i = 1; i < size; i++)
			buf.append("," + vertex(i));
		return buf + "}";
	}

	private String vertex(int i) {
		StringBuffer buf = new StringBuffer(vertices[i] + ":");
		for (int j = 0; j < size; j++) {
			if (j == i)
				continue;
			if (a[i][j] != Integer.MAX_VALUE - 200000) {
				buf.append(vertices[j]);
				buf.append("(" + a[i][j] + ")");
			}
		}
		return buf + "";
	}
}
