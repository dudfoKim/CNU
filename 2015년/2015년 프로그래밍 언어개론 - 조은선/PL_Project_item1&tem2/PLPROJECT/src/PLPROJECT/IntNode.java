package PLPROJECT;

public class IntNode extends Node {
	public int value;

	@Override
	public String toString() {
		return "INT: " + Integer.toString(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof IntNode))
			return false;
		if (!super.equals(obj))
			return false;
		IntNode tmp = (IntNode) obj;
		if (this.value == tmp.value)
			return true;
		return false;
	}

	@Override
	public void copyValue(Node node) {
		// TODO Auto-generated method stub
		this.value = ((IntNode) node).value;
	}
}
