package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentMainController implements Initializable {

    private boolean playPauseSwitch;
    private boolean muteSwitch;

    MediaPlayer mediaPlayer;

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
        muteSwitch = false;
        playPauseSwitch = false;
        String MEDIA_URL = "https://liveexample.pearsoncmg.com/common/sample.mp4";

        Media media = new Media(MEDIA_URL);
        mediaPlayer = new MediaPlayer(media);

        mediaView.setMediaPlayer(mediaPlayer);
    }

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

    @FXML
    public void mute(){
        mediaPlayer.setMute(muteSwitch);
        muteSwitch = !muteSwitch;
    }

    @FXML
    public void reponse(){

    }

}
