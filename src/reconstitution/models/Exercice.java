package reconstitution.models;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Exercice {
	private String titre;
	private Texte texte;
	private Media media;
	private Consigne consigne;
	
	//Constructeurs
	public Exercice() {
		super();
	}
	
	public Exercice(String titre) {
		super();
		this.titre = titre;
	}

	//Setters & Getters
	public Texte getTexte() {
		return texte;
	}

	public Media getMedia() {
		return media;
	}
	
	public String getTitre() {
		return titre;
	}

	public Consigne getConsigne() {
		return consigne;
	}

	public void setTexte(Texte texte) {
		this.texte = texte;
	}

	public void setMedia(Media media) {
		this.media = media;
	}
	
	public void setTitre() {
		this.titre = titre;
	}

	public void setConsigne(Consigne consigne) {
		this.consigne = consigne;
	}
	
	//Methodes
	public void sauvegarder(Exercice exercice, String nomFichier) {
		try {
            FileOutputStream file = new FileOutputStream(nomFichier);
            ObjectOutputStream object = new ObjectOutputStream(file);
            object.writeObject(exercice);
            object.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	
	
	
	
	
}
