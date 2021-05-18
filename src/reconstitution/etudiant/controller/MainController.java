package reconstitution.etudiant.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

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
        String MEDIA_URL = "https://liveexample.pearsoncmg.com/common/sample.mp4";

        Media media = new Media(MEDIA_URL);
        mediaPlayer = new MediaPlayer(media);

        mediaView.setMediaPlayer(mediaPlayer);
    }

    boolean playPauseSwitch = false;

    @FXML
    public void play() {
        if (playPauseSwitch) {
            mediaPlayer.play();
            playPauseSwitch = false;
        } else {
            playPauseSwitch = true;
            mediaPlayer.pause();
        }
    }

}
