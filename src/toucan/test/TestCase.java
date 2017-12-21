package toucan.test;

import java.awt.Color;
import java.util.Iterator;

import toucan.modele.Modele;
import toucan.modele.cases.Case;

public class TestCase {

	public static void testPosX() {
		Modele t = new Modele(new int[]{2, 1, 5, 4, 7, 8, 3, 0});
		
		Iterator<Case> i = t.iterator();
		int n = 0;
		
		while (n <= 350) {
			assert(i.next().posX(0) == n):"Mauvaise position de la case en X au temps 0"; //Test de la position de base en X de la case
			n+=50;
		}
	}
	
	public static void testPosY() {
		Modele t = new Modele(new int[]{2, 1, 5, 4, 7, 8, 3, 0});
		
		Iterator<Case> i = t.iterator();
		int n = 0;
		
		while (n < t.getNbCases() - 1) {
			assert(i.next().posY(0) == 0):"Mauvaise position de la case en Y au temps 0"; //Test de la position de base en X de la case
			n++;
		}
	}
	
	public static void testMouvement() {
		Modele t = new Modele(new int[]{2, 1, 5, 4, 7, 8, 3, 0});
		
		Iterator<Case> i = t.iterator();
		Case c = i.next();
		
		c.descendre(200); //Application d'un mouvement à la case pour changer ses coo
		assert(c.posY(200) == 200):"La case n'a pas la bonne coo en Y au temps 200";
		assert(c.posX(200) == 0):"La case n'a pas la bonne coo en X au temps 200";
		
		c.gauche(100);
		assert(c.posY(300) == 200):"La case n'a pas la bonne coo en Y au temps 300";
		assert(c.posX(300) == -100):"La case n'a pas la bonne coo en X au temps 300";
	}

	public static void testStable() {
		Modele t = new Modele(new int[]{2, 1, 5, 4, 7, 8, 3, 0});
		
		Iterator<Case> i = t.iterator();
		Case c = i.next();
		
		//Test des proprietes de la case avant le changement
		assert(c.posX(0) == 0):"Mauvaises coo en X au temps 0";
		assert(c.posY(0) == 0):"Mauvaises coo en Y au temps 0";
		assert(c.getColor(0) == Color.BLUE):"Mauvaises couleur de la case au temps 0";
		assert(c.getValeur(0) == 2):"Mauvaises valeur de la case au temps 0";
		c.stable(200, 9, Color.yellow); //On change la valeur et la couleur de la case au temps 200
		//Test des proprietes de la case apres le changement
		assert(c.posX(200) == 0):"Mauvaises coo en X au temps 200";
		assert(c.posY(200) == 0):"Mauvaises coo en Y au temps 200";
		assert(c.getColor(200) == Color.YELLOW):"Mauvaises couleur de la case au temps 200";
		assert(c.getValeur(200) == 9):"Mauvaises valeur de la case au temps 200";
		
	}
	
	public static void main(String[] args) {
		testPosX();
		testPosY();
		testMouvement();
		testStable();
	}

}
