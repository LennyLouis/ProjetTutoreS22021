package reconstitution.models;

import java.io.Serializable;

public class Entrainement extends Exercice implements Serializable {
	private Aide aide;

	//Constructeurs
	public Entrainement() {
		super();
	}

	//Setters & Getters
	public Aide getAide() {
		return aide;
	}

	public void setAide(Aide aide) {
		this.aide = aide;
	}
	
}
