package game;

public class Account {
	private int balance;
	
	public Account(int balance) {
		this.balance = balance;
	}
	
	public void setBalance(int balance){
		this.balance = balance;
	}
	
	public int getBalance(){
		return this.balance;
	}
	
	public void deposit(int value){
		if(value > 0){
			this.balance += value;
		} else if (this.balance-value > 0){
			this.balance += value;
		} else if (this.balance-value <= 0){
			this.balance = 0;
		}
	}
	
	public void withdraw(int value){
		if(this.balance-value > 0){
			this.balance -= Math.abs(value);
		} else{
			this.balance = 0;
		}
	}
}
