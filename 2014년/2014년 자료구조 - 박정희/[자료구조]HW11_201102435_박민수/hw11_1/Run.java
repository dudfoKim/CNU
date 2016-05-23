/*201102435 ¹Ú¹Î¼ö*/

package hw11_1;

import java.util.ArrayList;

public class Run {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UnorderedTree treeA,treeB,treeD;
		UnorderedTree treeC = new UnorderedTree("C");
		UnorderedTree treeE = new UnorderedTree("E");
		UnorderedTree treeF = new UnorderedTree("F");
		UnorderedTree treeG = new UnorderedTree("G");
		
		ArrayList<UnorderedTree> subtreesofB = new ArrayList<UnorderedTree>();
		subtreesofB.add(treeE);
		subtreesofB.add(treeF);
		treeB = new UnorderedTree("B", subtreesofB);

		ArrayList<UnorderedTree> subtreesofD = new ArrayList<UnorderedTree>();
		subtreesofD.add(treeG);
		treeD = new UnorderedTree("D", subtreesofD);
		
		ArrayList<UnorderedTree> subtreesofA = new ArrayList<UnorderedTree>();
		subtreesofA.add(treeB);
		subtreesofA.add(treeC);
		subtreesofA.add(treeD);
		
		treeA = new UnorderedTree("A", subtreesofA);


		System.out.print("Post-OrDer : ");
		treeA.PostOrder();
		while(treeA.stack.size !=0)
			System.out.print(treeA.stack.pop() + " ");
		System.out.println();
		
		System.out.print("Pre-OrDer : ");
		treeA.PreOrder();
		while(treeA.stack.size !=0)
			System.out.print(treeA.stack.pop() + " ");
		
	}

}