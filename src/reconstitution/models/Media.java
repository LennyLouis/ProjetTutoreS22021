package reconstitution.models;


import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Media implements Serializable {
	private String adresse;
	private byte[] mediaByte;

	//Constructeur
	public Media(URI adresse) throws IOException {
		super();
		this.adresse = adresse.toString();
		this.mediaByte = Files.readAllBytes(Paths.get(adresse));
	}

	//Getters & Setters

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public byte[] getMediaByte() {
		return mediaByte;
	}
}
