package reconstitution.models;

import java.io.Serializable;

public class Entrainement extends Exercice implements Serializable {
	private Aide aide;

	//Constructeurs
	public Entrainement() {
		super();
		this.getTexte().setMode(1);
	}

	//Setters & Getters
	public Aide getAide() {
		return aide;
	}

	public void setAide(Aide aide) {
		this.aide = aide;
	}
	
}
