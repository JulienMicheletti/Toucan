package toucan.vues;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import toucan.modele.Modele;


public class VueSaisie extends JPanel implements Observer{
	static private JEditorPane edit;
	private Modele m;
	
	/**
	 * Constructeur de la class VueSaisie
	 */
	public VueSaisie(Observable o) {
		m = (Modele)o;
		edit = new JEditorPane();

		setLayout(new BorderLayout());
		add(edit);
		edit.setSize(300, 1000);
		o.addObserver(this);
	}

	/**
	 * Fonction qui retourne le texte entre par l utilisateur
	 */
	public static String RetourneTexte(){
		return edit.getText();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (!m.getErreur().isEmpty()) {
			JOptionPane.showMessageDialog(this, m.getErreur(), "Exception", JOptionPane.ERROR_MESSAGE);
			m.setButtonPause();
			m.setStop();
		}
	}
}