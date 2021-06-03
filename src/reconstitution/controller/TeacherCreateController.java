package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherCreateController implements Initializable {

    MediaPlayer mediaPlayer;

    @FXML
    Button playBouton, muteBouton, pleinecranBouton, reponseBouton;

    @FXML
    ProgressBar mediaProgressBar;

    @FXML
    TextField reponseTextField;

    @FXML
    TextArea mediaTextArea;

    @FXML
    Label tempsRestant, consigne;

    @FXML
    MediaView mediaView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    boolean playPauseSwitch = false;

    @FXML
    public void play() {
        if (playPauseSwitch) {
            mediaPlayer.play();
        } else {
            mediaPlayer.pause();
        }
        playPauseSwitch = !playPauseSwitch;
    }



}
