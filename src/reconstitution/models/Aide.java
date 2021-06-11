package reconstitution.models;

import java.io.Serializable;

public class Aide implements Serializable {
	private String contenu;
	
	//Constructeur
	public Aide(String contenu) {
		super();
		this.contenu = contenu;
	}
	
	//Setters & Getters
	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
}
