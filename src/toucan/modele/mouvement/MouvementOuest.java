package toucan.modele.mouvement;

import java.awt.Color;

public class MouvementOuest extends Mouvement {
	/**
	 * Constructeur de MouvementOuest
	 * @param temps 
	 * @param duree du mouvement
	 * @param xDepart coordonnee en x au depart du mouvement
	 * @param yDepart coordonnee en y au depart du mouvement
	 */
	public MouvementOuest(int temps, int duree, int xDepart, int yDepart, int valeur, Color couleur) {
		super(temps, duree, xDepart, yDepart, valeur, couleur);
		xArrivee = xDepart - duree;
		yArrivee = yDepart;
	}
	
	@Override
	public int posX(int t) {
		return xDepart - (t - tempsDepart);
	}

	@Override
	public int posY(int t) {
		return yDepart;
	}

}
