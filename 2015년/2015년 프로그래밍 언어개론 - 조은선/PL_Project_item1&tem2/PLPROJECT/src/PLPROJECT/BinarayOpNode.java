package PLPROJECT;

public class BinarayOpNode extends Node {
	public enum BinType {
		MINUS, PLUS, TIMES, DIV, LT, GT, EQ
	}

	public BinType value;

	@Override
	public String toString() {
		return value.name();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof BinarayOpNode))
			return false;
		if (!super.equals(obj))
			return false;
		BinarayOpNode tmp = (BinarayOpNode) obj;
		if (this.value.equals(tmp.value))
			return true;
		return false;
	}

	@Override
	public void copyValue(Node node) {
		// TODO Auto-generated method stub
		this.value = ((BinarayOpNode) node).value;
	}
}
