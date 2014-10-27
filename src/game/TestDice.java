package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDice {

	
	@Test
	public void test() {
		int antalkast = 10000000;
		Dice a = new Dice();
		int[] liste = {0,0,0,0,0,0,0,0,0,0,0};
		for(int i=0; i<antalkast; i++){
			int b = a.roll();
			int c = a.roll();
		liste[b+c-2] = liste[b+c-2]+1;
		}
		
		for(int j=0; j<11; j++){
			System.out.println(liste[j]);
		}

		double[] nyliste = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
		double[] statestik = {1/36.0,2/36.0,3/36.0,4/36.0,5/36.0,6/36.0,5/36.0,4/36.0,3/36.0,2/36.0,1/36.0};
		for(int i=0; i<11; i++){
			double e = Math.pow((liste[i] - statestik[i]*antalkast),2);
			double f = (statestik[i]*antalkast);
			nyliste[i] = e/f;
		}
		double d = 0;
		for(int i=0; i<11; i++){
			d = d + nyliste[i];
		}
		System.out.println(d);
		if(d<18.31){
			fail("chi testen at vores test ikke er gyldig for et 5% siginifikansniveau");
		}
		
	}

}
