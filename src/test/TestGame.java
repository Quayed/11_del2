package test;

import static org.junit.Assert.*;

import java.awt.Color;

import game.*;

import org.junit.Test;

import boundaryToMatador.GUI;

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
	
	@Test
	public void runtest() {
		int a = 0;
		int b = 0;
		int n = 0;
		int c = 0;
		int m = 10;
		int t = 0;
		int u = 100;
		int s = 0;
		String[] run = new String[500];
		for(int j = 0; j <= m; j++) {
			c = 0;
			Language language = new Language();
			language.setLanguage("Dansk");
			String [] line = language.loadLanguageFile();
			
			GUI.create("fieldsDA.txt");
			
			int turn = 1;
			Dice dice1 = new Dice();
			Dice dice2 = new Dice();
			final String[] fieldCost = {"250","-200","-100","-20","180","0","-70","-60","-80","-90","650"};
			for (int i=0; i < fieldCost.length; i++) {
				GUI.setSubText(i+1, fieldCost[i]);
			}
			
			Player player1 = new Player("Spiller 1", 1);
			Account acc1 = new Account(1000, 1);
			GUI.addPlayer("Spiller 1", acc1.getBalance(), Color.BLUE);
			
			Player player2 = new Player("Spiller 2", 2);
			Account acc2 = new Account(1000, 1);
			GUI.addPlayer("Spiller 2", acc2.getBalance(), Color.RED);
			
			while (true) {
				if (true) {
					if (turn == player1.getId()) {
						GUI.removeAllCars(player1.getName());
						GUI.setDice(dice1.roll(), 0, 4, 3, dice2.roll(), 0, 5, 3);
						player1.setField(dice1.getDie()+dice2.getDie()-1); 
						GUI.setCar(player1.getField(), player1.getName());
						acc1.deposit(Integer.parseInt(fieldCost[player1.getField()-1]));
						GUI.setBalance(player1.getName(), acc1.getBalance());
						if (acc1.getBalance() >= 3000) {
							break;
						}
						if (player1.getField() != 9) {
							turn = 2;
						}
						run[j] += ": Spiller 1 slog " + (dice1.roll() + dice2.roll());
						c++;
					}
					else if (turn == player2.getId()) {
						GUI.removeAllCars(player2.getName());
						GUI.setDice(dice1.roll(), 0, 4, 3, dice2.roll(), 0, 5, 3);
						player2.setField(dice1.getDie()+dice2.getDie()-1); 
						GUI.setCar(player2.getField(), player2.getName());
						acc2.deposit(Integer.parseInt(fieldCost[player2.getField()-1]));
						GUI.setBalance(player2.getName(), acc2.getBalance());
						if (acc2.getBalance() >= 3000) {
							break;
						}
						if (player2.getField() != 9) {
							turn = 1;
						}
						run[j] += ":Spiller 2 slog " + (dice1.roll() + dice2.roll());
						c++;
					}
				}
			}
			if(turn != 1 && turn != 2) {
				fail("Spillet sluttede men hverken spiller 1 eller spiller 2 har vundet");
			}
			if(turn == 1 && !(acc1.getBalance() >= 3000)) {
				fail("Spiller 1 vandt men balancen var ikke på 3000 eller over");
			}
			if(turn == 2 && !(acc2.getBalance() >= 3000)) {
				System.out.println("Spiller " + turn + " vandt");
				System.out.println(acc2.getBalance());
				System.out.print("Antal gennemløb " + c);
				
				fail("Spiller 2 vandt men balancen var ikke på 3000 eller over");
			}
			n += c;
			if(turn == 1)
				a++;
			if(turn == 2)
				b++;
			if(c < u)
				u = c;
			if(c > t) {
				s = j;
				t = c;
			}
			
			System.out.println(j + ", " + c);
			
		}
		n = n/m;
		System.out.println("Spiller 1 vandt " + a + " gange");
		System.out.println("Spiller 2 vandt " + b + " gange");
		System.out.println("Gennemsnitslig antal kast per spil: " + n);
		System.out.println("Laveste antal kast: " + u);
		System.out.println("Forløb for korteste spil " + run[s]);
		System.out.println("Højeste antal kast: " + t);
	}
}