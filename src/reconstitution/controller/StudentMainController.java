package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentMainController implements Initializable {

    private static boolean playPauseSwitch = false;
    private static boolean muteSwitch = false;

    private MediaPlayer mediaPlayer;

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

    @FXML
    Button playButton, muteButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String MEDIA_URL = "file:/C:/Users/lennylouis/Downloads/sample.mp4";

        mediaPlayer = new MediaPlayer(new Media(MEDIA_URL));

        mediaView.setMediaPlayer(mediaPlayer);
        mediaView.setOnMouseClicked(mouseEvent -> playPause());
        mediaProgressBar.setProgress(0.5);
        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue)->{
            mediaProgressBar.setProgress(getPercentage(mediaPlayer));
        });
        mediaProgressBar.setOnMouseClicked(mouseEvent -> {
            setMediaCursor(mouseEvent.getX()/mediaProgressBar.getWidth());
        });
    }

    public void setMediaCursor(Double time){
        Duration duration = new Duration(time*mediaPlayer.getMedia().getDuration().toMillis());
        mediaPlayer.seek(duration);
    }

    public double getPercentage(MediaPlayer mediaPlayer){
        Double duration = mediaPlayer.getMedia().getDuration().toSeconds();
        Double time = mediaPlayer.getCurrentTime().toSeconds();

        return time/duration;
    }

    @FXML
    public void playPause() {
        if (playPauseSwitch) {
            mediaPlayer.play();
            playButton.setStyle("-fx-background-image: url('reconstitution/resources/images/pause.png');");
        } else {
            mediaPlayer.pause();
            playButton.setStyle("-fx-background-image: url('reconstitution/resources/images/play.png');");
        }
        playPauseSwitch = !playPauseSwitch;
    }

    @FXML
    public void mute(){
        mediaPlayer.setMute(muteSwitch);
        if(muteSwitch){
            muteButton.setStyle("-fx-background-image: url('reconstitution/resources/images/mute.png');");
        } else {
            muteButton.setStyle("-fx-background-image: url('reconstitution/resources/images/unmute.png');");
        }
        muteSwitch = !muteSwitch;
    }

    @FXML
    public void reponse(){

    }

}
