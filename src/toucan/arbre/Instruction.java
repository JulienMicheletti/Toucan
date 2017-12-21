package toucan.arbre;

public abstract class Instruction extends ArbreAbstrait {
    protected String indice0;
    protected String indice1;

    /**
     * Constructeur Instruction
     * @param indices indices des cases a deplacer
     */
    public Instruction(String... indices) {
        if (indices.length != 0) {
            indice0 = indices[0];
            if (indices.length == 2) {
                indice1 = indices[1];
            }
        }
    }
}
