package toucan.modele.cases;

import toucan.modele.mouvement.LesMouvements;
import java.awt.Color;

public class Case {
	private LesMouvements mouvements;
	private int maxTemps;
	private int valeur;
	private Color color;
	
	/**
	 * Constructeur Case
	 * @param val valeur de la case 
	 */
	public Case(int val) {
		maxTemps = 0;
		valeur = val;
		color = Color.BLUE;
	}
	
	/**
	 * Methode d'initialisation des mouvements
	 * @param x coordonnees du mouvement en x
	 * @param y coordonnees du mouvement en y
	 */
	public void init(int x, int y) {
		mouvements = new LesMouvements(x, y, valeur);
	}
	
	/**
	 * Methode du mouvement a droite
	 * @param d duree du mouvement
	 * @exception AssertionError si d > 0
	 */
	public void droite(int d) {
		mouvements.droite(d);
		maxTemps += d;
	}
	
	/**
	 * Methode du mouvement a gauche
	 * @param d duree du mouvement
	 * @exception AssertionError si d > 0
	 */
	public void gauche(int d) {
		mouvements.gauche(d);
		maxTemps += d;
	}

	/**
	 * Methode du mouvement monter
	 * @param d duree du mouvement
	 * @exception AssertionError si d > 0
	 */
	public void monter(int d) {
		mouvements.monter(d);
		maxTemps += d;
	}
	

	/**
	 * Methode du mouvement descendre
	 * @param d durïee du mouvement
	 * @exception AssertionError si d > 0
	 */
	public void descendre(int d) {
		assert(d > 0);
		mouvements.descendre(d);
		maxTemps += d;
	}

	/**
	 * Methode du mouvement stable
	 * @param d duree du mouvement
	 * @exception AssertionError si d > 0
	 */
	public void stable(int d, int valeur, Color couleur) {
		assert(d >= 0);
		mouvements.stable(d, valeur, couleur);
		maxTemps+= d;
	}

	/**
	 * Methode qui retourne la position en x de la case
	 * @param t temps correspondant a la position actuelle de la case
	 * @return mouvements.posX(t) : position de la case au temps t
	 * @exception AssertionError si t >= 0 && t <= maxTemps
	 */
	public int posX(int t) {
		assert(t >= 0 && t <= maxTemps);
		return mouvements.posX(t);
	}
	
	/**
	 * Methode qui retourne la position en y de la case
	 * @param t temps correspondant a la position actuelle de la case
	 * @return mouvements.posY(t) : position de la case au temps t
	 * @exception AssertionError si t >= 0 && t <= maxTemps
	 */
	public int posY(int t) {
		assert(t >= 0 && t <= maxTemps);
		return mouvements.posY(t);
	}
	
	/**
	 * Methode qui retourne la valeur d'une case au temps actuel
	 * @param t intervalle de temps pour lequel la valeur d'une case est demande
	 * @return mouvements.getValeur(t) valeur d'une case au temps t
	 */
	public int getValeur(int t) {
		assert(t >= 0 && t <= maxTemps);
		return mouvements.getValeur(t);
	}
	
	/**
	 * Methode qui initialise la couleur d'une case
	 * @param color Couleur de la case 
	 */
	public void setColor(Color color) {
		assert(color != null);
		this.color = color;
	}
	
	/**
	 * Methode qui retourne la couleur d'une case
	 * @param t intervalle de temps pour lequel la couleur de la case est demande
	 */
	public Color getColor(int t) {
		assert(t >= 0);
		return mouvements.getColor(t);
	}

	/**
	 * Methode de retour du temps maximum
	 * @return maxTemps
	 */
	public int getMaxTemps() {
		return maxTemps;
	}
	
	/**
	 * Methode de retour de la valeur d'une case
	 * @return valeur valeur de la case 
	 */
	public int getValeur() {
		return valeur;
	}
	
	@Override
	public String toString() {
		return mouvements.toString();
	}
}
