package reconstitution.models;

public class Resultat {
    private final int motsTrouve;
    private final int motsTotal;
    private final String nom;
    private final String prenom;
    private final Texte texte;

    public Resultat(int motsTrouve, int motsTotal, String nom, String prenom, Texte texte) {
        this.motsTrouve = motsTrouve;
        this.motsTotal = motsTotal;
        this.nom = nom;
        this.prenom = prenom;
        this.texte = texte;
    }

    public int getMotsTrouve() {
        return motsTrouve;
    }

    public int getMotsTotal() {
        return motsTotal;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Texte getTexte() {
        return texte;
    }
}
