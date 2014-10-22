package game;

import java.util.Random;

public class Dice {
	private int die;
	private Random rnd = new Random();
	
	public int roll() {
		die = rnd.nextInt(6)+1;
		return die;
	}
	
	
}
