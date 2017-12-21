package toucan.modele.animations.affectations;

import java.awt.Color;

import toucan.modele.cases.LesCases;

public class AffectationCaseCase extends Affectation {

	@Override
	public void executer(LesCases cases, int... indices) {
		assert(cases != null);
		assert(indices != null);
		int i0 = indices[0];
		int i1 = indices[1];
		int milieu;
		int var = cases.getNbCases() - 1;
		int tempsMouv;
		int t = cases.getMaxTemps();

		//Descente des deux cases concernees
		cases.descendre(i0, 100);
		cases.descendre(i1, 100);
		//Si la position de la premiere est > a la deuxieme
		if (cases.posX(i0, 0) > cases.posX(i1, 0)) {
			//On calcule l'endroit ou elles se rejoignent
			milieu = cases.posX(i0,  0) - cases.posX(i1, 0);
			tempsMouv =  milieu * 2 + 15 + 200;
			//La premiere case se deplace a gauche car elle est a droite de la deuxieme
			cases.gauche(i0, milieu);
			cases.stable(i1, tempsMouv - 200, cases.getValeur(i1, t), Color.BLUE);
			cases.stable(i0, 15, cases.getValeur(i1, t), Color.GREEN);
			cases.stable(var, tempsMouv, cases.getValeur(var, t), Color.BLUE);
			cases.droite(i0, milieu);
		} else {
			milieu = cases.posX(i1, 0) - cases.posX(i0, 0);
			tempsMouv =  milieu *2 + 15 + 200;
			cases.droite(i0, milieu);
			cases.stable(i1, tempsMouv - 200, cases.getValeur(i1, t), Color.BLUE);
			cases.stable(i0, 15, cases.getValeur(i1, t), Color.BLUE);
			cases.stable(var, tempsMouv, cases.getValeur(var, t), Color.BLUE);
			cases.gauche(i0, milieu);
		}
		//Les deux cases remontent a la fin de l'animation
		cases.monter(i0, 100);
		cases.monter(i1, 100);
		//Les cases non utilisees restent stable
		for (int i = 0; i < cases.getNbCases() - 1; i++) {
			if (i != i0 && i!= i1) {
				cases.stable(i, tempsMouv, cases.getValeur(i, t), Color.BLUE);
			}
		}
	}

}
