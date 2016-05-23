/*201102435 박민수*/

package hw06;

public class CircularQueue {
	int size = 0;			//배열의 크기를 받는 변수
	int DEFAULT_QUEUE_SIZE = 10;			//배열의 크기를 정하지 않았을때의 배열크기
	int front;
	int rear;

	String[] array;
	
	
	CircularQueue(){
		this.size = DEFAULT_QUEUE_SIZE;
		array = new String[size];
		rear = size -1;
		front = size - 1;
	}
	
	CircularQueue(int size){
		this.size=size;
		array = new String[size];
		rear = size -1;
		front = size - 1;
	}
	//배열에 item을 추가하는 메소드(배열이 꽉차있으면 에러메세지출력)
	public void Add(String item){
		if(isFull()){
			System.out.println("배열이 꽉 차있습니다.");
		}
		
		//비어있을대 조건을 주지 않으면 Remove로 다지우고 난뒤 추가안되어서 구현
		else if(isEmpty()){
			rear = size -1;
			front = size - 1;
			rear = (rear +1)%size;
			array[rear] = item;
		}
		else{
			rear = (rear +1)%size;
			array[rear] = item;
		}
		
	}
	//배열에 item을 추가하는 메소드로 배열의 꽉차있으면 배열의 크기를 그전 배열의 2배로 resize해서 추가하는 메소드
	public void Add_resize(String item){
		if(isFull()){
			String[] temp = new String[size];
			for(int i=0 ;i<size; i++)
			{
				temp[i] = array[i];
			}
			this.size = size*2;
			array = new String[size];
			for(int i = 0; i<temp.length;i++)
			{
				array[i] = temp[i];
			}
			front = size-1;
			rear = (rear +1)%size;
			array[rear] = item;
		}
		
		//비어있을대 조건을 주지 않으면 Remove로 다지우고 난뒤 추가안되어서 구현
		else if(isEmpty()){
			rear = size -1;
			front = size - 1;
			rear = (rear +1)%size;
			array[rear] = item;
		}
		
		else{
			rear = (rear +1)%size;
			array[rear] = item;
		}
	}
	//큐를 지우는 메소드(FIFO)
	public String Remove(){
		String remove = null ;
		if(isEmpty()){
			System.out.println("이 배열은 비어있어 지울 대상이 없습니다.");
		}
		else{
				front = (front +1)%size;
				remove = array[front];
				array[front] = null;
		}
		
		return remove;
	}
	//큐가 꽉차있는지 확인하는 메소드
	public boolean isFull(){
		return (front == (rear+1)%size);
	}
	//큐가 비어있는지 확인하는 메소드
	public boolean isEmpty(){
		return (rear == front);
	}
	//큐를 출력하는 메소드(큐가 비어있을시 에러메세지를 출력한다)
	public void PrintQueue(){
		System.out.print("Print Queue = > ");
		if(rear == front)
		{
			System.out.println("이 배열은 비어있습니다.");
		}
		else{
			for(int i =(front+1)%size; i<=rear; i++)
				System.out.print(array[i]+" ");
		}
	}
}