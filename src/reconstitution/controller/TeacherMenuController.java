package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherMenuController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void createExercice(){
        System.out.println("exercice");
    }

    @FXML
    public void createEvaluation() {
        System.out.println("evaluation");
    }

    @FXML
    public void correctExercice() {
        System.out.println("correct");
    }
}