package PLHW2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Scanner {
	private String source;
	private StringTokenizer st;

	public Scanner(String source) {
		this.source = source == null ? "" : source;
		// 문자열을 토큰화 해주는 클래스, " "을 delimiter로 함
		this.st = new StringTokenizer(this.source, " ");
	}

	private Token nextToken() {
		int state = 0;
		boolean errorState = false;
		// 토큰이 더 있는지 검사
		if (!st.hasMoreTokens())
			return null;
		// 그 다음 토큰을 받음
		String temp = st.nextToken();
		Token result = null;
		for (int i = 0; i < temp.length() && !errorState; i++) {
			switch (state) {
			case 0:
				if (Character.isDigit(temp.charAt(i)))
					state = 2;
				else if (temp.charAt(i) == '-')
					state = 1;
				else if (Character.isLetter(temp.charAt(i)))
					state = 3;
				else
					errorState = true;
				break;
			case 1:
				if(Character.isDigit(temp.charAt(i)))
					state = 2;
				else
					errorState = true;
				break;
			case 2:
				// 상태에 따라 state를 변경하도록 아래를 채우시오.
				if(Character.isDigit(temp.charAt(i)))
					state = 2;
				else
					errorState = true;
				break;
			case 3:
				// 상태에 따라 state를 변경하도록 아래를 채우시오.
				if(Character.isLetter(temp.charAt(i))| Character.isDigit(temp.charAt(i)))
					state = 3;
				else
					errorState = true;
				break;
			default:
				System.out.println("Case error: " + temp);
				return result;
			}
		}
		if (errorState) {
			System.out.println("acceptState error: " + temp);
			return result;
		}
		switch (state) {
		case 2:
			// 해당 상태에 맞는 토큰을 생성하여 반환
			Token t1 = new Token(TokenType.INT,temp);
			return t1;
		case 3:
			// 해당 상태에 맞는 토큰을 생성하여 반환
			Token t2 = new Token(TokenType.ID,temp);
			return t2;
		}
		return result;
	}

	public List<Token> tokenize() {
		List<Token> tokens = new ArrayList<Token>();
		Token t = null;
		t = nextToken();
		// 토큰 List를 반환하도록 작성
		while(t != null){
			
			tokens.add(t);
			t = nextToken();
		}
		return tokens;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader fr;
		try {
			fr = new FileReader("as02.txt");
			BufferedReader br = new BufferedReader(fr);
			String source = br.readLine();
			Scanner s = new Scanner(source);
			List<Token> tokens = s.tokenize();
			System.out.println(tokens);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
