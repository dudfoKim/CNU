package HW01;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;


public class PrintMain {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileReader fr = null;
		PrintWriter out = null;
		
		// 변수를 관리하기위한 Table 이라는 클래스를 사용.
		Table table = new Table();
		int i;
		try {
			// test.cw 파일을 읽어온다.
			fr = new FileReader("test.cw");
			// test.c 파일에 저장한다.
			out = new PrintWriter("test.c");
			BufferedReader br = new BufferedReader(fr);
			// test.cw 파일을 한줄씩 읽어온다.
			String source= br.readLine();
			
			// 기본적으로 구현되어야 하는 부분.
			out.println("#include <stdio.h>\n#include <stdlib.h>\n#include <string.h>\n");
			out.println("void main() {");
			out.println("		int i;");
			
			// 모든줄을 읽어올때까지 반복.
			while(source != null){
				// 읽어온 라인을 split을 이용하여 나누어서 배열에 저장.
				String[] tmp = source.split(" ");
				// 스위치문을 사용하여 각 명령어별로 처리.
				switch(tmp[0]){
				// def가 사용되었을때 
				case "def":
					// 뉴라인이 정의 되었을때 오류 메시지를 발생하고 프로그램을 종료한다.
					if(tmp[3].equals("\"\\n\""))
					{
						System.out.println("newline은 정의할 수 없습니다.");
						return;
					}
					// 해당하는 c코드로 변환하고 그것을 c파일에 작성한다.
					out.println("		char "+tmp[1]+"[] = "+tmp[3]+";");
					// 변수를 table에 따로 관리.
					table.insertTable(tmp[1], tmp[3]);
					break;
				// show가 사용되었을때
				case "show":
					// show 다음에 오는 것이 테이블에 존재하는 변수일 경우.  
					if(table.lookupTable(tmp[1]) != null && tmp.length == 2){
						out.println("		printf(\"%s\\n\", "+tmp[1]+");");
						break;
					}
					// 그렇지 않을경우.
					else{
						// show 다음에 정수가 들어오고, 배열의 크기가 3인경우.
						if(tmp[1].matches("-*[0-9]+") && tmp.length == 3){
							// 음수가 입력되었을경우 에러 메세지를 출력하고 프로그램을 종료한다.							
							if(Integer.parseInt(tmp[1])<0)
							{
								System.out.println("음수는 입력할수 없습니다.");
								return;
							}
							out.println("		for (i = 0; i < "+tmp[1]+"; i++)");
							out.println("			printf(\"%s\", "+tmp[2]+");");
							out.println("		printf(\"\\n\");");
							break;
						}else if (tmp.length == 2){	// 이 경우는 위에서 변수일 경우가 걸러지므로, 변수가 아닌 ""로 이루어진 출력문.
							out.println(" 		printf("+tmp[1]+");");
							out.println("		printf(\"\\n\");");
							break;
						}else {
							// show item1,item2,.......,itmeN일 경우.
							for(i=1; i<tmp.length; i++){
								String[] tmp2 = tmp[i].split(",");
								if(table.lookupTable(tmp2[0]) != null) {
									out.println("		printf(\"%s\", "+tmp2[0]+");"); 
								}else {
									out.println("		printf("+tmp2[0]+");"); 
								}
							}
							out.println("		printf(\"\\n\");"); 
							break;
						}
					}
				// showD 가 사용되었을때
				case "showD":
					// showD N Delimiter Item 인경우.
					if(tmp[1].matches("-*[0-9]+") && tmp.length == 4){
						// 음수일때 에러메시지를 출력하고 프로그램을 종료.
						if(Integer.parseInt(tmp[1])<0)
						{
							System.out.println("음수는 입력할수 없습니다.");
							return;
						}
						out.println("		for (i = 0; i < "+tmp[1]+"; i++) {");
						out.println("			printf(\"%s\", "+tmp[3]+");");
						out.println("			if(i<"+tmp[1]+"-1)");
						out.println("				printf("+tmp[2]+");");
						out.println("		}");
						out.println("		printf(\"\\n\");");
						break;
					}
					// showD Delimiter Item1,Item2,....,ItemN 일때
					else{
						for(i=2; i<tmp.length; i++){
							String[] tmp2 = tmp[i].split(",");
							if(table.lookupTable(tmp2[0]) != null) {
								out.println("		printf(\"%s\", "+tmp2[0]+");"); 
							}else {
								out.println("		printf("+tmp2[0]+");"); 
							}
							if(i<tmp.length - 1){
								out.println("		printf("+tmp[1]+");"); 
							}
						}
						out.println("		printf(\"\\n\");"); 
					}
					break;
				}
				// 다음줄을 읽어온다.
				source= br.readLine();
			}
			out.println("}");		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	// 파일 을 닫아줌.
			if(fr != null)
				fr.close();
			if(out != null)
				out.close();
		}
	}

}
