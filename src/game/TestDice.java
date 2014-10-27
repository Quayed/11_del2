package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDice {

	@Test
	public void test() {
		int numberOfThrows = 10000000;
		Dice dice = new Dice();
		int[] listForValuesOfDice = {0,0,0,0,0,0,0,0,0,0,0};
		for(int i=0; i<numberOfThrows; i++){
			int b = dice.roll();
			int c = dice.roll();
		listForValuesOfDice[b+c-2] = listForValuesOfDice[b+c-2]+1;
		}
		double[] listOfActualPropability = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
		double[] listOfCalculatedPropability = {1/36.0,2/36.0,3/36.0,4/36.0,5/36.0,6/36.0,5/36.0,4/36.0,3/36.0,2/36.0,1/36.0};
		for(int i=0; i<11; i++){
			double e = Math.pow((listForValuesOfDice[i] - listOfCalculatedPropability[i]*numberOfThrows),2);
			double f = (listOfCalculatedPropability[i]*numberOfThrows);
			listOfActualPropability[i] = e/f;
		}
		double d = 0;
		for(int i=0; i<11; i++){
			d = d + listOfActualPropability[i];
		}
		System.out.println(d);
		if(d<18.31){
			fail("chi testen at vores test ikke er gyldig for et 5% siginifikansniveau");
		}
		
	}

}
