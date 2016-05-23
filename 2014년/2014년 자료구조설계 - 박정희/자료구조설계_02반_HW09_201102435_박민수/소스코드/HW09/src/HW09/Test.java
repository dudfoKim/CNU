package HW09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		File file = null,file2 = null;
		Scanner scanner = null;

		int size = 27;
		int frequency[] = new int[size];

		try {
			file = new File("test.txt");
			file2 = new File("out.txt");
			scanner = new Scanner(file);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
		}

		String str;
		System.out.println("***** " + file.getName() + " *****");
		while (scanner.hasNextLine()) {
			str = scanner.nextLine();
			for (int i = 0; i < str.length(); i++) {
				char character = str.charAt(i);
				if (character >= 'a' && character <= 'z')
					frequency[character - 97]++;
				else if (character == ' ')
					frequency[size - 1]++;
			}
			System.out.println(str);
		}

		System.out.println("\n***** 빈도수 ******");
		MinHeap minHeap = new MinHeap(30);
		for (int i = 0; i < size; i++) {
			if (frequency[i] > 0) {
				trecord node;
				if (i != 26) {
					node = new trecord((char) (i + 97), frequency[i], null,null);
					System.out.print((char) (i + 97) + "(" + frequency[i]
							+ ") "); // 문자별 빈도수 출력
				} else {
					node = new trecord(' ', frequency[i], null, null);
					System.out.println("space(" + frequency[i] + ") ");
				}
				minHeap.insert(node);
			}
		}
		System.out.println();

		Huffman hoffman = new Huffman(minHeap);
		hoffman.createHuffmanTree();
		hoffman.printEncode();
		System.out.println();
		System.out.println("***** 인코딩 *****");
		try {
			hoffman.encoding(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("***** 디코딩 *****");
		try {
			hoffman.decoding(file2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
