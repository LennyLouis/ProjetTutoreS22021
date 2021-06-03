package reconstitution.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherMenuController implements Initializable {

    @FXML
    ImageView createExercise, createEvaluation, correctExercise;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createExercise.setOnMouseClicked(mouseEvent -> newExercise());
        createEvaluation.setOnMouseClicked(mouseEvent -> newEvaluation());
        correctExercise.setOnMouseClicked(mouseEvent -> correction());
    }

    public void newExercise(){
        System.out.println("exercice");
    }

    public void newEvaluation() {
        System.out.println("evaluation");
    }

    public void correction() {
        System.out.println("correct");
    }
}