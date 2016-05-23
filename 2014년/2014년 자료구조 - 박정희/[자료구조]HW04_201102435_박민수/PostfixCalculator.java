/*학번 201102435
    이름 박민수*/

package hw04_3;

public class PostfixCalculator {
	LinkedStack stack = new LinkedStack();
	String[] postfixExpression = null;
	
	PostfixCalculator(){
		
	}
	
	PostfixCalculator(String[] array){
		this.postfixExpression = array;
	}
	//후위표기를 계산하는 메소드
	public void RPN(){
		for(int i= 0; i<this.postfixExpression.length;i++){
			String temp = this.postfixExpression[i];
			if(isAnOperator(temp)){
				double y = Double.parseDouble((String)stack.pop());
				double x = Double.parseDouble((String)stack.pop());
				double z = evaluate(x,y,temp);
				stack.push(""+z);
			}
			else stack.push(temp);
		}
	}
	
	//연산자인지 피연산자인지 구분하는 메소드
	private boolean isAnOperator(String s){
		return (s.length() == 1 && "+-*/".indexOf(s) >= 0);
	}
	//연산자일경우 계산하기위한 메소드
	private double evaluate(double x, double y, String op){
		double z =0;
		if(op.equals("+")) z=x+y;
		else if(op.equals("-")) z=x-y;
		else if(op.equals("*")) z=x*y;
		else if(op.equals("/")) z=x/y;
		return z;
	}
}
