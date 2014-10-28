package test;

import static org.junit.Assert.*;
import game.*;

import org.junit.Test;

public class TestGame {

	@Test
	public void test() {
		int numberOfThrows = 10000000;
		Dice dice = new Dice();
		int[] listForValuesOfDice = {0,0,0,0,0,0,0,0,0,0,0};
		double[] listOfActualPropability = {0,0,0,0,0,0,0,0,0,0,0};
		double[] listOfCalculatedPropability = {1/36.0,2/36.0,3/36.0,4/36.0,5/36.0,6/36.0,5/36.0,4/36.0,3/36.0,2/36.0,1/36.0};
		double d = 0;
		
		for(int i = 0; i < numberOfThrows; i++){
			int b = dice.roll();
			int c = dice.roll();
			listForValuesOfDice[b+c-2] = listForValuesOfDice[b+c-2]+1;
		}
		
		for(int i = 0; i < 11; i++){
			double e = Math.pow((listForValuesOfDice[i] - listOfCalculatedPropability[i]*numberOfThrows),2);
			double f = (listOfCalculatedPropability[i]*numberOfThrows);
			listOfActualPropability[i] = e/f;
		}
		
		for(int i = 0; i < 11; i++){
			d = d + listOfActualPropability[i];
		}
		
		if(d > 18.31){
			fail("chi testen at vores test ikke er gyldig for et 5% siginifikansniveau");
		}
	}
	
	@Test
	public void pointtest() {
		int[] fieldCost = {250,-200,-100,-20,180,0,-70,-60,-80,-90,650};
		Dice Pointdieone = new Dice();
		Dice Pointdietwo = new Dice();
		Account Player = new Account(0,1);
		
		for(int i = 1; i <= 6; i++) {
			for(int j = 1; j <= 6; j++) {
				Player.setBalance(1000);
				Pointdieone.setDie(i);
				Pointdietwo.setDie(j);
				Player.deposit(fieldCost[Pointdieone.getDie() + Pointdietwo.getDie() - 2]);
				
				if(Player.getBalance() != fieldCost[Pointdieone.getDie() + Pointdietwo.getDie() - 2] + 1000 ) {
					fail("balance virker ikke korrekt");
				}
			}
		}
	}
}
