package toucan.arbre;

public class CompCaseCase extends Instruction{
    /**
     * Constructeur CompCaseCase
     * @param indice0 indice de la premiere case
     * @param indice1 indice de la seconde case
     */
    public CompCaseCase(String indice0, String indice1) {
        super(indice0, indice1);
    }

    @Override
    public String getCodeDecore() {
        return "comparaisonCaseCase.executer(lesCases, "+indice0+", "+indice1
                +");\nif (tab["+indice0+"] = tab["+indice1+"]){}\n";
    }
}
