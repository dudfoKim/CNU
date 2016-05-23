package sub01;

public class DS extends Section
{
	String subject;	//과목명
	double midterm;	//중간점수
	double finals;	//기말점수
	double practice_score;	//실습점수
	int num;	//인원수
	/*학습평가기준*/
	int midPercent = 40;		//중간고사
	int finalPercent = 40;	//기말고사
	int paracPercent = 20;	//실습평가

	DS(String subject){
		this.subject = subject;		
	}
	DS(String subject, double mid, double finals,double prac)
	{
		this.subject = subject;
		this.midterm = mid;
		this.finals = finals;
		this.practice_score = prac;
		
	
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
	public double getPractice_score(){
		return practice_score;
	}
	public void setPractice_score(double practice_score){
		this.practice_score = practice_score;
	}
	/*객체 정보 출력*/
	public void printInfo(){
		System.out.println("과목명 : " +subject);
		System.out.println("수강인원 : " +num);
		System.out.println("학습평가기준 : 중간고사 40% 기말고사 40% 실습평가 20%");
		
		calcScore();
		printScore();
	}
	/*점수 정보 출력*/
	public void printScore(){
		System.out.println("중간고사 " +midterm+ " 기말고사"+finals+" 실습점수 : " +practice_score+ " 환산점수 : "+(midPercent+finalPercent+paracPercent)+"\n");
	}
	/*점수 계산*/
	public void calcScore(){
		midPercent = (int)(midterm*0.4);
		finalPercent = (int)(finals*0.4);
		paracPercent = (int)(practice_score*0.2);
	}
}
