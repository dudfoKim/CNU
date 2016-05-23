package TopologicalSorting;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph(new String[] { "C1", "C2", "C3", "C4", "C5", "C6",
				"C7", "C8", "C9", "C10", "C11", "C12", "C13", "C14", "C15" });
		g.add("C1", "C3");
		g.add("C2", "C3");
		g.add("C3", "C7");
		g.add("C3", "C8");
		g.add("C4", "C5");
		g.add("C5", "C6");
		g.add("C6", "C7");
		g.add("C6", "C15");
		g.add("C7", "C9");
		g.add("C7", "C10");
		g.add("C7", "C12");
		g.add("C7", "C13");
		g.add("C8", "C9");
		g.add("C10", "C11");
		g.add("C13", "C14");
		System.out.println("***** 초기 그래프 *****");
		System.out.println(g);

		System.out.println("\n***** Totological Sorting *****");
		g.topsort();
		System.out.println();

		System.out.println("\n***** Totological Sorting후 그래프 *****");
		System.out.println(g);

	}

}