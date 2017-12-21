package toucan.arbre;

public class AffectVarCase extends Instruction {
    private String var;
    /**
     * Constructeur AffectCaseCase
     * @param indice0 indice de la premiere case
     * @param var indice de la variable
     */
    public AffectVarCase(String indice0, String var) {
        super(indice0);
        this.var = var;
    }

    @Override
    public String getCodeDecore() {
        return "affectationVarCase.executer(lesCases, "+indice0+");\ntab["+indice0+"] = "+ var + ";\n";
    }
}
