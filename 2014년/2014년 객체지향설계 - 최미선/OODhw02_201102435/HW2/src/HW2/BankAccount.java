package HW2;

public class BankAccount {
	private String name;
	private int accountNumber;
	private int balance;
	private int interestRates;
	
	/***디폴트 생성자***/
	BankAccount(){
		
	}
	
	BankAccount(String name, int number, int balance, int rates){
		this.setName(name);
		this.setAccountNumber(number);
		this.setBalance(balance);
		this.setInterestRates(rates);
	}
	
	public int deposit(int money){
		this.setBalance(this.getBalance() + money);
		
		return this.balance;
	}
	
	public int withdraw(int money) throws NegativeBalanceException{
		if(money > this.balance){
			throw new NegativeBalanceException();
		}
		this.setBalance(this.getBalance() - money);
		return this.balance;
	}
	
	

	/**** getter & setter ******/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getInterestRates() {
		return interestRates;
	}

	public void setInterestRates(int interestRates) {
		this.interestRates = interestRates;
	}

}
