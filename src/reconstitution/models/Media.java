package reconstitution.models;

public class Media {
	private String nom;
	private String adresse;
	
	//Constructeur
	public Media(String nom, String adresse) {
		super();
		this.nom = nom;
		this.adresse = adresse;
	}

	//Getters & Setters
	public String getNom() {
		return nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
	
	
}
