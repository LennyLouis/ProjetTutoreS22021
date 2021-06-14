package reconstitution.models;

import java.io.*;

public class Exercice implements Serializable {
	private String titre;
	private Texte texte;
	private Media media;
	private String consigne;
	private boolean tempReel;
	private boolean showSolution;
	private String aide;
	
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

	public String getConsigne() {
		return consigne;
	}

	public boolean getTempReel() {
		return tempReel;
	}

	public boolean didShowSolution() {
		return showSolution;
	}

	public void setTexte(Texte texte) {
		this.texte = texte;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public void setTempReel(boolean tempReel) {
		this.tempReel = tempReel;
	}

	public void setShowSolution(boolean showSolution) {
		this.showSolution = showSolution;
	}

	public boolean getShowSolution(){
		return showSolution;
	}

	public void setAide(String aide) {
		this.aide = aide;
	}

	public String getAide(){
		return aide;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}
	
	//Methodes
	public static void sauvegarder(Object exercice, String path) {
		try {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream object = new ObjectOutputStream(file);
            object.writeObject(exercice);
            object.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public static Object ouvrir(String path) throws IOException {
		Object o = null;

		try (ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(path))) {
			o = objectInput.readObject();
		} catch (EOFException eof) {
			System.out.println("Ce fichier n'est pas un fichier d'exercice.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		return o;
	}
	
	
	
	
	
	
	
}
