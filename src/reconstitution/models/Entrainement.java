package reconstitution.models;

public class Entrainement extends Exercice{
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
