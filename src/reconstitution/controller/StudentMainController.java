package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import reconstitution.models.Evaluation;
import reconstitution.models.Exercice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class StudentMainController implements Initializable {

    private static boolean playPauseSwitch = false;
    private static boolean muteSwitch = false;
    private static boolean evaluation;

    private MediaPlayer mediaPlayer;
    private Exercice exo;

    private Date compteur;

    @FXML
    ProgressBar mediaProgressBar;

    @FXML
    TextField reponseTextField;

    @FXML
    TextArea mediaTextArea;

    @FXML
    Label time, consigne, mediaTime;

    @FXML
    MediaView mediaView;

    @FXML
    Button playButton, muteButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exo = StudentHomeController.exo;
        if(exo instanceof Evaluation){
            evaluation = true; // TODO: Quelle utilité ?
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.SECOND, ((Evaluation) exo).getDuree());
            compteur = c.getTime();
            time.setText("Temps restant : "+timeRemaining());
        }
        initMediaPlayer();
        consigne.setText("Consigne : "+exo.getConsigne());
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i < exo.getTexte().getTexteClair().length; i++) {
            stringBuffer.append(exo.getTexte().getTexteClair()[i]);
        }
        //mediaTextArea.setText(stringBuffer.toString());
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

    @SuppressWarnings("all")
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

    @SuppressWarnings("all")
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

    @FXML
    public void initMediaPlayer(){
        // Loading byte[] media into a temporary file
        File tempFile = null;
        try {
            tempFile = File.createTempFile("reconstitution-video-temp", ".bin");
            try (FileOutputStream fos = new FileOutputStream(tempFile.getAbsolutePath())) {
                fos.write(exo.getMedia().getMediaByte());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Creating mediaPlayer
        mediaPlayer = new MediaPlayer(new Media(tempFile.toURI().toString()));

        // Creation mediaView
        mediaView.setMediaPlayer(mediaPlayer);
        mediaView.setOnMouseClicked(mouseEvent -> playPause());
        mediaProgressBar.setProgress(0);
        mediaTime.setText(new SimpleDateFormat("H:mm:ss").format(new Date((long) mediaPlayer.getCurrentTime().toMillis()-3600000))+"/"+new SimpleDateFormat("H:mm:ss").format(new Date((long) mediaPlayer.getMedia().getDuration().toMillis()-3600000)));
        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue)->{
            mediaProgressBar.setProgress(getPercentage(mediaPlayer));
            mediaTime.setText(new SimpleDateFormat("H:mm:ss").format(new Date((long) mediaPlayer.getCurrentTime().toMillis()-3600000))+"/"+new SimpleDateFormat("H:mm:ss").format(new Date((long) mediaPlayer.getMedia().getDuration().toMillis()-3600000)));
        });
        mediaProgressBar.setOnMouseClicked(mouseEvent -> {
            setMediaCursor(mouseEvent.getX()/mediaProgressBar.getWidth());
        });
        mediaTime.setOnMouseClicked(mouseEvent -> {
            double padding = (mediaProgressBar.getWidth()-mediaTime.getWidth())/2;
            setMediaCursor((mouseEvent.getX()+padding)/mediaProgressBar.getWidth());
        });
    }

    public String timeRemaining(){

        return "";
    }

}
