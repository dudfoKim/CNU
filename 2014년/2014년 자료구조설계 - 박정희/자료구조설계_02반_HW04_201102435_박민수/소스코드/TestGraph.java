package Kuruskal;

public class TestGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph(new String[] { "A", "B", "C", "D", "E", "F", "G" });
		g.add("A", "B", 4);
		g.add("A", "G", 2);
		g.add("A", "F", 1);
		g.add("B", "C", 2);
		g.add("B", "G", 5);
		g.add("C", "D", 4);
		g.add("C", "G", 1);
		g.add("D", "E", 1);
		g.add("D", "G", 2);
		g.add("E", "F", 3);
		g.add("E", "G", 4);
		g.add("F", "G", 3);
		System.out.println("*******저장되어 있는 그래프(괄호안은 가중치)******");
		System.out.println(g);
		System.out.println("*******Kruskal Algorithm 실행******");
		g.kruskal();
	}

}
