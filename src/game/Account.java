package game;

public class Account {
	private int balance;
	
	public Account(int balance) {
		this.balance = balance;
	}
	
	public void deposit(int value){
		if(value > 0){
			this.balance = this.balance + value;
		} else if (this.balance-value > 0){
			this.balance = this.balance + value;
		} else if (this.balance-value < 0){
			this.balance = 0;
		}
	}
}
