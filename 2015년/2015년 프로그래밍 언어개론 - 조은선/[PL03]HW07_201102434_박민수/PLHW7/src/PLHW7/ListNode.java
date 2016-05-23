package PLHW7;

public class ListNode extends Node {
	public Node value;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ListNode))
			return false;
		
		ListNode tmp = (ListNode) obj;
		if(this.value.next == null)
			return this.value.equals(tmp.value);
		else
			return this.value.equals(tmp.value) && this.value.next.equals(tmp.value.next);
	}

	@Override
	public void copyValue(Node node) {
		// TODO Auto-generated method stub
		this.value = ((ListNode) node).value;
	}
}
