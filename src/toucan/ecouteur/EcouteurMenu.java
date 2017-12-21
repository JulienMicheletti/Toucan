package toucan.ecouteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import toucan.modele.Modele;


public class EcouteurMenu implements ActionListener{
	private Observable o;

	/**
	 * Constructeur de la class EcouteurMenuu
	 */
	public EcouteurMenu(Observable o){
		this.o = o;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Modele m = (Modele)o;
		if (m.getPause() == true) {
			m.setPause();
		}
		if (m.getButtonPause() == false){
			m.setButtonPause();
		}
		m.setSelect(e.getActionCommand());
		m.create(true);
	}
}