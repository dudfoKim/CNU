/*학번 201102435
    이름 박민수*/

package hw04_3;



import java.util.Scanner;

public class Run {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		/* 0~9사이의 정수값만 입력받는다*/
		System.out.println("Input infix expression : ");
		String[] input = sc.nextLine().split("\\s+");
		
		//중위표기를 후위표기로 변환
		InfixToPostfix infix = new InfixToPostfix();
		infix.setInfix(input);
		infix.ToPostfix();
		
		String[] postfix = new String[infix.ToPostfix().length];		
		for(int i = 0; i<postfix.length;i++){
			postfix[i] = infix.Postfix[i];
		}
		
		PostfixCalculator p = new PostfixCalculator(postfix);
		//후위표기계산기로 계산
		p.RPN();
		System.out.println("Postfix expression : ");
		for(int i =0 ; i<postfix.length; i++){
			System.out.print(postfix[i]+" ");
		}
		
		System.out.println("\n==========계산 결과 출력=============\n");
		
		for(int i = 0;i<postfix.length;i++){
			System.out.print(postfix[i]+" ");
		}
		System.out.println(" = " +p.stack.pop());
	
	
	}

}


