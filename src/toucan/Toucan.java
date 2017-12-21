package toucan;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import toucan.modele.Modele;
import toucan.vues.VueGestion;
import toucan.vues.VueGraphique;
import toucan.vues.VueSaisie;
import toucan.vues.VueSelection;

/**
 * @author brigitte wrobel-dautcourt
 */

public class Toucan extends JFrame {
	
	/**
	 * Constructeur de la class graphique principale Toucan
	 */
    public Toucan() {
        super("Projet Toucan - animation des algorithmes de tris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Modele t = new Modele(new int[]{2, 1, 5, 4, 7, 8, 3, 0});
        // instanciation de VueGraphique
        VueGraphique vg = new VueGraphique(t);
        add(vg);
        //instanciation de VueSelection
        VueSelection vm = new VueSelection(t);
        add(vm, BorderLayout.NORTH);
        //instanciation de VueGestion
        VueGestion vb = new VueGestion(t);
        add(vb, BorderLayout.SOUTH);
        VueSaisie vs = new VueSaisie(t);
        add(vs, BorderLayout.WEST);
     
        pack();
        setVisible(true);
        
    }

    /**
	 * Fonction main du projet
	 */
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new Toucan());
    }
}