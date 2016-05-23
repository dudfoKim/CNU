package PLPROJECT;

import java.io.PrintStream;

public class Printer {
	PrintStream ps;

	public Printer(PrintStream ps) {
		this.ps = ps;
	}

	// Make your print class
	// ps.print(...)
	// node.getNext()
	// node.toString()
	// recursive call or iterate
	// void printNode
	// void printList
	// ¡¦ etc
	public void printNode(Node node) {

		if (node instanceof ListNode) {
			if (node.getNext() == null) {
				ps.print("( ");
				if (((ListNode) node).value != null)
					printNode(((ListNode) node).value);
				ps.print(" ) ");
			} else {
				ps.print("( ");
				if (((ListNode) node).value != null)
					printNode(((ListNode) node).value);
				ps.print(" ) ");
				printNode(node.getNext());

			}
		} else if (node instanceof IntNode) {
			if (node.getNext() == null) {
				ps.print("[" + ((IntNode) node).toString() + "] ");
			} else {
				ps.print("[" + ((IntNode) node).toString() + "] ");
				printNode(node.getNext());
			}
		} else if (node instanceof BinarayOpNode) {
			if (node.getNext() == null) {
				ps.print("[" + ((BinarayOpNode) node).toString() + "] ");
			} else {
				ps.print("[" + ((BinarayOpNode) node).toString() + "] ");
				printNode(node.getNext());
			}
		} else if (node instanceof BooleanNode) {
			if (node.getNext() == null) {
				ps.print("[" + ((BooleanNode) node).toString() + "] ");
			} else {
				ps.print("[" + ((BooleanNode) node).toString() + "] ");
				printNode(node.getNext());
			}
		} else if (node instanceof FunctionNode) {
			if (node.getNext() == null) {
				ps.print("[" + ((FunctionNode) node).toString() + "] ");
			} else {
				ps.print("[" + ((FunctionNode) node).toString() + "] ");
				printNode(node.getNext());
			}
		} else if (node instanceof IdNode) {
			if (node.getNext() == null) {
				ps.print("[" + ((IdNode) node).toString() + "] ");
			} else {
				ps.print("[" + ((IdNode) node).toString() + "] ");
				printNode(node.getNext());
			}
		} else if (node instanceof QuoteNode) {
			ps.print("` ");

			printNode(((QuoteNode) node).value);
		} else {
			System.out.print("ERROR");
			return;
		}
	}
}
