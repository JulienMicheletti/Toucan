package toucan.vues;

import java.awt.*;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import toucan.modele.Modele;
import toucan.modele.algos.Algo;
import toucan.modele.exceptions.LongueurChaineException;

public class VueGestion extends JToolBar implements Observer{
	private Modele m;
	private JTextField jtf;
	private JButton start;
	private JButton create;
	private JButton random;

	/**
	 * Constructeur de la class VueGestion
	 */
	public VueGestion(Observable o) {
		start = new JButton(new ImageIcon(getClass().getResource("play logo.png")));
		JButton stop = new JButton(new ImageIcon(getClass().getResource("stop logo.png")));
		create = new JButton("Creer");
	 	random = new JButton("Random");
		JSlider vitesse = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
		Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
		JPanel left = new JPanel();
		JPanel center = new JPanel();
		JPanel center2 = new JPanel();

		o.addObserver(this);
		m = (Modele)o;
		jtf = new JTextField("2, 1, 5, 4, 7, 8, 3, 0");
		table.put(0, new JLabel("Rapide"));
		table.put(10, new JLabel("Lent"));
		vitesse.setLabelTable(table);
		vitesse.setPaintLabels(true);
		//Layouts
		setLayout(new BorderLayout());
		center.setLayout(new BorderLayout());
		center2.setLayout(new GridLayout(1, 2));
		//Add
		left.add(start);
		left.add(stop);
		center.add(jtf, BorderLayout.NORTH);
		center.add(center2);
		center2.add(create);
		center2.add(random);
		add(left, BorderLayout.WEST);
		add(center);
		add(vitesse, BorderLayout.EAST);
		//Backgrounds/Foregrounds
		left.setBackground(Color.gray);
		start.setBackground(Color.gray);
		stop.setBackground(Color.gray);
		create.setBackground(Color.gray);
		random.setBackground(Color.gray);
		random.setForeground(Color.WHITE);
		create.setForeground(Color.WHITE);
		vitesse.setBackground(Color.gray);
		//Borders
		start.setBorder(BorderFactory.createRaisedBevelBorder());
		stop.setBorder(BorderFactory.createRaisedBevelBorder());
		create.setBorder(BorderFactory.createRaisedBevelBorder());
		random.setBorder(BorderFactory.createRaisedBevelBorder());
		//Listeners
		start.addActionListener(e -> {
			((Modele)o).setCode(VueSaisie.RetourneTexte());
			m.setButtonPause();
			if (m.getMaxTemps() != 0) {
				m.setPause();
			}
			if (m.getStop() == true) {
				m.setStop();
			}
			Thread t = new Thread(m, "Toucan");
			t.start();
		}
		);
		stop.addActionListener(e -> {
			if (m.getPause() == true) {
				m.setPause();
			}
			if (m.getButtonPause() == false){
				m.setButtonPause();
			}
			if(m.getStop() == false){
				m.setStop();
			}
			m.create(true);
		});
		create.addActionListener(e -> {
			try {
				m.buildTab(jtf.getText().replaceAll("\\s", ""));
				if (m.getPause() == true) {
					m.setPause();
				}
				if (m.getButtonPause() == false){
					m.setButtonPause();
				}
				m.create(false);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(),
						"Erreur", JOptionPane.ERROR_MESSAGE);
			} catch (LongueurChaineException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),
						"Erreur", JOptionPane.ERROR_MESSAGE);
			}
		});
		random.addActionListener(e -> {
			m.buildTab();
			if (m.getPause() == true) {
				m.setPause();
			}
			if (m.getButtonPause() == false){
				m.setButtonPause();
			}
			m.create(false);
		});
		vitesse.addChangeListener(e -> {
			m.setVitesse(vitesse.getValue());
		});
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		ImageIcon playLogo = new ImageIcon(getClass().getResource("play logo.png"));
		ImageIcon waitLogo = new ImageIcon(getClass().getResource("wait logo.png"));

		if (m.getButtonPause() == true){
			start.setIcon(playLogo);
		} else {
			start.setIcon(waitLogo);
		}
		if (m.getStop() == false) {
			create.setEnabled(false);
			random.setEnabled(false);
		} else if (m.getStop() == true){
			random.setEnabled(true);
			create.setEnabled(true);
		}
		if (m.isPut() == true){
			start.setEnabled(false);
		} else if (m.isPut() == false) {
			start.setEnabled(true);
		}
		if (arg1 != null) {
			((Algo)arg1).addObserver(this);
		} else {
			jtf.setText(m.getTabString());
		}
	}
}
