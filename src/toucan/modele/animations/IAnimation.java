package toucan.modele.animations;

import toucan.modele.cases.LesCases;

public interface IAnimation {

	/**
	 * Fonction d'execution des animations graphique des cases
	 */
	public void executer(LesCases cases, int... indices);
}
