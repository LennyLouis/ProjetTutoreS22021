package reconstitution.models;

import java.util.Locale;

public class test {
    public static void main(String[] args) {
        //Exercice exo = new Exercice("titre");
        //exo.setConsigne("consigne");
        //exo.setMedia(null);
        Texte text = new Texte('/', false);
        text.setMode(1);
        text.entrerTexteProf("Une souris verte qui courait dans l'herbe. Je l'attrape par sa grosse queue et je le fourre en lui brulant les yeux. Je la prend par sa tete.");
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
