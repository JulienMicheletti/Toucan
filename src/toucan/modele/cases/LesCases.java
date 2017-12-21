package toucan.modele.cases;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class LesCases implements Iterable<Case> {
	private ArrayList<Case> cases;
	private int maxTemps;
	
	/**
	 * Constructeur de la class LesCases
	 * @param tab tableau qui contient les valeurs des cases
	 */
	public LesCases(int[] tab) {
		assert(tab != null);
		cases = new ArrayList<Case>(tab.length);
		for(int i = 0; i < tab.length; i++) {
			ajouterCase(new Case(tab[i]));
		}
		maxTemps = 0;
	}
	
	/**
	 * Methode qui retourne la position en X d'une case au temps t
	 * @param nCase index de la case
	 * @param t temps auquel on demande la coordonnee 
	 * @return cases.get(nCase).posX(t)
	 */
	public int posX(int nCase, int t) {
		assert(t >= 0 && t <= maxTemps && nCase >= 0 && nCase <= cases.size());
		return cases.get(nCase).posX(t);
	}
	
	/**
	 * Methode qui retourne la position en Y d'une case au temps t
	 * @param nCase index de la case
	 * @param t temps auquel on demande la coordonnee 
	 * @return cases.get(nCase).posY(t)
	 */
	public int posY(int nCase, int t) {
		assert(t >= 0 && t <= maxTemps && nCase >= 0 && nCase <= cases.size());
		return cases.get(nCase).posY(t);
	}
	
	/**
	 * Methode qui retourne la valeur d'une case au temps t
	 * @param nCase index de la case
	 * @param t temps auquel on demande la coordonnee 
	 * @return cases.get(nCase).getValeur(t)
	 */
	public int getValeur(int nCase, int t) {
		assert(t >= 0 && t <= maxTemps && nCase >= 0 && nCase <= cases.size());
		return cases.get(nCase).getValeur(t);
	}
	
	/**
	 * Methode qui initialise la couleur d'une case
	 * @param nCase index de la case
	 * @param color Couleur demandee
	 */
	public void setColor(int nCase, Color color) {
		assert(color != null && nCase >= 0 && nCase <= cases.size());
		cases.get(nCase).setColor(color);
	}
	
	/**
	 * Methode d'ajout d'une case
	 * @param c Case a ajouter
	 * @exception AssertionError si c != null
	 */
	public void ajouterCase(Case c) {
		assert(c != null);
		cases.add(c);
	}
	
	/**
	 * Methode qui retourne le nombre de cases du tableau
	 * @return cases.size() taille du tableau de cases
	 */
	public int getNbCases() {
		return cases.size();
	}
	
	
	/**
	 * Methode qui positionne une case aux coordonnees demandees 
	 * @param noCase index de la case
	 * @param x coordonnees de la case en X
	 * @param y coordonnees de la case en Y
	 */
	public void setPosition(int noCase, int x, int y) {
		assert(noCase >= 0 && noCase <= cases.size());
		Case c = cases.get(noCase);
		c.init(x, y);
	}
	
	/**
	 * Methode d'appel du mouvement a droite
	 * @param d duree du mouvement
	 * @param nCase index de la case ciblee
	 * @exception AssertionError si nCase >= 0 && nCase <= cases.size() && d > 0
	 */
	public void droite(int nCase, int d) {
		assert(nCase >= 0 && nCase <= cases.size() && d >= 0);
		Case c = cases.get(nCase);
		c.droite(d);
		updateMaxTemps(c);
	}
	
	/**
	 * Methode d'appel du mouvement a gauche
	 * @param d duree du mouvement
	 * @param nCase index de la case ciblee
	 * @exception AssertionError si nCase >= 0 && nCase <= cases.size() && d > 0
	 */
	public void gauche(int nCase, int d) {
		assert(nCase >= 0 && nCase <= cases.size() && d >= 0);
		Case c = cases.get(nCase);
		c.gauche(d);
		updateMaxTemps(c);
	}
	
	/**
	 * Methode d'appel du mouvement monter
	 * @param d duree du mouvement
	 * @param nCase index de la case ciblee
	 * @exception AssertionError si nCase >= 0 && nCase <= cases.size() && d > 0
	 */
	public void monter(int nCase, int d) {
		assert(nCase >= 0 && nCase <= cases.size() && d >= 0);
		Case c = cases.get(nCase);
		c.monter(d);
		updateMaxTemps(c);
	}
	
	/**
	 * Methode d'appel du mouvement descendre
	 * @param d duree du mouvement
	 * @param nCase index de la case ciblee
	 * @exception AssertionError si nCase >= 0 && nCase <= cases.size() && d > 0
	 */
	public void descendre(int nCase, int d) {
		assert(nCase >= 0 && nCase <= cases.size() && d >= 0);
		Case c = cases.get(nCase);
		c.descendre(d);
		updateMaxTemps(c);
	}
	
	/**
	 * Methode d'appel du mouvement stable
	 * @param d duree du mouvement
	 * @param nCase index de la case ciblee
	 * @exception AssertionError si nCase >= 0 && nCase <= cases.size() && d > 0
	 */
	public void stable(int nCase, int d, int valeur, Color color) {
		assert(nCase >= 0 && nCase <= cases.size() && d >= 0);
		Case c = cases.get(nCase);
		c.stable(d, valeur, color);
		updateMaxTemps(c);
	}
	
	/**
	 * Methode de mise a jours du temps maximum
	 * @param c Case pour laquelle la duree maximum est calculee
	 */
	private void updateMaxTemps(Case c) {
		assert(c != null);
		int temps = c.getMaxTemps();
		if (temps > maxTemps) {
			maxTemps = temps;
		}
	}
	
	/**
	 * Methode de retour du temps maximum
	 * @return maxTemps
	 */
	public int getMaxTemps() {
		return maxTemps;
	}
	
    @Override
    public String toString() {
        int maxTemps = getMaxTemps() ;
        StringBuilder sb = new StringBuilder() ;
        for (int i = 0 ; i < cases.size() ; i++) {
            sb.append("Case " + i + ": ") ;
            Case c = cases.get(i) ;
            for (int t = 0 ; t <= maxTemps ; t++) {
                int x = c.posX(t) ;
                int y = c.posY(t) ;
                sb.append("\t" + t + " (" + x + "," + y + ") [" + c.getValeur(t) + "]") ;
                if (t % 5 == 0) {
                    sb.append("\n\t") ;
                }
            }
            sb.append("\n") ;
        }
        return sb.toString() ;
    }

	@Override
	public Iterator<Case> iterator() {
		return cases.iterator();
	}
}
