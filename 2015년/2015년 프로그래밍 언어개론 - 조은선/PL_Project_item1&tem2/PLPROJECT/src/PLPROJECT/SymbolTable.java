package PLPROJECT;

public class SymbolTable {
	static int cnt = 0;
	String[] id = new String[100];
	Node[] value = new Node[100];
	
	void insertTable(String id, Node value){
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
	Node lookupTable(String id){
		Node result = null;
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
