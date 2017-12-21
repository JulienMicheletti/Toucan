package toucan.modele.algos;

import toucan.modele.animations.affectations.Affectation;
import toucan.modele.animations.affectations.AffectationCaseCase;
import toucan.modele.animations.affectations.AffectationCaseVar;
import toucan.modele.animations.affectations.AffectationVarCase;
import toucan.modele.animations.comparaisons.Comparaison;
import toucan.modele.animations.comparaisons.ComparaisonCaseCase;
import toucan.modele.animations.comparaisons.ComparaisonFin;
import toucan.modele.cases.LesCases;

public class AlgoBulle extends Algo {
	
	/**
	 * Constructeur de la class AlgoBulle 
	 * @param lesCases Tableau de cases a trier
	 * @param tab tableau des entiers a trier
	 */
	public AlgoBulle(LesCases lesCases, int[] tab) {
		super(lesCases, tab);
	}
	
	@Override
	public void trier() {
		boolean encore = true;
		int n = lesCases.getNbCases() - 1;
	
		while (encore) {
			encore = false ;
			for (int j = 0 ; j < n - 1; j++) {
				comparaisonCaseCase.executer(lesCases, j, j+1) ;
				if (tab[j] > tab[j+1]) {
					affectationCaseVar.executer(lesCases, j) ;
					int var = tab[j] ;
					affectationCaseCase.executer(lesCases, j, j+1);
					tab[j] = tab[j+1] ;
					affectationVarCase.executer(lesCases, j+1) ;
					tab[j+1] = var ;
					encore = true ;
				}
				setChanged();
				notifyObservers(this);
			}
		}
		comparaisonFin.executer(lesCases, 0);
	}
}
