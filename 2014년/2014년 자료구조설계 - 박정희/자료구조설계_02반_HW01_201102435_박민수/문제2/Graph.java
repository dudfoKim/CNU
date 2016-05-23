package Question2;

public class Graph {
	int size;
	String[] vertices;
	//BFS시 방문했는지 기록(미방문시 0, 방문시 1)
	int[] visitvertices;
	boolean[][] a;
	//BFS시 사용할 큐
	LinkedQueue que = new LinkedQueue();
	
	public Graph(String[] args){
		size = args.length;
		vertices = new String[size];
		visitvertices = new int[size];
		System.arraycopy(args, 0, vertices, 0, size);
		a = new boolean[size][size];
	}
	
	public void add(String v, String w){
		int i = index(v), j = index(w);
		a[i][j] = a[j][i] = true;
	}
	
	public String toString(){
		if(size == 0) return "{}";
		StringBuffer buf = new StringBuffer("{" + vertex(0));
		for (int i = 1 ; i < size ; i++)
			buf.append("," + vertex(i));
		return buf + "}";
	}
	
	private int index(String v){
		for (int i = 0; i<size ; i++)
			if(vertices[i].equals(v)) return i;
		return a.length;
	}
	
	//너비우선탐색을 실행하는 메소드
	public void BFS(){
		//시작점을 설정(vertices[0])
		que.Add(vertices[0]);
		
		while (que.size !=0){
			int i = index(que.Remove());
			//방문한 곳을 방문했다고 표시
			visitvertices[i] = 1;
			for (int j = 0; j < size; j++)
				//두 정점사이에 간선이 존재하고 방문한적이 없고 que속에 존재하지 않는다면 que에 추가.
				if(a[i][j] && visitvertices[j] != 1 && que.Search(vertices[j]) != true) 
					que.Add(vertices[j]);
		}
	}
	
	//너비우선탐색을 수행한 결과를 프린트하는 메소드
	public void PrintBFS() {
		// 시작점을 설정(vertices[0])
		que.Add(vertices[0]);

		while (que.size != 0) {
			int i = index(que.Remove());
			//방문한 곳을 방문했다고 표시
			visitvertices[i] = 1;
			for (int j = 0; j < size; j++)
				//두 정점사이에 간선이 존재하고 방문한적이 없고 que속에 존재하지 않는다면 que에 추가.
				if (a[i][j] && visitvertices[j] != 1 && que.Search(vertices[j]) != true)
					que.Add(vertices[j]);
			//BFS를 수행하면서 방문한 정점 출력
			System.out.print(vertices[i]);
			if(que.size !=0)
				System.out.print(" -> ");
		}
	}
	
	private String vertex(int i){
		StringBuffer buf = new StringBuffer(vertices[i] + ":");
		for (int j = 0; j < size; j++)
			if(a[i][j]) buf.append(vertices[j]);
		return buf + "";
	}
}
