package toucan.arbre;

public class Code extends Instruction {
    private String code;

    /**
     * Constructeur Code
     * @param code code rentre par l utilisateur
     */
    public Code(String code) {
        this.code = code;
    }

    @Override
    public String getCodeDecore() {
        return code+";\n";
    }
}
