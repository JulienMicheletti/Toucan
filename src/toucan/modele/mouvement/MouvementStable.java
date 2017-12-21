package toucan.modele.mouvement;

import java.awt.Color;

public class MouvementStable extends Mouvement {
	/**
	 * Constructeur de MouvementStable
	 * @param temps 
	 * @param duree du mouvement
	 * @param xDepart coordonnee en x au depart du mouvement
	 * @param yDepart coordonnee en y au depart du mouvement
	 */
	public MouvementStable(int temps, int duree, int xDepart, int yDepart, int valeur, Color couleur) {
		super(temps, duree, xDepart, yDepart, valeur, couleur);
		xArrivee = xDepart;
		yArrivee = yDepart;
		this.valeur = valeur;
		this.couleur = couleur;
	}
	@Override
	public int posX(int t) {
		return xDepart;
	}

	@Override
	public int posY(int t) {
		return yDepart;
	}
}
