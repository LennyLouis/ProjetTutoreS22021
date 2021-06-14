package reconstitution.models;

public class test {
    public static void main(String[] args) {
        //Exercice exo = new Exercice("titre");
        //exo.setConsigne("consigne");
        //exo.setMedia(null);
        Texte text = new Texte('/', false);
        text.entrerTexteProf("Je suis le texte. je pue je pète!");
        //exo.setTexte(text);
        System.out.println(text.getVisibleTextOccult());
        text.entrerMotEtu("suis");
        System.out.println(text.getVisibleTextOccult());
        text.entrerMotEtu("Je");
        System.out.println(text.getVisibleTextOccult());
        text.entrerMotEtu("le");
        System.out.println(text.getVisibleTextOccult());


    }
}
