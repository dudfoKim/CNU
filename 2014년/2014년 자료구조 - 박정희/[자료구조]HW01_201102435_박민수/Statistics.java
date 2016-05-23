package sub01;

public class Statistics extends Section
{
	String subject;
	double midterm;
	double finals;
	int num;
	int midPercent = 40;
	int finalPercent = 60;

	Statistics(String subject){
		this.subject = subject;		
	}
	Statistics(String subject, double mid, double finals)
	{
		this.subject = subject;
		this.midterm = mid;
		this.finals = finals;
	
	}
	/*Getter & Setter*/
	public int getNum(){
		return num;
	}
	public void setNum(int num){
		this.num = num;
	}
	public double getMidterm(){
		return midterm;
	}
	public void setMidterm(double mid){
		this.midterm = mid;
	}
	public double getFinals(){
		return finals;
	}
	public void setFinals(double finals){
		this.finals = finals;
	}	
	/*객체 정보 출력*/
	public void printInfo(){
		System.out.println("과목명 : " +subject);
		System.out.println("수강인원 : " +num);
		System.out.println("학습평가기준 : 중간고사 40% 기말고사 60% ");
		
		calcScore();
		printScore();
	}
	/*점수 정보 출력*/
	public void printScore(){
		System.out.println("중간고사 " +midterm+ " 기말고사"+finals+ " 환산점수 : "+(midPercent+finalPercent)+"\n");
	}
	/*점수 계산*/
	public void calcScore(){
		midPercent = (int)(midterm*0.4);
		finalPercent = (int)(finals*0.6);
	}
		
}
