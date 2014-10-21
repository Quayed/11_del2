package game;

import java.util.Random;

public class Dice {
	private int dieOne,dieTwo;
	private Random rnd = new Random();
	
	public int roll() {
		dieOne = rnd.nextInt(6)+1;
		dieTwo = rnd.nextInt(6)+1;
		return dieOne+dieTwo;
	}
	
}
