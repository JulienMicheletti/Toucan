package toucan.modele.animations.comparaisons;

import java.awt.Color;

import toucan.modele.cases.LesCases;

public class ComparaisonCaseCase extends Comparaison{

	@Override
	public void executer(LesCases cases, int... indices) {
		assert(cases != null);
		assert(indices != null);
		int i0 = indices[0];
		int i1 = indices[1];
		int t = cases.getMaxTemps();
		int tempsMouv = 50;

		//Si la valeur de la premiere case est superieur a la deuxieme
		if (cases.getValeur(i0, t) > cases.getValeur(i1, t)) {
			//On colorie en rouge ces deux cases
			cases.stable(i0, 50, cases.getValeur(i0, t), Color.RED);
			cases.stable(i1, 50, cases.getValeur(i1, t), Color.RED);
		} else {
			//On colorie en vert ces deux cases
			cases.stable(i0, 50, cases.getValeur(i0, t), Color.GREEN);
			cases.stable(i1, 50, cases.getValeur(i1, t), Color.GREEN);
		}
		for (int i = 0; i < cases.getNbCases(); i++) {
			if (i != i0 && i!= i1) {
				cases.stable(i, tempsMouv, cases.getValeur(i, t), Color.BLUE);
			}
		}
	}
}

