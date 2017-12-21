package toucan.vues;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import toucan.graphique.PanneauAnimation;
import toucan.modele.Modele;
import toucan.modele.algos.Algo;

import static java.lang.Thread.yield;

@SuppressWarnings("serial")
public class VueGraphique extends JPanel implements Observer{
	private PanneauAnimation panneauAnimation;
	
	/**
	 * Constructeur de la class VueGraphique
	 */
	public VueGraphique(Observable o) {
		 panneauAnimation = new PanneauAnimation(o) ;
		 setLayout(new BorderLayout());
		 this.setBackground(Color.DARK_GRAY);
	     this.add(panneauAnimation, BorderLayout.CENTER) ;
	     o.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg != null) {
			((Algo)arg).addObserver(this);
		}
		Runnable code = () -> panneauAnimation.repaint();
		if (SwingUtilities.isEventDispatchThread()) {
			code.run();
		} else {
			try {
				SwingUtilities.invokeAndWait(code);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}
