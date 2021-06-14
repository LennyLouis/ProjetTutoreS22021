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
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class TeacherCreateController implements Initializable {

    // Media player
    private static boolean playPauseSwitch = false;
    private static boolean muteSwitch = false;

    private static Stage menuOption;

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
    TextArea consigne, aide, textClair, title;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createOptionStage();
        initOptions();

        if(!TeacherMenuController.isEvaluation()){
            exo = new Entrainement();
            anchorPane.getChildren().removeAll(evaluationTime);
            //ici tu retire tout les elements qui n'ont pas lieu d'être dans un entrainement
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
            //fileChooser.setSelectedExtensionFilter(); //TODO: extension de fichier dans le FileChooser
            File file = fileChooser.showSaveDialog(MainTeacher.getStage());
            if (file != null) {
                constructExercice();
                Exercice.sauvegarder(exo, file.getAbsolutePath());
                showDialog(file);
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
                exo = (Exercice) Exercice.ouvrir(file.getAbsolutePath());
            } catch (Exception e){
                e.printStackTrace();
                return;
            }
            //TODO: Faire tout les setters sur les options et les champs de la vue
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
        Texte texte = new Texte(occultCharVar, false);
        texte.setMode(lettersMotVar);
        texte.setOccultChar(occultCharVar);
        texte.setSensiCasse(caseSensitivVar);
        texte.entrerTexteProf(textClair.getText());
        exo.setTexte(texte);
        exo.setConsigne(consigne.getText());
        exo.setTitre(title.getText());
        if (timeLimitVar) ((Evaluation) exo).setDuree(timeLimitValueVar);
        exo.setAide(aide.getText());
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
        //fileChooser.setSelectedExtensionFilter(); //TODO: extension de fichier dans le FileChooser
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
            player.getChildren().remove(addMedia);
            anchorPane.getChildren().remove(uploadLogo);

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
                    System.out.println(child.getId()+ " pas rempli");
                } else {
                    child.getStyleClass().removeAll("empty-field");
                    System.out.println(child.getId()+ " rempli");
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

    public void showDialog(File createdFile){
        Stage saveSuccess = new Stage();
        AnchorPane ap = new AnchorPane();
        Label label = new Label("Le sauvegarde a été réalisé avec succès.");
        Button openFolder = new Button("Ouvrir le dossier");
        Button ok = new Button("OK");
        ok.setOnAction(e -> {
            saveSuccess.close();
        });
        openFolder.setOnAction(e -> {
            try {
                Desktop.getDesktop().open(createdFile.getParentFile());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        ap.getChildren().addAll(label, openFolder, ok);
        saveSuccess.setTitle("Sauvegarde réussie !");
        saveSuccess.setScene(new Scene(ap, 400, 100));
        saveSuccess.setResizable(false);
        saveSuccess.show();
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

    public void immport() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importer un exercice");
        File file = fileChooser.showOpenDialog(MainTeacher.getStage());
        if(file!=null) {
            try {
                exo = (Exercice) Exercice.ouvrir(file.getAbsolutePath());
            } catch (Exception e){
                e.printStackTrace();
                return;
            }
        }
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
}
