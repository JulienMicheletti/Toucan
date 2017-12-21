package toucan.modele.animations.affectations;

import java.awt.Color;

import toucan.modele.cases.LesCases;

public class AffectationVarCase extends Affectation{

	@Override
	public void executer(LesCases cases, int... indices) {
		assert(cases != null);
		assert(indices != null);
		int i0 = indices[0];
		int var = cases.getNbCases() - 1;
		int t = cases.getMaxTemps();
		//On definit la position de la case a affecter par rapport a la variable
		int cibleX = cases.posX(i0, t) - cases.posX(var, t);
		int cibleY = cases.posY(var, t) - cases.posY(i0, t);
		int tempsMouv;
		
		//Si la variable est a droite de la case
		if (cibleX < 0) {
			//On redefinit la position de la case a affecter
			int cibleX2 = cases.posX(var, t) - cases.posX(i0, t);
			tempsMouv = cibleX2 + cibleY * 2 + 115;
			//La case var se deplace a gauche vers la case a affecter
			cases.gauche(var, cibleX2);
			cases.stable(i0, cibleX2, cases.getValeur(i0, t), Color.BLUE);
		} else {
			tempsMouv = cibleX + 2 * cibleY  + 115;
			cases.droite(var, cibleX);
			cases.stable(i0, cibleX, cases.getValeur(i0, t), Color.BLUE);
		}
		//La case qui affecte se deplace vers la variable
		cases.descendre(i0, cibleY);
		//On immobilise la case var le temps de l'affectation
		cases.stable(var, cibleY * 2 + 15, cases.getValeur(var, t), Color.BLUE);
		cases.stable(i0, 15, cases.getValeur(var, t), Color.BLUE);
		//La case qui affecte remonte a la fin de l'animation
		cases.monter(i0, cibleY);
		for (int i = 0; i < cases.getNbCases() - 1; i++) {
			if (i != i0) {
				cases.stable(i, tempsMouv - 100, cases.getValeur(i, t), Color.BLUE);
			}
		}
	}
}



