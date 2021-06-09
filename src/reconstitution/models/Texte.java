package reconstitution.models;

public class Texte {
	private String texteOccult[];
	private String texteClair[];
	private char occultChar;
	private int nbMaxMots;
	private int nbMotsDecouv;
	private int nbMotsTotal;
	private boolean sensiCasse;

	//Constructeur
	public Texte(char occultChar, int nbMaxMots, int nbMotsDecouv, int nbMotsTotal, boolean sensiCasse) {
		super();
		this.occultChar = occultChar;
		this.nbMaxMots = nbMaxMots;
		this.nbMotsDecouv = nbMotsDecouv;
		this.nbMotsTotal = nbMotsTotal;
		this.sensiCasse = sensiCasse;
		this.texteOccult = new String[nbMaxMots];
		this.texteClair = new String[nbMaxMots];
	}

	//Setters & Getters
	public String[] getTextOccult() {
		return texteOccult;
	}

	public int getNbMaxMots() {
		return nbMaxMots;
	}

	public char getOccultChar() {
		return occultChar;
	}

	public int getNbMotsDecouv() {
		return nbMotsDecouv;
	}

	public int getNbMotsTotal() {
		return nbMotsTotal;
	}

	public boolean isSensiCasse() {
		return sensiCasse;
	}

	public void setNbMaxMots(int nbMaxMots) {
		this.nbMaxMots = nbMaxMots;
	}

	public void setOccultChar(char occulChar) {
		this.occultChar = occulChar;
	}

	public void setNbMotsTotal(int nbMotsTotal) {
		this.nbMotsTotal = nbMotsTotal;
	}

	public void setSensiCasse(boolean sensiCasse) {
		this.sensiCasse = sensiCasse;
	}

	//Methodes
	public void entrerTexteProf(String texte) {
		int c = 0;									//compteur de "case" utilisées du tableau
		for(int i = 0; i < texte.length(); i++) {
			String mot = "";
			if(texte.charAt(i) != ' ' && texte.charAt(i) != '.' && texte.charAt(i) != '!' && texte.charAt(i) != '?') {
				mot = mot + texte.charAt(i);
			}
			//remplissage du texte en clair
			else {
				texteClair[c] = mot;
				c++;
				nbMotsTotal++;

				//remplissage du texte masqué avec le caratère occultant
				for(int a = 0; a < mot.length(); a++) {
					texteOccult[c] = texteOccult[c] + occultChar;
				}
				mot = "";
			}
			//gestion de la ponctuation
			if(texte.charAt(i) == '.' || texte.charAt(i) == '!' || texte.charAt(i) =='?') {
				String ponctuation = "";
				ponctuation = ponctuation + texte.charAt(i);
				texteClair[c] = ponctuation;
				texteOccult[c] = ponctuation;
			}
		}
	}

	public String getVisibleTextClair() {
		String visibleTexteOccult = "";
		String[] tableauTexteOccult = getTextOccult();
		for(int i = 0; i < nbMotsTotal; i++) {
			visibleTexteOccult = visibleTexteOccult + tableauTexteOccult[i];
		}
		return visibleTexteOccult;
	}

	void entrerMotEtu(String mot) {
		if(isSensiCasse()) {
			for(int i = 0; i < nbMaxMots; i++) {
				if(texteClair[i] == mot) {
					texteOccult[i] = texteClair[i];
				}
			}
		}
		else {
			for(int i = 0; i < nbMaxMots; i++) {
				boolean identique = true;
				for(int a = 0; a < mot.length(); a++) {
					if(mot.toLowerCase() != texteClair[i].toLowerCase()) {
						identique = false;
					}
				}
				if(identique) {
					texteOccult[i] = texteClair[i];
				}
			}
		}
	}

}
