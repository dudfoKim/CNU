package TopologicalSorting;

public class Graph {
	int size;
	List[] a;
	// vertex의 indegree
	int[] indegree;

	Stack stack = new Stack();
	Que que = new Que();

	public Graph(String[] args) {
		size = args.length;

		a = new List[size];

		for (int i = 0; i < size; i++) {
			a[i] = new List(args[i]);
		}
		indegree = new int[size];
	}

	public void add(String v, String w) {
		a[index(v)].add(index(w));
		// 간선이 추가되면 indegree가 증가된다.
		indegree[index(w)]++;
	}

	public void delete(String v, String w) {

		// 입력받은 두값중에서 잘못된 값이 입력될경우 메세지를 띄우고 메소드를 종료한다
		if (index(v) == size || index(w) == size) {
			System.out.println("잘못된 값을 입력하셨습니다.");
			return;
		}

		// 두값사이의 간선이 존재하지 않는다면 메소드를 종료한다.
		if (a[index(v)].Checkedges(index(w)) == false) {
			System.out.println("입력하신 값사이에 간선이 존재하지 않습니다.");
			return;
		}
		a[index(v)].delete(index(w));
	}

	// Topological Sorting 메서드
	public void topsort() {
		
		// indegree가 0인 vertex의 인덱스를 스택에 추가
		for (int i = 0; i < size; i++) {
			if (indegree[i] == 0) {
				stack.Add(i);
			}
		}
		
		while (stack.size != 0) {
			while (stack.size != 0) {
				int i = stack.Remove();
				que.Add(i);

				while (a[i].edgeNum != 0) {
					indegree[a[i].edges.to]--;
					a[i].delete(a[i].edges.to);
				}
				indegree[i]--;
			}
			for (int i = 0; i < size; i++) {
				if (indegree[i] == 0) {
					stack.Add(i);
				}
			}
		}
		
		if(que.size != this.size){
			System.out.println("위상정렬을 할수없습니다.");
		}
		else{
			while(que.size != 0){
				int i = que.Remove();
				System.out.print(a[i].vertex + " ");
			}
		}
	}

	public String toString() {
		if (size == 0)
			return "{}";
		StringBuffer buf = new StringBuffer("{" + a[0]);
		for (int i = 1; i < size; i++)
			buf.append("," + a[i]);
		return buf + "}";

	}

	private int index(String v) {
		for (int i = 0; i < size; i++)
			if (a[i].vertex.equals(v))
				return i;
		return a.length;
	}

	private class List {
		String vertex;
		int edgeNum = 0;
		Node edges;

		List(String vertex) {
			this.vertex = vertex;
		}

		public void add(int j) {
			edges = new Node(j, edges);
			edgeNum++;
		}

		// 입력한 두 값사이에 간선이 존재하는지 안하는지 검사는 메소드로 존재하면 true를 존재하지 않으면 false를 리턴.
		public boolean Checkedges(int j) {
			Node check = edges;
			for (; check != null; check = check.next) {
				if (check.to == j)
					return true;
			}
			return false;
		}

		// 입력한 두 값사이의 간선을 제거하는 메소드
		public void delete(int j) {
			Node p = edges;

			// 인접리스트에서 처음과 두번째 값 사이의 간선을 삭제할 결우
			if (edges.to == j) {
				edges = edges.next;
			}
			// 나머지의 경우
			else {
				while (true) {
					if (p.next.to == j)
						break;
					p = p.next;
				}
				p.next = p.next.next;
			}

			edgeNum--;
		}

		public String toString() {
			StringBuffer buf = new StringBuffer(vertex);
			if (edges != null)
				buf.append(":");
			for (Node p = edges; p != null; p = p.next)
				buf.append(Graph.this.a[p.to].vertex);
			return buf + "";
		}

		public class Node {
			int to;
			Node next;

			Node(int to, Node next) {
				this.to = to;
				this.next = next;
			}
		}
	}

}