package toucan.vues;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import toucan.ecouteur.EcouteurMenu;
import toucan.modele.Modele;
import toucan.modele.algos.Algo;

public class VueSelection extends JPanel implements Observer{
	private JButton[] algo;
	private Modele m;

	/**
	 * Constructeur de la class VueSelection
	 */
	public VueSelection(Observable o){
		JMenu menu;
		JMenuItem quitter;
		JMenuBar bar;
		JToolBar toolBar;

		m = (Modele)o;
		toolBar = new JToolBar("Toucan");
		menu = new JMenu("Toucan");
		quitter = new JMenuItem("Quitter");
		bar = new JMenuBar();
		algo = new JButton[4];
		algo[0] = new JButton("Tri a bulle");
		algo[1] = new JButton("Tri par insertion");
		algo[2] = new JButton("Tri par selection");
		algo[3] = new JButton("Tri personnel");
		toolBar.add(algo[0]);
		toolBar.add(algo[1]);
		toolBar.add(algo[2]);
		toolBar.add(algo[3]);
		algo[0].addActionListener(new EcouteurMenu(o));
		algo[0].setActionCommand("bulle");
		algo[1].addActionListener(new EcouteurMenu(o));
		algo[1].setActionCommand("insertion");
		algo[2].addActionListener(new EcouteurMenu(o));
		algo[2].setActionCommand("selection");
		algo[3].addActionListener(new EcouteurMenu(o));
		algo[3].setActionCommand("algo personnel");
		bar.add(menu);
		menu.add(quitter);
		quitter.addActionListener(e -> System.exit(0));
		setLayout(new BorderLayout());
		this.add(bar, BorderLayout.NORTH);
		this.add(toolBar);
		this.setBackground(Color.gray);   
		o.addObserver(this);
		update(o, null);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (arg != null) {
			((Algo)arg).addObserver(this);
		}
		String select = m.getSelect();
		for (JButton b : algo) {
			if (b.getActionCommand().equals(select)) {
				b.setBackground(Color.BLACK);
			} else {
				b.setBackground(Color.LIGHT_GRAY);
			}
			b.setForeground(Color.WHITE);
		}
		if (m.getStop() == false){
			algo[0].setEnabled(false);
			algo[1].setEnabled(false);
			algo[2].setEnabled(false);
			algo[3].setEnabled(false);
		} else if (m.getStop() == true){
			algo[0].setEnabled(true);
			algo[1].setEnabled(true);
			algo[2].setEnabled(true);
			algo[3].setEnabled(true);
		}
	}
}
