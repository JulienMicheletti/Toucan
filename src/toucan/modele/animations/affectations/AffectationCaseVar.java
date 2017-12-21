package toucan.modele.animations.affectations;

import java.awt.Color;

import toucan.modele.cases.LesCases;

public class AffectationCaseVar extends Affectation{

	@Override
	public void executer(LesCases cases, int... indices) {
		assert(cases != null);
		assert(indices != null);
		int i0 = indices[0];
		int var = cases.getNbCases() - 1;
		int tempsMouv;
		int t = cases.getMaxTemps();
		//On definit la position de la case a affecter par rapport a la variable
		int cibleX = cases.posX(i0, t) - cases.posX(var, t);
		int cibleY = cases.posY(var, t) - cases.posY(i0, t);

		//Si la variable est a droite de la case
		if (cibleX < 0) {
			//On redefinit la position de la case a affecter
			int cibleX2 = cases.posX(var, t) - cases.posX(i0, t);
			tempsMouv = cibleX2 + 15 + cibleY * 2;
			//La case var se deplace a gauche vers la case a affecter
			cases.gauche(var, cibleX2);
		} else {
			tempsMouv = cibleX + 15 + cibleY * 2;
			cases.droite(var, cibleX);
		}
		//La case var monte pour affecter la case demandee
		cases.monter(var, cibleY);
		cases.stable(var, 15, cases.getValeur(i0, t), Color.GREEN);
		cases.descendre(var, cibleY);
		//Pendant tout le trajet de var, la case est immobilisee
		cases.stable(i0, tempsMouv, cases.getValeur(i0, t), Color.BLUE);
		for (int i = 0; i < cases.getNbCases() - 1; i++) {
			if (i != i0) {
				cases.stable(i, tempsMouv, cases.getValeur(i, t), Color.BLUE);
			}
		}
	}
}
