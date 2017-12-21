package toucan.arbre;

public class AffectCaseCase extends Instruction{
    /**
     * Constructeur AffectCaseCase
     * @param indice0 indice de la premiere case
     * @param indice1 indice de la seconde case
     */
    public AffectCaseCase(String indice0, String indice1) {
       super(indice0, indice1);
    }

    @Override
    public String getCodeDecore() {
        return "affectationCaseCase.executer(lesCases, "+indice0+", "+indice1
                +");\ntab["+indice0+"] = tab["+indice1+"];\n";
    }
}
