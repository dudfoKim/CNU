package PLHW2;

public enum TokenType {
	ID(3), INT(2);
	private final int finalState;

	TokenType(int finalState) {
		this.finalState = finalState;
	}
}
