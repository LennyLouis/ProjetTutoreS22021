package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import reconstitution.MainTeacher;
import reconstitution.models.Exercice;
import reconstitution.models.Resultat;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class TeacherCorrectController implements Initializable {

    private Resultat resultat;

    @FXML
    Label nbMots, timeLabel, nomEtu;

    @FXML
    TextArea texteComplet, reponseEtu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void homeButton() throws IOException {
        MainTeacher.setView("/view/teacherHomeMenuView.fxml");
    }

    @FXML
    public void immport() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importer un rendu d'exercice");
        File file = fileChooser.showOpenDialog(MainTeacher.getStage());
        if(file!=null) {
            try {
                resultat = (Resultat) Exercice.ouvrir(file.getAbsolutePath());
            } catch (Exception e){
                e.printStackTrace();
                return;
            }
            texteComplet.setText(resultat.getTexte().getVisibleTextClair());
            reponseEtu.setText(resultat.getTexte().getVisibleTextOccult());
            nbMots.setText(resultat.getMotsTrouve()+"/"+resultat.getMotsTotal());
            nomEtu.setText("Réponse de " + resultat.getNom() + " " + resultat.getPrenom());
            if(resultat.getTime()<3660000) {
                timeLabel.setText(new SimpleDateFormat("mm:ss").format(resultat.getTime()-3600000));
            } else {
                timeLabel.setText(new SimpleDateFormat("HH:mm:ss").format(resultat.getTime()-3600000));
            }
        }
    }


}
