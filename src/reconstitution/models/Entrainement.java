package reconstitution.models;

import java.io.Serializable;

public class Entrainement extends Exercice implements Serializable {
	private String aide;

	//Constructeurs
	public Entrainement() {
		super();
	}

	//Setters & Getters
	public String getAide() {
		return aide;
	}

	public void setAide(String aide) {
		this.aide = aide;
	}
	
}
