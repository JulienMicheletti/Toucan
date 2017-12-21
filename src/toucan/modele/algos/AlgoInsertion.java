package toucan.modele.algos;

import toucan.modele.animations.affectations.Affectation;
import toucan.modele.animations.affectations.AffectationCaseCase;
import toucan.modele.animations.affectations.AffectationCaseVar;
import toucan.modele.animations.affectations.AffectationVarCase;
import toucan.modele.animations.comparaisons.Comparaison;
import toucan.modele.animations.comparaisons.ComparaisonCaseVar;
import toucan.modele.animations.comparaisons.ComparaisonFin;
import toucan.modele.cases.LesCases;

public class AlgoInsertion extends Algo {
	
	/**
	 * Constructeur de la class AlgoInsertion
	 * @param lesCases Tableau de cases a trier
	 * @param tab tableau des entiers a trier
	 */
	public AlgoInsertion(LesCases lesCases, int[] tab) {
		super(lesCases, tab);
	}
	
	@Override
	public void trier() {
		int n = lesCases.getNbCases() - 1;
		int x;
		int j;

		for (int i = 1; i < n; i++) {
			affectationCaseVar.executer(lesCases, i);
			x = tab[i];
			j = i;
			if (j > 0) {
				comparaisonCaseVar.executer(lesCases, j-1);
			}
			while (j > 0 && tab[j-1] > x) {
				affectationCaseCase.executer(lesCases,  j, j-1);
				tab[j] = tab[j-1];
				j = j - 1;
				if (j > 0) {
					comparaisonCaseVar.executer(lesCases, j-1);
				}
				affectationVarCase.executer(lesCases, j);
				tab[j] = x;
			}
			setChanged();
			notifyObservers(this);
		}
		comparaisonFin.executer(lesCases, 0);
	}
}