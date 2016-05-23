package Kuruskal;

public class Graph {
	// 그래프의 크기
	private int size;
	// 정점을 저장하는 배열
	private String[] vertices;
	// 가중치가 저장된 인접행렬
	private int[][] a;
	// 전체 간선의 개수
	private int e_size = 0;

	public Graph(String[] args) {
		size = args.length;
		vertices = new String[size];

		System.arraycopy(args, 0, vertices, 0, size);

		a = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j)
					continue;
				/*
				 * 자기자신을 가르치는곳을 제외하고 int형의 최대값을 설정(-200000은 오버플로우를 방지하기위해 사용)
				 */
				a[i][j] = Integer.MAX_VALUE - 200000;
			}
		}

	}

	// 쿠르스칼 알고리즘
	public void kruskal() {
		Edges[] T = new Edges[size - 1];
		Edges[] E = new Edges[e_size];
		int z = 0;
		int q = 0;
		int p = 0;

		// 집합 E에 그래프에 모든 간선들을 받아온다.
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				if (a[i][j] != Integer.MAX_VALUE - 200000 && a[i][j] != 0) {
					E[z] = new Edges(vertices[i], vertices[j], a[i][j]);
					z++;
				}
			}
		}
		// 오름차순 정렬
		for (int i = 0; i < e_size; i++) {
			for (int j = i + 1; j < e_size; j++) {
				Edges temp;
				if (E[i].weight > E[j].weight) {
					temp = E[i];
					E[i] = E[j];
					E[j] = temp;
				}
			}
		}
		// 오름차순으로 정렬된 간선들이사이클을 형성하지않으면 T에 간선 추가.
		while (q < size - 1 && p < e_size) {
			if (find(E[p], T)) {
				union(E[p], T);
				q++;
			}
			p++;
		}
		if (q != size - 1) {
			System.out.println("신장트리를 만들수 없습니다.");
		} else {
			System.out.println("최소 신장트리를 구성하는 간선 : ");
			for (int i = 0; i < size - 1; i++) {
				System.out.println(T[i].v + "-" + T[i].w);
			}
		}

	}

	// 두집합의 합집합을 구함
	public void union(Edges e, Edges[] T) {
		for (int i = 0; i < size - 1; i++) {
			if (T[i] == null) {
				T[i] = e;
				break;
			}
		}
	}

	// 선택한 간선이 사이클을 이루는지 체크
	public boolean find(Edges e, Edges[] T) {
		boolean v = false, w = false;
		for (int i = 0; i < size - 1; i++) {
			if (T[i] == null) {
				continue;
			}
			if (e.v.equals(T[i].w)) {
				v = true;
			}
			if (e.w.equals(T[i].w)) {
				w = true;
			}
		}
		if (v & w) {
			return false;
		}
		return true;
	}

	public void add(String v, String w, int weight) {
		int i = index(v), j = index(w);
		a[i][j] = a[i][j] = weight;
		this.e_size++;
	}

	private int index(String v) {
		for (int i = 0; i < size; i++)
			if (vertices[i].equals(v))
				return i;
		return a.length;
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

	// 두개의 정점과 가중치를 가지는 클래스
	public class Edges {
		private String v;
		private String w;
		private int weight;

		Edges(String v, String w, int weight) {
			this.v = v;
			this.w = w;
			this.weight = weight;
		}
	}
}
