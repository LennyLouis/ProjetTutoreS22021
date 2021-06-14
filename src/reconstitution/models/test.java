package reconstitution.models;

public class test {
    public static void main(String[] args) {
        //Exercice exo = new Exercice("titre");
        //exo.setConsigne("consigne");
        //exo.setMedia(null);
        Texte text = new Texte('#', true);
        text.entrerTexteProf("Je suis le texte. je pue je pète!");
        //exo.setTexte(text);
        for(int i = 0; i<11;i++) {
            System.out.println(text.getTexteClair()[i]);
        }

        for(int i = 0; i<10;i++) {
            System.out.println(text.getTextOccult()[i]);
        }
        System.out.println(text.getVisibleTextOccult());

    }
}
