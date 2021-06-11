package reconstitution.models;

import java.io.Serializable;

public class Consigne implements Serializable {
	private String contenu;

	//Constrcteur
	public Consigne(String contenu) {
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
