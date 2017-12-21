package toucan.modele.mouvement;

import java.awt.Color;

public class MouvementSud extends Mouvement {
	/**
	 * Constructeur de MouvementSud
	 * @param temps 
	 * @param duree du mouvement
	 * @param xDepart coordonnee en x au depart du mouvement
	 * @param yDepart coordonnee en y au depart du mouvement
	 */
	public MouvementSud(int temps, int duree, int xDepart, int yDepart, int valeur, Color couleur) {
		super(temps, duree, xDepart, yDepart, valeur, couleur);
		xArrivee = xDepart;
		yArrivee = yDepart + duree;
	}
	
	@Override
	public int posX(int t) {
		return xDepart;
	}

	@Override
	public int posY(int t) {
		return yDepart + (t - tempsDepart);
	}

}
