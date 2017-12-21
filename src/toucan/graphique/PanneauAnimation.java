package toucan.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import toucan.modele.Modele;

/**
 * 24 juin 2014 - maj 29 août 2017
 *
 * @author brigitte wrobel-dautcourt
 */

@SuppressWarnings("serial")
public class PanneauAnimation extends JPanel {
    protected LesCasesAnimation lesCasesAnimation ;
    private Observable o;
    protected int temps ;

    /**
     * Constructeur PanneauAnimation
     * @param o
     */
    public PanneauAnimation(Observable o) {
        super() ;
        this.setPreferredSize(new Dimension(800, 600)) ;
        this.o = o;
        lesCasesAnimation = new LesCasesAnimation(o) ;
        temps = 0 ;
        repaint() ;
    }
    
    @Override
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int w = getWidth() ;
        int h = getHeight() ;
        GradientPaint gp = new GradientPaint(-w, -h, Color.LIGHT_GRAY, w, h, Color.BLACK);
        g2.setPaint(gp);
        
        
        g2.fillRect(0, 0, w, h);
        lesCasesAnimation.dessiner(g, temps) ;
        temps ++ ;
        Modele m = (Modele)o;
        
        try {
            Thread.sleep(m.getVitesse()) ;
        } catch (InterruptedException ex) {
             Logger.getLogger(PanneauAnimation.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (m.getMaxTemps() == 0) {
            lesCasesAnimation = new LesCasesAnimation(o);
            temps = 0;
        }
        if (temps <= m.getMaxTemps() && m.getPause() != true) {
        	repaint();
        }
	}
}
