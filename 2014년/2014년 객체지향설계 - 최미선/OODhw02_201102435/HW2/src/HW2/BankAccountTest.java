package HW2;

import java.util.Scanner;

public class BankAccountTest {

	private static Scanner sc;

	public static void main(String[] args) {
		
		int money = 0;
		sc = new Scanner(System.in);
		
		
		BankAccount bank = new BankAccount("박민수", 81540, 10000, 1);
		System.out.println("***** 계좌 정보 *****");
		System.out.println("이름 : " + bank.getName());
		System.out.println("계좌번호 : " + bank.getAccountNumber());
		System.out.println("잔액 : " + bank.getBalance());
		
		System.out.println("원하는 금액입금 : ");
		money = sc.nextInt();
		bank.deposit(money);
		System.out.println("입금후 잔액 : " + bank.getBalance());
		
		System.out.println("원하는 금액 출금 : ");
		money = sc.nextInt();
		try {
			bank.withdraw(money);
		} catch (NegativeBalanceException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			System.out.println("잔액 : " + bank.getBalance());
		}
		
		
		
	}

}
