package toucan.test;

import java.util.Iterator;

import toucan.modele.Modele;
import toucan.modele.cases.Case;
import toucan.modele.exceptions.LongueurChaineException;

public class TestToucan {

	public static void testTab() {
		Modele t = new Modele(new int[]{2, 1, 5, 4, 7, 8, 3, 0});
		int n = 0;
		Iterator<Case> i = t.iterator();
		
        while (i.hasNext()) {
        	if (n == 1) {
            	assert(i.next().getValeur() == 1):"Le tableau est mal initialise";
        	} else if (n == 6) {
                assert(i.next().getValeur() == 3):"Le tableau est mal initialise";
        	} else {
        		i.next();
        	}
        	n++;
        }
	}
	
	public static void testBuildTab() throws NumberFormatException, LongueurChaineException {
		Modele t = new Modele(new int[]{2, 1, 5, 4, 7, 8, 3, 0});
		
		t.buildTab("3,2,1,5");
		t.create(false);
		int n = 0;
		Iterator<Case> i = t.iterator();
		
        while (i.hasNext()) {
        	if (n == 1) {
            	assert(i.next().getValeur() == 2):"Le tableau est mal initialise";
        	} else if (n == 0) {
                assert(i.next().getValeur() == 3):"Le tableau est mal initialise";
        	} else {
        		i.next();
        	}
        	n++;
        }
        assert(t.getNbCases() == 5):"Erreur creation du tableau";
	}
	
	public static void testNbCases() {
		Modele t = new Modele(new int[]{2, 1, 5, 4, 7, 8, 3, 0});
		 
		assert(t.getNbCases() == 9):"Le nombre de cases est incorrect";
	}
	
	public static void testSelect() {
		Modele t = new Modele(new int[]{2, 1, 5, 4, 7, 8, 3, 0});
		
		t.setSelect("bulle");
		assert(t.getSelect() == "bulle"):"Selection de l'algo erronee";
		t.setSelect("insertion");
		assert(t.getSelect() != "bulle"):"Selection de l'algo erronee";
	}
	
	public static void testVitesse() {
		Modele t = new Modele(new int[]{2, 1, 5, 4, 7, 8, 3, 0});
		
		t.setVitesse(10);
		assert(t.getVitesse() == 10):"Vitesse erronee";
		t.setVitesse(2);
		assert(t.getVitesse() != 10):"Vitesse erronee";
	}
	
	public static void testPause() {
		Modele t = new Modele(new int[]{2, 1, 5, 4, 7, 8, 3, 0});

		assert(!t.getPause()):"Pause activee";
		t.setPause();
		assert(t.getPause() == true):"Pause non activee";
	}
	

	public static void main(String[] args) throws NumberFormatException, LongueurChaineException {
		testNbCases();
		testSelect();
		testVitesse();
		testPause();
		testTab();
		testBuildTab();
	}

}
