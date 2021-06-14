package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import reconstitution.MainStudent;
import reconstitution.MainTeacher;
import reconstitution.models.Evaluation;
import reconstitution.models.Exercice;
import reconstitution.models.Resultat;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;

public class StudentMainController implements Initializable {

    private static boolean playPauseSwitch = true;
    private static boolean muteSwitch = true;

    private MediaPlayer mediaPlayer;
    private Exercice exo;

    private Date dateDebutFin;
    private long compteur;
    public static Timer timer;

    private boolean isItGood;

    @FXML
    ProgressBar mediaProgressBar;

    @FXML
    TextField reponseTextField;

    @FXML
    TextArea mediaTextArea;

    @FXML
    Label time, consigne, mediaTime, typeExercice;

    @FXML
    MediaView mediaView;

    @FXML
    Button playButton, muteButton, solutionButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exo = StudentHomeController.exo;
        initMediaPlayer();

        if(exo instanceof Evaluation){
            // Initialisation du timer
            initTimeRemain();
            // Mise a jour du timer
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    javafx.application.Platform.runLater(() -> updateTimeRemain());
                }
            }, 0, 1000);
        } else {
            // Initialisation du timer
            initTimeLeft();
            // Mise a jour du timer
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    javafx.application.Platform.runLater(() -> updateTimeLeft());
                }
            }, 0, 1000);
        }
        // Consigne
        consigne.setText("Consigne : "+exo.getConsigne());
        // Script du média
        mediaTextArea.setText(exo.getTexte().getVisibleTextOccult());
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
    public void entrerTexte(){
        exo.getTexte().entrerMotEtu(reponseTextField.getText());
        System.out.println(reponseTextField.getText());
        System.out.println(exo.getTexte().getVisibleTextOccult());
        mediaTextArea.setText(exo.getTexte().getVisibleTextOccult());
    }

    @FXML
    public void afficherLaSolution(){
        mediaTextArea.setText(exo.getTexte().getVisibleTextClair());
        solutionButton.setText("Masquer la Solution");
    }

    @FXML
    public void save(){
        openNameDialog();
        Stage nameStage = new Stage();
        HBox hBoxTextField = new HBox();
        HBox hBoxButton = new HBox();
        VBox vBox = new VBox();
        TextField nom = new TextField();
        TextField prenom = new TextField();
        nom.setPromptText("Nom");
        prenom.setPromptText("Prénom");
        Button retour = new Button("Retour");
        Button valider = new Button("Valider");
        retour.setOnAction(e -> {
            nameStage.close();
        });
        hBoxTextField.getChildren().addAll(nom, prenom);
        valider.setOnAction(e -> {
            isItGood = true;
            for (Node child : hBoxTextField.getChildren()) {
              if(child instanceof TextField){
                  if(((TextField) child).getText().length()<1){
                      isItGood = false;
                      child.getStyleClass().add("empty-field");
                  } else {
                      child.getStyleClass().removeAll("empty-field");
                  }
              }
            }
            System.out.println(isItGood);
            if(isItGood){
                nameStage.close();
                Resultat resultat = new Resultat(12, 24, nom.getText(), prenom.getText(), exo.getTexte(), 3600000);
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Rendre votre exercice");
                //fileChooser.setSelectedExtensionFilter(); //TODO: extension de fichier dans le FileChooser
                File file = fileChooser.showSaveDialog(MainTeacher.getStage());
                if (file != null) {
                    Exercice.sauvegarder(exo, file.getAbsolutePath());
                }
                System.out.println("Fin programme");
            }
        });
        hBoxButton.getChildren().addAll(retour, valider);
        vBox.getChildren().addAll(hBoxTextField, hBoxButton);
        nameStage.setTitle("Sauvegarde réussie !");
        Scene scene = new Scene(vBox, 400, 100);
        scene.getStylesheets().add(String.valueOf(MainStudent.class.getResource("/style.css")));
        nameStage.setScene(scene);
        nameStage.setResizable(false);
        nameStage.show();
    }

    @FXML
    public void help(){

    }

    @FXML
    public void apropos(){

    }

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
            if(mediaPlayer.getCurrentTime().toMillis()>8.0) setMediaCursor(mouseEvent.getX()/mediaProgressBar.getWidth());
        });
        mediaTime.setOnMouseClicked(mouseEvent -> {
            double padding = (mediaProgressBar.getWidth()-mediaTime.getWidth())/2;
            if(mediaPlayer.getCurrentTime().toMillis()>8.0) setMediaCursor((mouseEvent.getX()+padding)/mediaProgressBar.getWidth());
        });
    }

    public void initTimeRemain(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MINUTE, ((Evaluation) exo).getDuree());
        dateDebutFin = c.getTime();
    }

    public void updateTimeRemain() {
        compteur = dateDebutFin.getTime() - new Date().getTime();
        if(compteur<3660000) {
            time.setText("Temps restant : " + new SimpleDateFormat("mm:ss").format(compteur-3600000));
        } else {
            time.setText("Temps restant : " + new SimpleDateFormat("HH:mm:ss").format(compteur-3600000));
        }
    }

    public void initTimeLeft(){
        dateDebutFin = new Date();
    }

    public void updateTimeLeft(){
        compteur = new Date().getTime()-dateDebutFin.getTime();
        if(compteur<3660000) {
            time.setText("Temps écoulé : " + new SimpleDateFormat("mm:ss").format(compteur-3600000));
        } else {
            time.setText("Temps écoulé : " + new SimpleDateFormat("HH:mm:ss").format(compteur-3600000));
        }
    }

    public void openNameDialog(){

    }

}
