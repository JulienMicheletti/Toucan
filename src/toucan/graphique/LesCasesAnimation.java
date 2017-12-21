package toucan.graphique;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import java.awt.Color;

import toucan.modele.Modele;
import toucan.modele.cases.Case;

/**
 * 22 juin 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class LesCasesAnimation {
    protected ArrayList<CaseAnimation> lesCases ;
    
    public LesCasesAnimation(Observable o) {
    	int nbCases = ((Modele)o).getNbCases();
        lesCases = new ArrayList<>(nbCases);
        Iterator<Case> i = ((Modele)o).iterator();
        while (i.hasNext()) {
        	lesCases.add(new CaseAnimation(i.next(), Color.BLUE));
        }
    }
    
    public void dessiner(Graphics g, int t) {
        for (CaseAnimation ca : lesCases) {
            ca.dessiner(g, t) ;
        }
    }

}
