package toucan.modele.animations.comparaisons;

import java.awt.Color;

import toucan.modele.cases.LesCases;

public class ComparaisonCaseVar extends Comparaison{

	@Override
	public void executer(LesCases cases, int... indices) {
		assert(cases != null);
		assert(indices != null);
		int i0 = indices[0];
		int t = cases.getMaxTemps();
		int var = cases.getNbCases() - 1;
		int tempsMouv = 50;

		//Si la valeur de la case est superieur a celle de la variable
		if (cases.getValeur(i0, t) > cases.getValeur(var, t)) {
			//On colorie en rouge les deux cases
			cases.stable(i0, 50, cases.getValeur(i0, t), Color.RED);
			cases.stable(var, 50, cases.getValeur(var, t), Color.RED);
		} else {
			//On colorie en vert les deux cases
			cases.stable(i0, 50, cases.getValeur(i0, t), Color.GREEN);
			cases.stable(var, 50, cases.getValeur(var, t), Color.GREEN);
		}
		for (int i = 0; i < cases.getNbCases() - 1; i++) {
			if (i != i0) {
				cases.stable(i, tempsMouv, cases.getValeur(i, t), Color.BLUE);
			}
		}
	}
}
