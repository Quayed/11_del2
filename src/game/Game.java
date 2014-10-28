package game;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//hello
import boundaryToMatador.GUI;

public class Game {

	public static void main(String[] args) {
		
		// Language Section
		File file = new File("test.txt");
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);
			
			String[] linjer = new String[3];
			
			int c = 0;
			while (dis.available() != 0) {
				linjer[c] = dis.readLine();
				c++;
			}
			//Display af loadede sætninger
			for(int i = 0; i <= linjer.length-1; i++) {
				System.out.println(linjer[i]);
			}
			
			fis.close();
			bis.close();
			dis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Language Section End
		
		GUI.create("fields.txt");
		int turn = 1;
		Dice dice1 = new Dice();
		Dice dice2 = new Dice();
		final String[] fieldCost = {"250","-200","-100","-20","180","0","-70","-60","-80","-90","650"};
		for (int i=0; i<fieldCost.length; i++) {
			System.out.println(i);
			GUI.setSubText(i+1, fieldCost[i]);
		}
		GUI.showMessage("Game has begun");
		
		Player player1 = new Player(GUI.getUserString("Indtast spiller 1's navn:"), 1);
		Account acc1 = new Account(1000, 1);
		GUI.addPlayer(player1.getName(), acc1.getBalance(), Color.BLUE);
		
		Player player2 = new Player(GUI.getUserString("Indtast spiller 2's navn:"), 2);
		Account acc2 = new Account(1000, 1);
		GUI.addPlayer(player2.getName(), acc2.getBalance(), Color.RED);
		
		while (true) {
			if (GUI.getUserButtonPressed("Spiller "+turn+"'s tur.", "Slå!")!="") {
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
				}
			}
		}
		GUI.showMessage("Spiller "+turn+" har vundet!");
		
	}

}
