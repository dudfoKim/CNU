package Ds_HW07;

import java.io.*;
import java.util.StringTokenizer;

public class Run {

		public Run(String file){
			BST bst = new BST();
			AVLTree avl = null;
			Hash hash = new Hash();
			int i = 0;
			
			try{
				BufferedReader in = new BufferedReader(new FileReader(file));
				String line = in.readLine();
				while(line != null){
					StringTokenizer parser = new StringTokenizer(line, " ,:;-.?!");
					while(parser.hasMoreTokens()){
						
						String word = parser.nextToken().toUpperCase();
						String list = (String) hash.get(word);
						if(list == null) hash.put(word, 1);
						else hash.put(word, 0);
						bst.Add(word);
						if(i==0){
							avl = new AVLTree(word);
						}
						else{
							avl.add(word);
						}
						
						i++;
					}
					
					line = in.readLine();
				}
				in.close();
			} catch(IOException e){
				System.out.println(e);
			}
			System.out.println("***** Hash *****");
			hash.print();
			System.out.println("Å½»ö ½Ã°£: "+hash.collisions());
			
			System.out.println("***** AVLTree *****");
			avl.inorder();
			System.out.println("height: "+avl.height);
			System.out.println("Å½»ö ½Ã°£: "+avl.count);
			
			System.out.println("***** BST *****");
			bst.inOrder(bst.root);
			System.out.println("height: "+bst.height(bst.root));
			System.out.println("Å½»ö ½Ã°£: "+bst.count);
		
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Run("Caesar.txt");
	}

}
