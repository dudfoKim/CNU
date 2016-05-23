package OODhw04;

import java.io.*;
import java.util.Scanner;

public class Test {

	private static Scanner sc;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		sc = new Scanner(System.in);
		int num;
		String name, number, phone, email;

			while (true) {
				System.out.println("**** 1:입력, 2: 출력, etc:종료 ****");
				num = sc.nextInt();
				
					switch (num) {
					case 1:
						BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt",
								true));
						System.out.print("번호 : ");
						number = reader.readLine();
						System.out.print("이름 : ");
						name = reader.readLine();
						System.out.print("전화번호 : ");
						phone = reader.readLine();
						System.out.print("이메일주소 : ");
						email = reader.readLine();
						String item = String.format("%s|%s|%s|%s\n", number,
								name, phone, email);
						writer.write(item);						
						writer.close();					
						break;
					case 2:
						String a;
						BufferedReader reader2 = new BufferedReader(
								new FileReader("test.txt"));
						String line = "";
						System.out.print("출력할 번호입력 : ");
						a = sc.next();
						System.out.println("저장된 전화번호 : ");

						while ((line = reader2.readLine()) != null) {
							String[] temp = line.split("\\|");
							if (temp[0].equals(a)) {
								System.out.printf("%s\t\n", temp[2]);
							}
						}
						reader2.close();
						break;
					default:
						return;
					}
				
			}
		

	}
}
