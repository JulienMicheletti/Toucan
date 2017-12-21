package toucan.modele.animations.comparaisons;

import java.awt.Color;

import toucan.modele.cases.LesCases;

public class ComparaisonFin extends Comparaison{
	
	@Override
	public void executer(LesCases cases, int... indices) {
	assert(cases != null);
	assert(indices != null);
	int t = cases.getMaxTemps();
	int var = cases.getNbCases() - 1;
	
	//La case var est stabilisee
	cases.stable(var, 500, cases.getValeur(var,  t), Color.BLUE);
	//On fait clignoter les cases en jaune pour montrer la fin de l'animation
	for (int i = 0; i < cases.getNbCases() - 1; i++) {
			cases.stable(i, 500, cases.getValeur(i, t), Color.MAGENTA);
		}
	}			
}

