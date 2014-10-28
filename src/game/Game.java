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
		
		//Lav betingelse p� sprog-indstilling
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		
		try {
			File file;
			GUI.create("fieldsDA.txt");
			String language = GUI.getUserButtonPressed("Vælg hvilket sprog du gerne vil have spillet på:", "Dansk", "Engelsk");
			if(language.equals("Engelsk")){
				GUI.close();
				GUI.create("fields.txt");
				file = new File("EN.txt");
			} else{
				file = new File("DA.txt");
			}
			
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);
			
			String lines;
			
			int c = 0;
			lines = dis.readLine();
			
			String[] line =  lines.split(":");
			
			fis.close();
			bis.close();
			dis.close();
			
			//Lav betingelse p� sprog-indstilling
			
			
			int turn = 1;
			Dice dice1 = new Dice();
			Dice dice2 = new Dice();
			final String[] fieldCost = {"250","-200","-100","-20","180","0","-70","-60","-80","-90","650"};
			for (int i=0; i<fieldCost.length; i++) {
				System.out.println(i);
				GUI.setSubText(i+1, fieldCost[i]);
			}
			GUI.showMessage(line[0]);
			
			Player player1 = new Player(GUI.getUserString(line[1]), 1);
			Account acc1 = new Account(1000, 1);
			GUI.addPlayer(player1.getName(), acc1.getBalance(), Color.BLUE);
			
			Player player2 = new Player(GUI.getUserString(line[2]), 2);
			Account acc2 = new Account(1000, 1);
			GUI.addPlayer(player2.getName(), acc2.getBalance(), Color.RED);
			
			while (true) {
				if (GUI.getUserButtonPressed(line[4]+turn+line[6], line[3])!="") {
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
			GUI.showMessage(line[4] + turn + line[5]);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
