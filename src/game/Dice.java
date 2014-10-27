package game;

import java.util.Random;

public class Dice {
	private int die;
	private Random rnd = new Random();
	
	public int roll() {
		die = rnd.nextInt(6)+1;
		return die;
	}
	
	public void setDie(int a){
		die = a;
	}
	
	public int getDie() {
		return die;
	}
	
	
}
