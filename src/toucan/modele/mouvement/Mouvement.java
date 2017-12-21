package toucan.modele.mouvement;

import java.awt.Color;

public abstract class Mouvement {
	protected int duree;
	protected int xDepart;
	protected int yDepart;
	protected int xArrivee;
	protected int yArrivee;
	protected int tempsDepart;
	protected int tempsArrivee;
	protected int valeur;
	protected Color couleur;
	
	/**
	 * Constructeur de Mouvement
	 * @param temps 
	 * @param duree du mouvement
	 * @param xDepart coordonnee en x au depart du mouvement
	 * @param yDepart coordonnee en y au depart du mouvement
	 */
	public Mouvement(int temps, int duree, int xDepart, int yDepart, int valeur, Color couleur) {
		assert(temps >= 0 && duree >= 0 && xDepart >= 0 && yDepart >= 0);
		tempsDepart = temps;
		tempsArrivee = temps + duree;
		this.duree = duree;
		this.xDepart = xDepart;
		this.yDepart = yDepart;
		this.valeur = valeur;
		this.couleur = couleur;
	}
	
	/**
	 * Methode qui retourne la valeur actuelle d'une case
	 * @return valeur valeur de la case
	 */
	public int getValeur() {
		return valeur;
	}
	
	/**
	 * Methode de retour des coordonnees en X du mouvement
	 * @return xArrivee
	 */
	public int getXArrivee() {
		return xArrivee;
	}
	
	/**
	 * Methode de retour des coordonnees en XYdu mouvement
	 * @return yArrivee
	 */
	public int getYArrivee() {
		return yArrivee;
	}
	
	/**
	 * Methode de retour du temps de depart du mouvement
	 * @return tempsDepart
	 */
	public int getTempsDepart() {
		return tempsDepart;
	}
	
	/**
	 * Methode de retour du temps a l'arrivee du mouvement
	 * @return tempsArrivee
	 */
	public int getTempsArrivee() {
		return tempsArrivee;
	}
	
	/**
	 * Methode qui retourne la couleur actuelle d'une case
	 * @return col couleur de la case
	 */
	public Color getCouleur() {
		return couleur;
	}
	
	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append("duree = ");
		strb.append(duree);
		strb.append(", xDepart = ");
		strb.append(xDepart);
		strb.append(", yDepart = ");
		strb.append(yDepart);
		strb.append(", xArrivee = ");
		strb.append(xArrivee);
		strb.append(", yArrivee = ");
		strb.append(yArrivee);
		strb.append(", tempsDepart = ");
		strb.append(tempsDepart);
		strb.append(", tempsArrivee = ");
		strb.append(tempsArrivee);
		return strb.toString();
	}
	
	public abstract int posX(int t);
	public abstract int posY(int t);
}
