package sub01;

import java.util.ArrayList;
import java.util.Iterator;


public class Run {

	public static void main(String[] args) {
		

		
		/* 학생 1  */
		ArrayList<Section> student1 = new ArrayList<Section>();
		
		DS ds1 = new DS("자료구조", 10.0, 10.0, 10.0);
		CP cp1 = new CP("컴퓨터 프로그래밍", 20.0, 20.0, 20.0);
		Statistics st1 = new Statistics("통계", 40.0, 40.0);
		
		student1.add(ds1);
		student1.add(cp1);
		student1.add(st1);	
		
		/*수강인원 입력*/
		ds1.setNum(3);
		cp1.setNum(3);
		st1.setNum(3);
	
		
		
		/*	학생2 */
		ArrayList<Section> student2 = new ArrayList<Section>();
		
		DS ds2 = new DS("자료구조", 15.0, 20.0, 50.0);
		CP cp2 = new CP("컴퓨터 프로그래밍", 60.0, 70.0, 60.0);
		Statistics st2 = new Statistics("통계", 80.0, 70.0);
		
		student2.add(ds2);
		student2.add(cp2);
		student2.add(st2);	
		
		/*수강인원 입력*/
		ds2.setNum(3);
		cp2.setNum(3);
		st2.setNum(3);
		
	
		/*	학생3  */

		ArrayList<Section> student3 = new ArrayList<Section>();
		
		DS ds3 = new DS("자료구조", 80.0, 70.0, 90.0);
		CP cp3 = new CP("컴퓨터 프로그래밍", 70.0, 90.0, 80.0);
		Statistics st3 = new Statistics("통계", 80.0, 90.0);
		
		student3.add(ds3);
		student3.add(cp3);
		student3.add(st3);		
		
		/*수강인원 입력*/
		ds3.setNum(3);
		cp3.setNum(3);
		st3.setNum(3);

			
		/*	Student Arraylist*/
		ArrayList<Student> studentList = new ArrayList<Student>();
		
		studentList.add(new Student("홍길동", 201400001, student1));
		studentList.add(new Student("임꺽정", 201400002, student2));
		studentList.add(new Student("박민수", 201102435, student3));
	
		
			
				
		/*	iterator 를 이용하여 출력	*/
		Iterator <Student>it = studentList.iterator();
		while(it.hasNext())
		{
			it.next().printSubjectScore();
			System.out.println(" ");
		}
	}
	/*PPT상의 결과화면에는 수강인원하고 평가기준이 출력안되있는 화면으로 나와있는데PPT안에 내용중에 
	   구현하라는 이야기가 나와있어서 구현했습니다.*/

}
