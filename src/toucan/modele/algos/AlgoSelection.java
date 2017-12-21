package toucan.modele.algos;

import toucan.modele.animations.affectations.Affectation;
import toucan.modele.animations.affectations.AffectationCaseCase;
import toucan.modele.animations.affectations.AffectationCaseVar;
import toucan.modele.animations.affectations.AffectationVarCase;
import toucan.modele.animations.comparaisons.Comparaison;
import toucan.modele.animations.comparaisons.ComparaisonCaseCase;
import toucan.modele.animations.comparaisons.ComparaisonCaseVar;
import toucan.modele.animations.comparaisons.ComparaisonFin;
import toucan.modele.cases.LesCases;

public class AlgoSelection extends Algo{
	
	/**
	 * Constructeur de la class AlgoSelection
	 * @param lesCases Tableau de cases a trier
     * @param tab tableau des entiers a trier
	 */
    public AlgoSelection(LesCases lesCases, int[] tab) {
        super(lesCases, tab);
    }

    @Override
    public void trier() {
       int n = lesCases.getNbCases() - 1;
       int min;

         for(int i = 0; i < n - 1; i++) {
            min = i;
            for (int j = i + 1; j < n; j++) {
                if (tab[j] < tab[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int var = tab[i];
                affectationCaseVar.executer(lesCases, i);
                tab[i] = tab[min];
                affectationCaseCase.executer(lesCases, i, min);
                tab[min] = var;
                affectationVarCase.executer(lesCases, min);
            }
             setChanged();
             notifyObservers(this);
        }
        comparaisonFin.executer(lesCases, 0);
    }
    
}
