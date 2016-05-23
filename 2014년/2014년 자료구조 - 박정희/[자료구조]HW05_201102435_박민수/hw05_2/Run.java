/*201102435 박민수*/

package hw05_2;

public class Run {

	public static void main(String[] args) {
		LinkedQueue que = new LinkedQueue();
		
		que.Add("0");
		que.Add("1");
		que.Add("2");
		que.Add("3");
		que.Add("4");
		que.Add("5");
		que.Add("6");
		que.Add("7");
		que.Add("8");
		que.Add("9");
		que.Add("10");
		que.Add("11");
		
		System.out.print("Que Size : "+que.size+"\n");
		que.PrintQueue();
		System.out.println("");
		
		que.Remove();
		System.out.print("Remove >0 삭제 Que Size : "+que.size+"\n");		
		que.PrintQueue();
		System.out.println("");
		
		que.Remove();
		System.out.print("Remove >1 삭제 Que Size : "+que.size+"\n");
		que.PrintQueue();
		System.out.println("");
		
		que.Remove();
		System.out.print("Remove >2 삭제 Que Size : "+que.size+"\n");
		que.PrintQueue();
		System.out.println("");
		
		que.Remove();
		System.out.print("Remove >3 삭제 Que Size : "+que.size+"\n");
		que.PrintQueue();
		System.out.println("");
		
		que.Remove();
		System.out.print("Remove >4 삭제 Que Size : "+que.size+"\n");
		que.PrintQueue();
		System.out.println("");
		
		que.Remove();
		System.out.print("Remove >5 삭제 Que Size : "+que.size+"\n");
		que.PrintQueue();
		System.out.println("");
		
		que.Remove();
		System.out.print("Remove >6 삭제 Que Size : "+que.size+"\n");
		que.PrintQueue();
		System.out.println("");
		
		que.Remove();
		System.out.print("Remove >7 삭제 Que Size : "+que.size+"\n");
		que.PrintQueue();
		System.out.println("");
		
		que.Remove();
		System.out.print("Remove > 8삭제   Que Size : "+que.size+"\n");
		que.PrintQueue();
		System.out.println("");
		
		que.Remove();
		System.out.print("Remove > 9  삭제 Que Size : "+que.size+"\n");
		que.PrintQueue();
		System.out.println("");
		
		que.Remove();
		System.out.print("Remove > 10 삭제 Que Size : "+que.size+"\n");
		que.PrintQueue();
		System.out.println("");
		
		que.Remove();
		System.out.print("Remove > 11 삭제 Que Size : "+que.size+"\n");
		que.PrintQueue();
		System.out.println("");

	}

}
