package reconstitution.models;

import java.io.Serializable;
import java.util.Locale;

public class Texte implements Serializable {
	private String texteOccult[];
	private String texteClair[];
	private char occultChar;
	private int tailleTableau;
	private int nbMotsDecouv;
	private int nbMotsTotal;
	private int mode;
	private boolean sensiCasse;

	//Constructeur
	public Texte(char occultChar, boolean sensiCasse) {
		super();
		this.tailleTableau = 0;
		this.occultChar = occultChar;
		this.sensiCasse = sensiCasse;
		this.texteOccult = new String[3000];
		this.texteClair = new String[3000];
	}

	//Methodes
	public void entrerTexteProf(String texte) {
		String mot = "";
		int c = 0;
		for(int i = 0; i < texte.length(); i++){
			if(texte.charAt(i) != ' ' && texte.charAt(i) != '.' && texte.charAt(i) != '!' && texte.charAt(i) != '?' && texte.charAt(i) != ',') {
				mot = mot + texte.charAt(i);
			}
			if(texte.charAt(i) == '.' || texte.charAt(i) == '!' || texte.charAt(i) =='?' || texte.charAt(i) ==',' || texte.charAt(i) == ' '){
				texteClair[c] =  mot;
				if(texteOccult[c] == null){
					texteOccult[c] = "";
				}
				for(int a = 0; a < mot.length(); a++){
					texteOccult[c] = texteOccult[c] + occultChar;
				}
				c++;
				tailleTableau++;
				nbMotsTotal++;
				texteClair[c] = Character.toString(texte.charAt(i));
				texteOccult[c] = Character.toString(texte.charAt(i));
				c++;
				tailleTableau++;
				mot = "";
				}
			}
		}


	public String getVisibleTextOccult() {
		String visibleTexteOccult = "";
		String[] tableauTexteOccult = getTextOccult();
		for(int i = 0; i < tailleTableau; i++) {
			visibleTexteOccult = visibleTexteOccult + texteOccult[i];
		}
		return visibleTexteOccult;
	}

	public String getVisibleTextClair() {
		String visibleTexteClair = "";
		String[] tableauTexteClair = getTextClair();
		for(int i = 0; i < tailleTableau; i++) {
			visibleTexteClair = visibleTexteClair + texteClair[i];
		}
		return visibleTexteClair;
	}

	public void entrerMotEtu(String mot) {
		switch(mode) {
			case 2:								//Mot incomplet 2 lettres
				if(!isSensiCasse()) {
					for(int i = 0; i < tailleTableau; i++) {
						if(texteClair[i].length() >= mot.length()) {
							if(texteClair[i].length() >= 2 && mot.length() >= 2) {
								for(int a = 0; a < mot.length(); a++) {
									if(texteClair[i].charAt(a) == mot.charAt(a)) {
										texteOccult[i] = texteClair[i];
										nbMotsDecouv++;
									}
								}
							}else {
								if(texteClair[i].equals(mot)) {
									texteOccult[i] = texteClair[i];
									nbMotsDecouv++;
								}
							}
						}
					}
				}
				else {
					for(int i = 0; i < tailleTableau; i++) {
						boolean identique = false;
						if(mot.length() >= 2 && texteClair[i].length() >= 2 && mot.length() <= texteClair[i].length()) {
							if(mot.toLowerCase(Locale.ROOT).equals(texteClair[i].toLowerCase(Locale.ROOT).substring(0, mot.length()))){
								identique = true;
							}
							if(identique) {
								texteOccult[i] = texteClair[i];
								nbMotsDecouv++;
							}
						}else {
							if(texteClair[i].toLowerCase().equals(mot.toLowerCase())) {
								texteOccult[i] = texteClair[i];
								nbMotsDecouv++;
							}
						}
					}
				}

				break;

			case 3:								//Mot incomplet 2 lettres
				if(!isSensiCasse()) {
					for(int i = 0; i < tailleTableau; i++) {
						if(texteClair[i].length() >= mot.length()) {
							if(texteClair[i].length() >= 3 && mot.length() >= 3) {
								for(int a = 0; a < mot.length(); a++) {
									if(texteClair[i].charAt(a) == mot.charAt(a)) {
										texteOccult[i] = texteClair[i];
										nbMotsDecouv++;
									}
								}
							}else {
								if(texteClair[i].equals(mot)) {
									texteOccult[i] = texteClair[i];
									nbMotsDecouv++;
								}
							}
						}
					}
				}
				else {
					for(int i = 0; i < tailleTableau; i++) {
						boolean identique = false;
						if(mot.length() >= 3 && texteClair[i].length() >= 3 && mot.length() <= texteClair[i].length()) {
							if(mot.toLowerCase(Locale.ROOT).equals(texteClair[i].toLowerCase(Locale.ROOT).substring(0, mot.length()))){
								identique = true;
							}

							if(identique) {
								texteOccult[i] = texteClair[i];
								nbMotsDecouv++;
							}
						}else {
							if(texteClair[i].toLowerCase().equals(mot.toLowerCase())) {
								texteOccult[i] = texteClair[i];
								nbMotsDecouv++;
							}
						}
					}
				}

				break;
			default: 							//Mot incomplet désactivé
				if(!isSensiCasse()) {
					for(int i = 0; i < tailleTableau; i++) {
						if(texteClair[i].equals(mot)) {
							texteOccult[i] = texteClair[i];
							nbMotsDecouv++;
						}
					}
				}
				else {
					for(int i = 0; i < tailleTableau; i++) {
						boolean identique = false;
						if(mot.toLowerCase().equals(texteClair[i].toLowerCase())) {
							identique = true;
						}
						if(identique) {
							texteOccult[i] = texteClair[i];
							nbMotsDecouv++;
						}
					}
				}

				break;

		}

	}

	//Setters & Getters
	public String[] getTextOccult() {
		return texteOccult;
	}

	public String[] getTextClair() {
		return texteClair;
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

	public String[] getTexteClair() {
		return texteClair;
	}

	public boolean isSensiCasse() {
		return sensiCasse;
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

	public void setMode(int mode) {
		this.mode = mode;
	}

}
