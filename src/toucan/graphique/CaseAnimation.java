package toucan.graphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import toucan.modele.cases.Case;

/**
 * 24 juin 2014 - maj 29 ao√ªt 2017
 *
 * @author brigitte wrobel-dautcourt
 */

public class CaseAnimation extends BufferedImage {
    protected Graphics2D carre ;
    protected int cote = 50 ;
    protected Color couleur ;
    protected int positionX ;
    protected int positionY ;
    protected String valeur ;
    private Case c;
    
    /**
	 * Constructeur de la class CaseAnimation
	 * @param c case ‡ ajouter dans l'animation
	 * @param coul couleur ‡ appliquer sur la case
	 */
    public CaseAnimation(Case c, Color coul) {
        super(150, 150, BufferedImage.TYPE_INT_ARGB);
        this.c = c;
    	couleur = coul;
    	carre = createGraphics();
    	valeur = c.getValeur()+"";
    	positionX = c.posX(0);
    	positionY = c.posY(0);
        dessinerCase() ;
    }
    
    /**
     * Dessin de l'element graphique (l'element graphique est redessine, car sa couleur et son contenu peuvent 
     * changer au cours de l'animation)
     */
    private void dessinerCase() {
        carre.setPaint(Color.white) ;
        carre.fillRect(0, 0, cote, cote) ;
        carre.setColor(couleur) ;
        carre.drawRect(0, 0, cote, cote);
        // dessin de la chaine au centre de la case
        carre.setFont(new Font("Arial", Font.BOLD, 16));
        FontMetrics fm = carre.getFontMetrics();
        int xC = (cote - fm.stringWidth(valeur)) / 2;
        int yC = (fm.getAscent() + (cote - (fm.getAscent() + fm.getDescent())) / 2);
        carre.drawString(valeur, xC, yC);
    } 

    /**
     * Dessin de l'element graphique et positionnement dans la fenetre graphique
     * @param g fenetre graphique dans laquelle on dessine
     */
    public void dessiner(Graphics g, int t) {   
        dessinerCase() ;
        positionX = c.posX(t) ;
        positionY = c.posY(t) ;
        valeur = c.getValeur(t)+"";
        couleur = c.getColor(t);
        g.drawImage(this, positionX, positionY, null);
    } 

}
