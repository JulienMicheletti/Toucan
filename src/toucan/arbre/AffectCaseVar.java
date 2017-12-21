package toucan.arbre;

public class AffectCaseVar extends Instruction{
    private String var;
    /**
     * Constructeur AffectCaseVar
     * @param indice0 indice de la premiere case
     * @param var indice de la variable
     */
    public AffectCaseVar(String indice0, String var) {
        super(indice0);
        this.var = var;
    }

    @Override
    public String getCodeDecore() {
        return "affectationCaseVar.executer(lesCases, "+indice0+");\n"+ var + " = tab["+indice0+"];\n";
    }
}
