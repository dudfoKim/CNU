package Question1;

public class TestGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph(new String[]{"0", "1", "2", "3" ,"4"});
		System.out.println(g);
		g.add("0", "1");
		g.add("0", "2");
		g.add("1", "2");
		g.add("1", "3");
		g.add("2", "3");
		g.add("3", "4");
		System.out.println(g);
		
		System.out.println("*****1과 2사이의 간선을 제거(중간값들 사이의 간선제거경우)*****");
		g.delete("1", "2");
		System.out.println(g);
		
		System.out.println("*****1과 3사이의 간선을 제거(처음과 두번째 사이의 간선제거경우)*****");
		g.delete("1", "3");
		System.out.println(g);
		
		System.out.println("*****잘못된 값을 입력한경우(5,3)*****");
		g.delete("5", "3");
		
		System.out.println("*****두값사이의 간선이 존재하지 않을 경우*****");
		g.delete("1", "3");

	}

}
