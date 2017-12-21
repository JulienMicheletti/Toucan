package toucan.test;

import toucan.arbre.*;

public class TestGetCodeDecore {
    public static void main(String args[]) {
        AffectCaseCase caseCase = new AffectCaseCase("1", "2");
        AffectCaseVar caseVar = new AffectCaseVar("1", "");
        AffectVarCase varCase = new AffectVarCase("1", "");
        CompCaseCase compCase = new CompCaseCase("1", "2");
        CompCaseVar compVar = new CompCaseVar("2");

        BlocDInstructions b = new BlocDInstructions();
        b.ajouter(caseCase);
        b.ajouter(caseVar);
        b.ajouter(varCase);
        b.ajouter(compCase);
        b.ajouter(compVar);
        System.out.println(b.getCodeDecore());
        //assert(b.getCodeDecore().equals("affectationCaseCase.executer(lesCases, 1, 2);\ntab[1] = tab[2];"));
    }
}
