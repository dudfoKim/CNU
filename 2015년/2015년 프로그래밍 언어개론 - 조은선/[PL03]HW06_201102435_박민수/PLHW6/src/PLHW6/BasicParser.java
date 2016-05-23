package PLHW6;

import java.util.List;
import java.util.ListIterator;

import PLHW6.BinarayOpNode.BinType;
import PLHW6.FunctionNode.FunctionType;

public class BasicParser {
	private ListIterator<Token> iter;

	public BasicParser(List<Token> tokenList) {
		iter = tokenList.listIterator();
	}

	private void errorLog(String err) {
		System.out.println(err);
	}

	private Token getNextToken() {
		if (!iter.hasNext())
			return null;
		return iter.next();
	}

	public Node parseExpr() {
		Token t = getNextToken();
		if (t == null) {
			System.out.println("No more token");
			return null;
		}
		switch (t.type) {
		case ID:
			IdNode idNode = new IdNode();
			idNode.value = t.lexeme;
			return idNode;
		case INT:
			IntNode intNode = new IntNode();
			if (t.lexeme == null)
				System.out.println("???");
			intNode.value = new Integer(t.lexeme);
			return intNode; 
		// BinaryOpNode에 대하여 작성 
		// +, -, /, *가 해당
		case MINUS:
			BinarayOpNode minus = new BinarayOpNode();
			minus.value = BinarayOpNode.BinType.MINUS;
			return minus;
		case PLUS:
			BinarayOpNode plus = new BinarayOpNode();
			plus.value = BinarayOpNode.BinType.PLUS;
			return plus;
		case TIMES:
			BinarayOpNode times = new BinarayOpNode();
			times.value = BinarayOpNode.BinType.TIMES;
			return times;
		case DIV:
			BinarayOpNode div = new BinarayOpNode();
			div.value = BinarayOpNode.BinType.DIV;
			return div;
		case GT:
			BinarayOpNode gt = new BinarayOpNode();
			gt.value = BinarayOpNode.BinType.GT;
			return gt;
		case EQ:
			BinarayOpNode eq = new BinarayOpNode();
			eq.value = BinarayOpNode.BinType.EQ;
			return eq;
		case LT:
			BinarayOpNode lt = new BinarayOpNode();
			lt.value = BinarayOpNode.BinType.LT;
			return lt;
		case ATOM_Q:
			FunctionNode atom = new FunctionNode();
			atom.value = FunctionType.ATOM_Q;
			return atom; 
		// FunctionNode에 대하여 작성 
		// 키워드가 FunctionNode에 해당
		case DEFINE:
			FunctionNode define = new FunctionNode();
			define.value = FunctionType.DEFINE;
			return define; 
		case LAMBDA:
			FunctionNode lambda = new FunctionNode();
			lambda.value = FunctionType.LAMBDA;
			return lambda; 
		case COND:
			FunctionNode cond = new FunctionNode();
			cond.value = FunctionType.COND;
			return cond; 
		case NOT:
			FunctionNode not = new FunctionNode();
			not.value = FunctionType.NOT;
			return not; 
		case CDR:
			FunctionNode cdr = new FunctionNode();
			cdr.value = FunctionType.CDR;
			return cdr; 
		case CAR:
			FunctionNode car = new FunctionNode();
			car.value = FunctionType.CAR;
			return car; 
		case CONS:
			FunctionNode cons = new FunctionNode();
			cons.value = FunctionType.CONS;
			return cons; 
		case EQ_Q:
			FunctionNode eq_q = new FunctionNode();
			eq_q.value = FunctionType.EQ_Q;
			return eq_q; 
		case NULL_Q:
			FunctionNode null_Q = new FunctionNode();
			null_Q.value = FunctionType.NULL_Q;
			return null_Q; 
		case FALSE:
			BooleanNode falseNode = new BooleanNode();
			falseNode.value = false;
			return falseNode; 
		// BooleanNode에 대하여 작성 
		case TRUE:
			BooleanNode trueNode = new BooleanNode();
			trueNode.value = true;
			return trueNode;
		// L_PAREN일 경우
		// parseExprList()를 호출하여 처리
		case L_PAREN:
			ListNode listNode = new ListNode();
			listNode.value = parseExprList();
			return listNode;
		case R_PAREN:
			return null;
		case APOSTROPHE:
			ListNode apListNode = new ListNode();
			QuoteNode apQuoteNode = new QuoteNode();
			//apQuoteNode의 value를 set하시오.
			apListNode.value = apQuoteNode;
			apQuoteNode.value = parseExpr();
			return apListNode;
		case QUOTE: //QuoteNode를 반환하도록 작성하시오.
			QuoteNode q_node = new QuoteNode();
			q_node.value = parseExpr();
			return q_node;
		}
		System.out.println("Parsing Error!");
		return null;
	}

	private Node parseExprList() {
		Node head = parseExpr(); 
		// head의 next 노드를 set하시오.
		if(head != null){
			head.next = parseExprList();
		}
		return head;
	}
}
