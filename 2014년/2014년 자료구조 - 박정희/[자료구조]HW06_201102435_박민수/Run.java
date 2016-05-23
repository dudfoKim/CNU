/*201102435 박민수*/

package hw06;

public class Run {

	public static void main(String[] args) {
		CircularQueue q = new CircularQueue();
		System.out.println("==========환영 큐에 값을 입력==========");
		q.Add("1");
		q.Add("2");
		q.Add("3");
		q.Add("4");
		q.Add("5");
		q.Add("6");
		q.Add("7");
		q.Add("8");
		q.Add("9");
		q.PrintQueue();
		System.out.println();
		
		System.out.println("==========환영 큐에 값을 입력(크기초과)==========");
		q.Add("10");
		System.out.println();
		
		System.out.println("==========환영 큐에 값을 입력(resize)==========");
		q.Add_resize("10");
		q.Add("11");
		q.Add("12");
		q.Add("13");
		q.Add("14");
		q.Add("15");
		q.Add("16");
		q.Add("17");
		q.Add("18");
		q.Add("19");
		q.PrintQueue();
		System.out.println();
		
		System.out.println("==========환영 큐에 값을 입력(크기초과)==========");
		q.Add("20");
		System.out.println();
		
		System.out.println("==========환영 큐를 삭제(FIFO)==========");
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println();
		
		q.Remove();
		q.PrintQueue();
		System.out.println("==========환영 큐삭제(큐가 비어있을때)==========");
		q.Remove();
	
		System.out.println("==========환영 큐추가==========");
		q.Add("1");
		q.Add("2");
		q.Add("3");
		q.Add("4");
		q.PrintQueue();
	

	}

}