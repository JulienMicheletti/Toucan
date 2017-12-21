package toucan.arbre;

import java.util.ArrayList;

public class BlocDInstructions extends ArbreAbstrait{
    private ArrayList<Instruction> instructions;
    /**
     * Constructeur BlocDInstruction
     */
    public BlocDInstructions () {
        instructions = new ArrayList<Instruction>();
    }

    /**
     * Methode d ajout dans la liste d instruction
     */
    public void ajouter(Instruction i) {
        instructions.add(i);
    }

    @Override
    public String getCodeDecore() {
        StringBuilder strb = new StringBuilder();
        for (Instruction i : instructions) {
            strb.append(i.getCodeDecore());
        }
        return strb.toString();
    }
}
