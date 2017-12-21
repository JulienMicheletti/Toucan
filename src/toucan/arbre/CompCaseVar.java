package toucan.arbre;

public class CompCaseVar extends Instruction{
    /**
     * Constructeur CompCaseVar
     * @param indice0 indice de la premiere case
     */
    public CompCaseVar(String indice0) {
        super(indice0);
    }

    @Override
    public String getCodeDecore() {
        return "comparaisonCaseVar.executer(lesCases, "+indice0+");\nif (tab["+indice0+"] == "+indice1+"){}\n";
    }
}
