package game;
import boundaryToMatador.GUI;
public class Game {

	public static void main(String[] args) {
		GUI.create("fields.txt");
		final String[] fieldCost = {"0","250","-200","-100","-20","180","0","-70","-60","-80","-90","650"};
		
		for (int i=1; i<fieldCost.length; i++) {
			System.out.println(i);
			GUI.setSubText(i, fieldCost[i]);
		}
		GUI.showMessage("Game has begun");
	}

}
