package game;
import boundaryToMatador.GUI;
//import boundaryToMatador.GUI;
//hello
//hello again
//Swag
public class Game {

	public static void main(String[] args) {
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
		
		Player player1 = new Player("Mogens", 1);
		Account acc1 = new Account(1000, 1);
		
		Player player2 = new Player("Jens", 2);
		Account acc2 = new Account(1000, 1);
		
		GUI.addPlayer(player1.getName(), acc1.getBalance());
		GUI.addPlayer(player2.getName(), acc2.getBalance());
		while (true) {
			if (GUI.getUserButtonPressed("", "Slå!")!="") {
				if (turn == player1.getId()) {
					if (acc1.getBalance() >= 3000) {
						break;
					}
					GUI.removeAllCars(player1.getName());
					player1.setField(dice1.roll()+dice2.roll()-1);
					GUI.setCar(player1.getField(), player1.getName());
					acc1.deposit(Integer.parseInt(fieldCost[player1.getField()-1]));
					GUI.setBalance(player1.getName(), acc1.getBalance());
					if (player1.getField() != 9) {
						turn = 2;
					}
				}
				else if (turn == player2.getId()) {
					if (acc2.getBalance() >= 3000) {
						break;
					}
					GUI.removeAllCars(player2.getName());
					player2.setField(dice1.roll()+dice2.roll()-1);
					GUI.setCar(player2.getField(), player2.getName());
					acc2.deposit(Integer.parseInt(fieldCost[player2.getField()-1]));
					GUI.setBalance(player2.getName(), acc2.getBalance());
					if (player2.getField() != 9) {
						turn = 1;
					}
				}
			}
		}
		GUI.showMessage("Spiller "+turn+" har vundet!");
		
	}

}