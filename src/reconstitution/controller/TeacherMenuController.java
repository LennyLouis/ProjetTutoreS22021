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
import reconstitution.MainTeacher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherMenuController implements Initializable {

    private static boolean evaluation;

    @FXML
    ImageView createExercise, createEvaluation, correctExercise;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createExercise.setOnMouseClicked(mouseEvent -> {
            try {
                newExercise();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        createEvaluation.setOnMouseClicked(mouseEvent -> {
            try {
                newEvaluation();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        correctExercise.setOnMouseClicked(mouseEvent -> {
            try {
                correction();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void newExercise() throws IOException {
        evaluation = false;
        MainTeacher.setView("/view/teacherCreateView.fxml");
    }

    public void newEvaluation() throws IOException {
        evaluation = true;
        MainTeacher.setView("/view/teacherCreateView.fxml");
    }

    public void correction() throws IOException {
        MainTeacher.setView("/view/teacherCorrectView.fxml");
    }

    public static boolean isEvaluation() {
        return evaluation;
    }
}