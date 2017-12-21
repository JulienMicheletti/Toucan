package toucan.modele.mouvement;

import java.awt.Color;
import java.util.ArrayList;

public class LesMouvements {
	private ArrayList<Mouvement> lesMouvements;
	private int xInit;
	private int yInit;
	private int valInit;
	
	/**
	 * Constructeur de LesMouvements
	 * @param xInit coordonnee en x initial du mouvement
	 * @param yInit coordonnee en y initial du mouvement
	 * @exception AssertionError si xInit >= 0 && yInit >= 0
	 */
	public LesMouvements(int xInit, int yInit, int valeur) {
		assert(xInit >= 0 && yInit >= 0);
		lesMouvements = new ArrayList<Mouvement>();
		this.xInit = xInit;
		this.yInit = yInit;
		this.valInit = valeur;
	}

	/**
	 * Methode d'execution du mouvement a droite
	 * @param d duree du mouvement
	 * @exception AssertionError si d > 0
	 */
	public void droite(int d) {
		assert(d >= 0);
		int i = lesMouvements.size() - 1;
		if (i == -1) {
			ajouterMouvement(new MouvementEst(0, d, xInit, yInit, valInit, Color.BLUE));
		} else {
			Mouvement m = lesMouvements.get(i);
			ajouterMouvement(new MouvementEst(m.getTempsArrivee(), d, m.getXArrivee(), m.getYArrivee(), m.getValeur(), Color.BLUE));
		}
	}
	

	/**
	 * Methode d'execution du mouvement a gauche
	 * @param d duree du mouvement
	 * @exception AssertionError si d > 0
	 */
	public void gauche(int d) {
		assert(d >= 0);
		int i = lesMouvements.size() - 1;
		if (i == -1) {
			ajouterMouvement(new MouvementOuest(0, d, xInit, yInit, valInit, Color.BLUE));
		} else {
			Mouvement m = lesMouvements.get(i);
			ajouterMouvement(new MouvementOuest(m.getTempsArrivee(), d, m.getXArrivee(), m.getYArrivee(), m.getValeur(), Color.BLUE));
		}
	}
	

	/**
	 * Methode d'execution du mouvement monter
	 * @param d duree du mouvement
	 * @exception AssertionError si d > 0
	 */
	public void monter(int d) {
		assert(d >= 0);
		int i = lesMouvements.size() - 1;
		if (i == -1) {
			ajouterMouvement(new MouvementNord(0, d, xInit, yInit, valInit, Color.BLUE));
		} else {
			Mouvement m = lesMouvements.get(i);
			ajouterMouvement(new MouvementNord(m.getTempsArrivee(), d, m.getXArrivee(), m.getYArrivee(), m.getValeur(), Color.BLUE));
		}
	}
	

	/**
	 * Methode d'execution du mouvement descendre
	 * @param d duree du mouvement
	 * @exception AssertionError si d > 0
	 */
	public void descendre(int d) {
		assert(d >= 0);
		int i = lesMouvements.size() - 1;
		if (i == -1) {
			ajouterMouvement(new MouvementSud(0, d, xInit, yInit, valInit, Color.BLUE));
		} else {
			Mouvement m = lesMouvements.get(i);
			ajouterMouvement(new MouvementSud(m.getTempsArrivee(), d, m.getXArrivee(), m.getYArrivee(), m.getValeur(), Color.BLUE));
		}
	}

	/**
	 * Methode d'execution du mouvement stable
	 * @param d duree du mouvement
	 * @exception AssertionError si d > 0
	 */
	public void stable(int d, int valeur, Color couleur) {
		assert(d >= 0);
		int i = lesMouvements.size() - 1;
		if (i == -1) {
			ajouterMouvement(new MouvementStable(0, d, xInit, yInit, valeur, couleur));
		} else {
			Mouvement m = lesMouvements.get(i);
			ajouterMouvement(new MouvementStable(m.getTempsArrivee(), d, m.getXArrivee(), m.getYArrivee(), valeur, couleur));
		}
	}
	

	/**
	 * Methode de calcule de la position du mouvement en X
	 * @param t indice temporel du mouvement
	 * @return posX position en X du mouvement au temps t
	 * @exception AssertionError si t >= 0
	 */
	public int posX(int t) {
		assert(t >= 0);
		int i = 0;
		int posX = 0;
		boolean tempsTrouve = false;
		if (lesMouvements.size() == 0) {
			return xInit;
		}
		while (!tempsTrouve) {
			Mouvement m = lesMouvements.get(i);
			int tempsDepart = m.getTempsDepart();
			int tempsArrivee = m.getTempsArrivee();
			if (t >= tempsDepart && t <= tempsArrivee) {
				tempsTrouve = true;
				posX = m.posX(t);
			} else if (i == lesMouvements.size() - 1) {
				tempsTrouve = true;
				posX = m.getXArrivee();
			}
			++i;
		}
		return posX;
	}
	
	/**
	 * Methode de calcule de la position du mouvement en Y
	 * @param t indice temporel du mouvement
	 * @return posX position en Y du mouvement au temps t
	 * @exception AssertionError si t >= 0
	 */
	public int posY(int t) {
		assert(t >= 0);
		int i = 0;
		int posY = 0;
		boolean tempsTrouve = false;
		if (lesMouvements.size() == 0) {
			return yInit;
		}
		while (!tempsTrouve) {
			Mouvement m = lesMouvements.get(i);
			int tempsDepart = m.getTempsDepart();
			int tempsArrivee = m.getTempsArrivee();
			if (t >= tempsDepart && t <= tempsArrivee) {
				tempsTrouve = true;
				posY = m.posY(t);
			} else if (i == lesMouvements.size() - 1) {
				tempsTrouve = true;
				posY = m.getYArrivee();
			}
			++i;
		}
		return posY;
	}
	
	/**
	 * Methode qui retourne la couleur actuelle d'une case
	 * @param t temps ou la couleur est demande
	 * @return col couleur de la case
	 */
	public Color getColor(int t) {
			assert(t >= 0);
			int i = 0;
			Color col = Color.BLUE;
			boolean tempsTrouve = false;
			while (!tempsTrouve && i < lesMouvements.size()) {
				Mouvement m = lesMouvements.get(i);
				int tempsDepart = m.getTempsDepart();
				int tempsArrivee = m.getTempsArrivee();
				if (t >= tempsDepart && t <= tempsArrivee) {
					tempsTrouve = true;
					col = m.getCouleur();
				}
				++i;
			}
			return col;
	}
	
	/**
	 * Methode qui retourne la valeur actuelle d'une case
	 * @param t temps ou la valeur est demande
	 * @return val valeur de la case
	 */
	public int getValeur(int t) {
		assert(t >= 0);
		int i = 0;
		int val = valInit;
		boolean tempsTrouve = false;
		if (lesMouvements.size() == 0) {
			return valInit;
		}
		while (!tempsTrouve && i < lesMouvements.size()) {
			Mouvement m = lesMouvements.get(i);
			int tempsDepart = m.getTempsDepart();
			int tempsArrivee = m.getTempsArrivee();
			if (t >= tempsDepart && t <= tempsArrivee) {
				tempsTrouve = true;
				val = m.getValeur();
			}
			++i;
		}
		return val;
	}
	
	/**
	 * Methode d'ajout d'un mouvement
	 * @param m mouvement a ajouter
	 * @exception AssertionError m != null
	 */
	private void ajouterMouvement(Mouvement m) {
		assert(m != null);
		lesMouvements.add(m);
	}

	@Override
	public String toString() {
		return lesMouvements.toString();
	}
}
