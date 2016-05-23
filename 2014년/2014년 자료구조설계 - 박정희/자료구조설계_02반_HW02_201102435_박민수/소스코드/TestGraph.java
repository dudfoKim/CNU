package Week2;

import java.util.Scanner;


public class TestGraph {

	private static Scanner sc;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph g = new Graph(new String[]{"1", "2", "3" ,"4" ,"5" ,"6"});
		g.add("1", "2", 7);
		g.add("1", "3", 9);
		g.add("1", "6", 14);
		g.add("2", "3", 10);
		g.add("2", "4", 15);
		g.add("3", "4", 11);
		g.add("3", "6", 2);
		g.add("4", "5", 6);
		g.add("5", "6", 9);
		
		System.out.println("*******저장되어 있는 그래프(괄호안은 가중치)******");
		System.out.println(g);
		
		System.out.println("*******출발지를 설정******");
		System.out.print("출발지 설정 : ");
		sc = new Scanner(System.in);
		int startNum = sc.nextInt();

		g.Dijkstra(startNum-1);
		g.PrintFlight();
		
		System.out.println("*******출발지 "+startNum+"에서 목적지까지의 경로 출력******");
		System.out.print("목적지 설정 : ");
	
		int Destination = sc.nextInt();
		System.out.println(startNum+" 에서 "+Destination+"까지의 경로");
		g.Route(Destination-1);
	}

}
