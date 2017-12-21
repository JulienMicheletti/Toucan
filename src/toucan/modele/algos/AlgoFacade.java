package toucan.modele.algos;

import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;

import toucan.analyse.AnalyseurLexical;
import toucan.analyse.AnalyseurSyntaxique;
import toucan.arbre.AffectCaseCase;
import toucan.arbre.ArbreAbstrait;
import toucan.arbre.BlocDInstructions;
import toucan.modele.Modele;
import toucan.modele.cases.LesCases;
import toucan.outils.KitJava;

public class AlgoFacade extends Algo {
	private Modele m;
	private KitJava kit;

	/**
	 * Constructeur AlgoFacade
	 * @param lesCases Tableau de cases a trier
	 * @param m Modele
	 * @param tab tableau des entiers a trier
	 */
	public AlgoFacade(LesCases lesCases, Modele m, int[] tab) {
		super(lesCases, tab);
		this.m = m;
		kit = KitJava.getInstance();
	}

	@Override
	public void trier() throws Exception {
		AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new StringReader(m.getCode())));
		ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;
		kit.construireClasse(arbre.getCodeDecore());
		kit.compiler();
		m.putText(true);
		kit.executer(lesCases, tab);
	}
}
