package PLPROJECT;

public class Token {
	public final TokenType type;
	public final String lexeme;

	Token(TokenType type, String lexeme) {
		this.type = type;
		this.lexeme = lexeme;
	}

	@Override
	public String toString() {
		return String.format("[%s: %s]", type.toString(), lexeme);
	}

}
