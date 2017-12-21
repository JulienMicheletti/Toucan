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

import java.util.Observable;

public abstract class Algo extends Observable{
	protected LesCases lesCases;
	protected int[] tab;
	protected Affectation affectationCaseCase = new AffectationCaseCase();
    protected Comparaison comparaisonCaseCase = new ComparaisonCaseCase();
    protected Affectation affectationCaseVar = new AffectationCaseVar();
    protected Affectation affectationVarCase = new AffectationVarCase();
    protected Comparaison comparaisonFin = new ComparaisonFin();
    protected Comparaison comparaisonCaseVar = new ComparaisonCaseVar();
    

	/**
	 * Constructeur de la class abstraite Algo
	 * @param lesCases Tableau contenant les cases a trier
	 */
	public Algo(LesCases lesCases, int[] tab) {
		assert(lesCases != null);
		this.lesCases = lesCases;
		this.tab = tab;
	}
	
	/**
	 * Fonction de tri des algorithmes
	 */
	public abstract void trier() throws Exception;
}
