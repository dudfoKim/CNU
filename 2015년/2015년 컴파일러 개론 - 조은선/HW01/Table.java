package HW01;

// 변수를 관리하기 위한 클래스. 
// 변수의 삽입과 변수가 테이블에 존재하는지를 판별해준다.
public class Table {
	static int cnt = 0;
	String[] id = new String[100];
	String[] value = new String[100];
	
	void insertTable(String id, String value){
		for(int i=0;i<cnt;i++)
		{
			if(this.id[i].equals(id))
			{
				this.value[i] = value;
				return ;
			}
		}
		this.id[cnt] = id;
		this.value[cnt] = value;
		cnt ++;
	}
	// 변수가 테이블에 존재하면 해당하는 변수에 value값을 리턴.
	String lookupTable(String id){
		String result = null;
		for(int i=0;i<cnt;i++)
		{
			if(this.id[i].equals(id))
			{
				result = this.value[i];
			}
		}
		return result;
	}
}
