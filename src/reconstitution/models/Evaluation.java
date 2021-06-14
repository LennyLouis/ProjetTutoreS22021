package reconstitution.models;

import java.io.Serializable;

public class Evaluation extends Exercice implements Serializable {

	// Durée en minutes
	private int duree;

	//Constructeur
	public Evaluation() {
		super();
	}

	//Setters & Getters
	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}
	
	
	
	
}
