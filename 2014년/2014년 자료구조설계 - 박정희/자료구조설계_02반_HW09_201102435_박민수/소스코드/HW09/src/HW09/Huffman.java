package HW09;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Huffman {

	private MinHeap minHeap;
	public trecord root;

	public Huffman(MinHeap minHeap) {
		this.minHeap = minHeap;
	}

	public void createHuffmanTree() {
		trecord node;
		while (true) {
			node = new trecord();
			node.lchild = minHeap.removeMin();
			node.rchild = minHeap.removeMin();
			node.freq = (node.lchild.freq) + (node.rchild.freq);
			minHeap.insert(node);

			if (minHeap.getSize() == 1)
				break;
		}
		root = minHeap.removeMin();
	}

	public void printEncode() {
		System.out.println("***** 허프만 코드 *****");
		encode("", root);
	}

	private void encode(String code, trecord node) {
		if (node.lchild == null && node.rchild == null) {
			node.code = code;
			System.out.println(node.alphabet + " : " + node.code);
		} else {
			String rCode = code;
			encode((code += "0"), node.lchild);
			encode((rCode += "1"), node.rchild);
		}
	}

	public void encoding(File f) throws FileNotFoundException {
		String str;
		String str2 = "";
		Scanner sc = new Scanner(f);
		BufferedWriter out;

		while (sc.hasNextLine()) {
			str = sc.nextLine();
			str = str.toLowerCase();
			for (int i = 0; i < str.length(); i++) {
				char character = str.charAt(i);
				str2 += find(root, character);
			}
			System.out.println(str2);
			try {
				out = new BufferedWriter(new FileWriter("out.txt", true));
				out.write(str2);
				out.newLine();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			str2 = "";
		}

	}

	public String find(trecord temp, char a) {
		String str = "";
		if (temp.alphabet == a) {
			return temp.code;
		}
		if (temp.lchild != null)
			str += find(temp.lchild, a);
		if (temp.rchild != null)
			str += find(temp.rchild, a);
		return str;

	}

	public void decoding(File f) throws FileNotFoundException {
		trecord node = root;
		char a = '0';
		char b = '1';
		Scanner sc = new Scanner(f);

		while (sc.hasNextLine()) {
			String str = sc.nextLine();
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == a) {
					node = node.lchild;
					if (node.lchild == null && node.rchild == null) {
						System.out.print(node.alphabet);
						node = root;
					}
				} else if (str.charAt(i) == b) {
					node = node.rchild;
					if (node.lchild == null && node.rchild == null) {
						System.out.print(node.alphabet);
						node = root;
					}
				}
			}
			System.out.println();
		}
	}

}
