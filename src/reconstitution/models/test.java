package reconstitution.models;

public class test {
    public static void main(String[] args) {
        //Exercice exo = new Exercice("titre");
        //exo.setConsigne("consigne");
        //exo.setMedia(null);
        Texte text = new Texte('#', true);
        text.setMode(3);
        text.entrerTexteProf("Je suis le texte. je pue je pète!");
        //exo.setTexte(text);
        System.out.println(text.getVisibleTextOccult());
        text.entrerMotEtu("suis");
        text.entrerMotEtu("suis");
        text.entrerMotEtu("suis");
        System.out.println(text.getVisibleTextOccult());
        System.out.println(text.getTexteClair()[4]);
        System.out.println(text.getTextOccult()[4]);

    }
}
