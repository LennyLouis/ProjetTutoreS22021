package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import reconstitution.MainTeacher;
import reconstitution.models.*;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TeacherCreateController implements Initializable {

    // Media player
    private static boolean playPauseSwitch = true;
    private static boolean muteSwitch = true;

    private static Stage menuOption;
    public static List<File> files;

    // JavaFX Nodes
    private Button playPauseButton;
    private Button muteButton;
    private MediaPlayer mediaPlayer;
    private Exercice exo;

    // Options
    private static boolean caseSensitivVar, timeLimitVar, showSolutionVar, realTimeVar;
    private static char occultCharVar;
    private static int timeLimitValueVar, lettersMotVar;

    @FXML
    AnchorPane anchorPane;

    @FXML
    Label evaluationTime;

    @FXML
    Rectangle addMedia;

    @FXML
    VBox player;

    @FXML
    ImageView uploadLogo;

    @FXML
    TextArea consigne, textClair, title, aide;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createOptionStage();
        initOptions();
        files = new ArrayList<>();

        if(!TeacherMenuController.isEvaluation()){
            exo = new Entrainement();
            anchorPane.getChildren().removeAll(evaluationTime);
        } else{
            exo = new Evaluation();
            anchorPane.getChildren().removeAll(aide);
        }
    }

    @FXML
    public void homeButton() throws IOException {
        MainTeacher.setView("/view/teacherHomeMenuView.fxml");
    }

    @FXML
    public void openOpt() throws IOException {
        if(menuOption.isShowing()){
            menuOption.close();
        }else {
            menuOption.show();
        }
    }

    @FXML
    public void saveExercise() {
        if(checkFields()){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Sauvegarder votre exercice");
            File file = fileChooser.showSaveDialog(MainTeacher.getStage());
            if (file != null) {
                constructExercice();
                Exercice.sauvegarder(exo, file.getAbsolutePath());
            }
        }
    }

    @FXML
    public void openExo(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importer un exercice");
        File file = fileChooser.showOpenDialog(MainTeacher.getStage());
        if(file!=null) {
            try {
                Exercice exercice = (Exercice) Exercice.ouvrir(file.getAbsolutePath());
                if(TeacherMenuController.isEvaluation()){
                    exo = new Evaluation();
                } else {
                    exo = new Entrainement();
                    if(exercice instanceof Entrainement) ((Entrainement) exo).setAide(((Entrainement) exercice).getAide());
                }
                exo.setShowSolution(exercice.getShowSolution());
                exo.setConsigne(exercice.getConsigne());
                exo.setTexte(exercice.getTexte());
                exo.setTempReel(exercice.getTempReel());
                exo.setTitre(exercice.getTitre());
                exo.setMedia(exercice.getMedia());
                File tempFile = null;
                try {
                    tempFile = File.createTempFile("reconstitution-video-temp", ".bin");
                    try (FileOutputStream fos = new FileOutputStream(tempFile.getAbsolutePath())) {
                        fos.write(exo.getMedia().getMediaByte());
                    }
                    files.add(tempFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Creating mediaPlayer
                mediaPlayer = new MediaPlayer(new Media(tempFile.toURI().toString()));
                createMediaPlayer();
            } catch (Exception e){
                e.printStackTrace();
                return;
            }
            if(exo instanceof  Evaluation){
                consigne.setText(exo.getConsigne());
                textClair.setText(exo.getTexte().getVisibleTextClair());
                title.setText(exo.getTitre());

            }else{
                consigne.setText(exo.getConsigne());
                textClair.setText(exo.getTexte().getVisibleTextClair());
                title.setText(exo.getTitre());
                aide.setText(((Entrainement) exo).getAide());
            }
        }
    }

    @SuppressWarnings("all")
    @FXML
    public void playPause() {
        if (playPauseSwitch) {
            mediaPlayer.play();
            playPauseButton.setStyle("-fx-background-image: url('reconstitution/resources/images/pause-white.png');");
        } else {
            mediaPlayer.pause();
            playPauseButton.setStyle("-fx-background-image: url('reconstitution/resources/images/play-white.png');");
        }
        playPauseSwitch = !playPauseSwitch;
    }

    @SuppressWarnings("all")
    @FXML
    public void mute(){
        mediaPlayer.setMute(muteSwitch);
        if(muteSwitch){
            muteButton.setStyle("-fx-background-image: url('reconstitution/resources/images/mute-white.png');");
        } else {
            muteButton.setStyle("-fx-background-image: url('reconstitution/resources/images/unmute-white.png');");
        }
        muteSwitch = !muteSwitch;
    }

    public void setMediaCursor(Double time){
        Duration duration = new Duration(time*mediaPlayer.getMedia().getDuration().toMillis());
        mediaPlayer.seek(duration);
    }

    public void constructExercice(){
        Texte texte = new Texte(occultCharVar, !caseSensitivVar);
        texte.setMode(lettersMotVar);
        texte.entrerTexteProf(textClair.getText());
        exo.setTexte(texte);
        exo.setConsigne(consigne.getText());
        exo.setTitre(title.getText());
        if (exo instanceof Entrainement) ((Entrainement) exo).setAide(aide.getText());
        if (timeLimitVar) ((Evaluation) exo).setDuree(timeLimitValueVar);
        exo.setTempReel(realTimeVar);
        exo.setShowSolution(showSolutionVar);
    }

    public double getPercentage(MediaPlayer mediaPlayer){
        Double duration = mediaPlayer.getMedia().getDuration().toSeconds();
        Double time = mediaPlayer.getCurrentTime().toSeconds();

        return time/duration;
    }

    public void openMedia() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un média");
        File file = fileChooser.showOpenDialog(MainTeacher.getStage());
        if(file!=null) {
            try {
                mediaPlayer = new MediaPlayer(new Media(file.toURI().toString()));
            } catch (Exception e) {
                System.err.println("Le fichier selectionné n'est pas un média ! Merci de choisir un fichier approprié.");
                openMedia();
                return;
            }
            exo.setMedia(new reconstitution.models.Media(file.toURI()));

            createMediaPlayer();
        }
    }

    public boolean checkFields(){
        boolean isItGood = true;
        for (Node child : anchorPane.getChildren()) {
            if (child instanceof TextArea || child instanceof TextField){
                if(((TextInputControl) child).getText().length()<2){
                    isItGood = false;
                    child.getStyleClass().add("empty-field");
                } else {
                    child.getStyleClass().removeAll("empty-field");
                }
            }
        }
        if(mediaPlayer==null) {
            isItGood = false;
            addMedia.setStrokeWidth(1);
            addMedia.setStroke(Color.RED);
        }
        return isItGood;
    }

    public void createMediaPlayer(){

        mediaPlayer.stop();
        player.getChildren().clear();
        anchorPane.getChildren().remove(uploadLogo);

        HBox hbox = new HBox();
        StackPane stackPane = new StackPane();
        ProgressBar mediaProgressBar = new ProgressBar(0);
        Label mediaTime = new Label(new SimpleDateFormat("H:mm:ss").format(new Date((long) mediaPlayer.getCurrentTime().toMillis()-3600000))+"/"+new SimpleDateFormat("H:mm:ss").format(new Date((long) mediaPlayer.getMedia().getDuration().toMillis()-3600000)));

        playPauseButton = new Button();

        playPauseButton.getStyleClass().addAll("playPause", "white");
        playPauseButton.setOnAction(actionEvent -> playPause());
        playPauseButton.setPrefWidth(25.0);

        muteButton = new Button();

        muteButton.getStyleClass().addAll("mute", "white");
        muteButton.setOnAction(actionEvent -> mute());
        muteButton.setPrefWidth(25.0);

        mediaProgressBar.setPrefHeight(26.0);
        mediaProgressBar.setOnMouseClicked(mouseEvent -> {
            setMediaCursor(mouseEvent.getX() / mediaProgressBar.getWidth());
        });
        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue)->{
            mediaProgressBar.setProgress(getPercentage(mediaPlayer));
            mediaTime.setText(new SimpleDateFormat("H:mm:ss").format(new Date((long) mediaPlayer.getCurrentTime().toMillis()-3600000))+"/"+new SimpleDateFormat("H:mm:ss").format(new Date((long) mediaPlayer.getMedia().getDuration().toMillis()-3600000)));
        });
        mediaTime.setOnMouseClicked(mouseEvent -> {
            double padding = (mediaProgressBar.getWidth()-mediaTime.getWidth())/2;
            setMediaCursor((mouseEvent.getX()+padding)/mediaProgressBar.getWidth());
        });

        MediaView mv = new MediaView();
        mv.setMediaPlayer(mediaPlayer);
        mv.setFitWidth(308.0);
        mv.setOnMouseClicked(mouseEvent -> playPause());

        mediaProgressBar.setPrefWidth(mv.getFitWidth() - playPauseButton.getWidth() - muteButton.getWidth() - 2);

        stackPane.setPrefWidth(mediaProgressBar.getPrefWidth());
        stackPane.getChildren().addAll(mediaProgressBar, mediaTime);

        hbox.getChildren().addAll(playPauseButton, stackPane, muteButton);
        player.getChildren().addAll(mv, hbox);
    }

    public void initOptions(){
        caseSensitivVar = false;
        timeLimitVar = false;
        showSolutionVar = false;
        realTimeVar = false;
        occultCharVar = '#';
        timeLimitValueVar = 0;
        lettersMotVar = 0;
    }

    public void createOptionStage(){
        menuOption = new Stage();
        Parent root = null;
        if(TeacherMenuController.isEvaluation()) {
            try {
                root = FXMLLoader.load(getClass().getResource("/view/teacherOptionMenuEvaluationView.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                root = FXMLLoader.load(getClass().getResource("/view/teacherOptionMenuView.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        menuOption.setTitle("Options");
        menuOption.setScene(new Scene(root));
        menuOption.getIcons().add(new Image(MainTeacher.class.getResourceAsStream("/images/option.png")));
        menuOption.setResizable(false);
    }

    public static Stage getMenuOption() {
        return menuOption;
    }

    public static void setCaseSensitivVar(boolean caseSensitivVar) {
        TeacherCreateController.caseSensitivVar = caseSensitivVar;
    }

    public static void setTimeLimitVar(boolean timeLimitVar) {
        TeacherCreateController.timeLimitVar = timeLimitVar;
    }

    public static void setShowSolutionVar(boolean showSolutionVar) {
        TeacherCreateController.showSolutionVar = showSolutionVar;
    }

    public static void setRealTimeVar(boolean realTimeVar) {
        TeacherCreateController.realTimeVar = realTimeVar;
    }

    public static void setOccultCharVar(char occultCharVar) {
        TeacherCreateController.occultCharVar = occultCharVar;
    }

    public static void setTimeLimitValueVar(int timeLimitValueVar) {
        TeacherCreateController.timeLimitValueVar = timeLimitValueVar;
    }

    public static void setLettersMotVar(int lettersMotVar) {
        TeacherCreateController.lettersMotVar = lettersMotVar;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
}
