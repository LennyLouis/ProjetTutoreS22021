package reconstitution.models;

import java.util.Locale;

public class test {
    public static void main(String[] args) {
        //Exercice exo = new Exercice("titre");
        //exo.setConsigne("consigne");
        //exo.setMedia(null);
        Texte text = new Texte('/', false);
        text.setMode(1);
        text.entrerTexteProf("Je suis le texte. je pue je pète je prend mon cul pour une trompette!");
        //exo.setTexte(text);
        System.out.println(text.getVisibleTextOccult());
        text.entrerMotEtu("suis");
        System.out.println(text.getVisibleTextOccult());
        text.entrerMotEtu("Je");
        System.out.println(text.getVisibleTextOccult());
        text.entrerMotEtu("le");
        System.out.println(text.getVisibleTextOccult());
        text.entrerMotEtu("tex");
        System.out.println(text.getVisibleTextOccult());


    }
}
