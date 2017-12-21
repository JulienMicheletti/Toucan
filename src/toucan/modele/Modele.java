package toucan.modele;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;

import toucan.modele.algos.Algo;
import toucan.modele.algos.AlgoBulle;
import toucan.modele.algos.AlgoFacade;
import toucan.modele.algos.AlgoInsertion;
import toucan.modele.algos.AlgoSelection;
import toucan.modele.cases.Case;
import toucan.modele.cases.LesCases;
import toucan.modele.exceptions.LongueurChaineException;

public class Modele extends Observable implements Iterable<Case>, Runnable {
	private LesCases cases;
	private Algo algo;
	private int nbCases;
	private String select;
	private boolean pause;
	private boolean bPause;
	private boolean stop;
	private boolean putText;
	private int speed;
	private int[] valeurs;
	private int[] oldValeurs;
	private String code;
	private String erreur;

	/**
	 * Constructeur de la class Modele
	 * @param tab tableau d'entier contenant les valeurs des cases � ajouter
	 */
	public Modele(int[] tab) {
		super();
		assert(tab != null);
		bPause = true;
		valeurs = tab;
		select = "bulle";
		pause = false;
		putText = false;
		stop = true;
		speed = 2;
		oldValeurs = valeurs.clone();
		create(false);
		erreur = "";
	}

	/**
	 * Fonction de parsing et construction du tableau de valeurs
	 * @param str Chaine entree par l'utilisateur � parser pour recuperer les valeurs des cases
	 * 
	 */
	public void buildTab(String str) throws LongueurChaineException, NumberFormatException {
		assert(str != null);
		String[] tab = str.split(",");
		int[] tmp = new int[tab.length];
		
		if (tab.length > 15) {
			throw new LongueurChaineException();
		}
		for (int i = 0; i < tmp.length; i++) {
			try {
				tmp[i] = Integer.parseInt(tab[i]);
			} catch (NumberFormatException e) {
				throw new NumberFormatException("La chaine de nombres est invalide");
			}
		}
		valeurs = tmp.clone();
	}

	/**
	 * Fonction de construction du tableau de valeurs aléatoire
	 */
	public void buildTab() {
		Random r = new Random();
		int nb = r.nextInt(6) + 5;
		valeurs = new int[nb];
		for (int i = 0; i < nb; i++) {
			valeurs[i] = r.nextInt(100);
		}
	}

	/**
	 * Fonction de creation du tableau de cases
	 */
	public void create(boolean ancien) {
		if (ancien) {
			valeurs = oldValeurs.clone();
		}
		oldValeurs = valeurs.clone();
		cases = new LesCases(valeurs);
		nbCases = valeurs.length + 1; // +1 car ajout case var
		setPosition();
		setChanged();
		notifyObservers();
	}

	/**
	 * Fonction determinant si un code a deja ete rentre par l utilisateur
	 * @param b
	 */
	public void putText(boolean b){
		this.putText = b;

	}

	/**
	 * Fonction verifiant si un code a deja ete rentre
	 * @return putText
	 */
	public boolean isPut(){
		return putText;
	}


	/**
	 * Fonction de lancement d'un algorithme choisit par l'utilisateur
	 */
	public void run() {
		if (getPause() == false && isPut() == false) {
			//Selection de l'algorithme en fonction de la variable select
			if (select.equals("bulle")) {
				algo = new AlgoBulle(cases, valeurs);
			} else if (select.equals("insertion")) {
				algo = new AlgoInsertion(cases, valeurs);
			} else if (select.equals("selection")) {
				algo = new AlgoSelection(cases, valeurs);
			} else if (select.equals("algo personnel")) {
				algo = new AlgoFacade(cases, this, valeurs);
			}
			setChanged();
			notifyObservers(algo);
			try {
				algo.trier();
			} catch (Exception e) {
				erreur = e.getMessage();
				setChanged();
				notifyObservers();
				erreur = "";
			}
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Fonction qui retourne l'erreur
	 *
	 * @return erreur
	 */
	public String getErreur() {
		return erreur;
	}

	/**
	 * Fonction qui initialise les coordonnees des cases
	 */
	public void setPosition() {
		int x = 0;
		int i = 0;
		//Positionnement de toutes les cases 
		for (;i < getNbCases() - 1; i++) {
			cases.setPosition(i, x, 0);
			x += 50;
		}
		cases.ajouterCase(new Case(0)); // Case var
		cases.setPosition(i, 0, 200);
	}

	/**
	 * Fonction d ajout du code de l utilisateur
	 * @param code code rentre par l utilisateur
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Fonction de retour du code de l utilisateur
	 * @return code code rentre par l utilisateur
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Fonction d'initialisation du boolean qui demande la pause de l'algorithme actuel
	 */
	public void setPause() {
		pause = pause == true ? false : true;
	}

	/**
	 * Fonction de retour du boolean qui definit la pause de l'algo actuel
	 * @return pause 
	 */
	public boolean getPause() {
		return pause;
	}

	/**
	 * Fonction de retour du boolean qui definit l arret de l'algo actuel
	 * @return stop;
	 */
	public boolean getStop() {
		return stop;
	}

	/**
	 * Fonction d'initialisation du boolean qui demande l arret de l'algorithme actuel
	 */
	public void setStop() {
		stop = stop == false ? true : false;
		putText(false);
	}

	/**
	 * Fonction qui retourne le tableau en string
	 * @return str
	 */
	public String getTabString() {
		String str = new String();
		for (int i = 0; i < valeurs.length; i++){
			str += (i == 0) ? (""+valeurs[i]) : (", "+valeurs[i]);
		}
		return str;
	}

	/**
	 * Fonction qui initialise la vitesse d'execution de l'algorithme actuel
	 * @param speed vitesse definie par l'utilisateur
	 */
	public void setVitesse(int speed) {
		this.speed = speed;
	}
	
	
	/**
	 * Fonction qui retourne la vitesse d'execution de l'algorithme actuel
	 * @return speed vitesse definie par l'utilisateur
	 */
	public int getVitesse() {
		return speed;
	}

	/**
	 * Fonction qui initialise la variable de selection de l'algorithme demande
	 * @param s Algorithme selectionne par l'utilisateur
	 */
	public void setSelect(String s) {
		assert(s != null);
		select = s;
		setChanged();
		notifyObservers();
	}

	/**
	 * Fonction qui retourne la variable de selection de l'algorithme demande
	 * @return select Algorithme selectionne par l'utilisateur
	 */
	public String getSelect() {
		return select;
	}

	/**
	 * Fonction qui retourne le nombre de cases actuel
	 * @return nbCases nombre de cases du tableau
	 */
	public int getNbCases() {
		return nbCases;
	}

	/**
	 * Fonction qui retourne le temps de mouvement actuel des cases
	 * @return cases.getMaxTemps() Temps total du mouvement des cases
	 */
	public int getMaxTemps() {
		return cases.getMaxTemps();
	}

	/**
	 * Fonction d'initialisation du boolean qui verifie le mode actuel du bouton start/pause
	 */
	public void setButtonPause() {bPause = bPause == false ? true : false;}

	/**
	 * Fonction de retour de l etat actuel du bouton start/pause
	 * @return bPause
	 */
	public boolean getButtonPause() {
		return bPause;
	}

	@Override
	public String toString() {
		return cases.toString();
	}

	@Override
	public Iterator<Case> iterator() {
		return cases.iterator();
	}
}
