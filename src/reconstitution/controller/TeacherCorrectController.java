package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import reconstitution.MainTeacher;
import reconstitution.models.Exercice;
import reconstitution.models.Resultat;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherCorrectController implements Initializable {

    private Resultat resultat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void homeButton() throws IOException {
        MainTeacher.setView("/view/teacherHomeMenuView.fxml");
    }

    @FXML
    public void import() throws IOException {
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
            //resultat.get....
        }
    }
}
