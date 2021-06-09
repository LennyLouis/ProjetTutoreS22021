package reconstitution.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import reconstitution.MainTeacher;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherCreateController implements Initializable {

    private static boolean playPauseSwitch = false;
    private static boolean muteSwitch = false;

    private static Stage menuOption;

    private Button playPauseButton;
    private Button muteButton;
    private MediaPlayer mediaPlayer;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createOptionStage();

        addMedia.setOnMouseClicked(mouseEvent -> openMedia());
        if(!TeacherMenuController.isEvaluation()){
            anchorPane.getChildren().removeAll(evaluationTime);
        }
        uploadLogo.setOnMouseClicked(mouseEvent -> openMedia());
        if(!TeacherMenuController.isEvaluation()){
            anchorPane.getChildren().removeAll(evaluationTime);
        }
    }

    @FXML
    public void homeButton() throws IOException {
        MainTeacher.setView("/view/teacherMenuView.fxml");
    }

    @FXML
    public void openOpt() throws IOException {
        if(menuOption.isShowing()){
            menuOption.close();
        }
        menuOption.show();
    }

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

    public double getPercentage(MediaPlayer mediaPlayer){
        Double duration = mediaPlayer.getMedia().getDuration().toSeconds();
        Double time = mediaPlayer.getCurrentTime().toSeconds();

        return time/duration;
    }

    public void openMedia(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un média");
        //fileChooser.setSelectedExtensionFilter(); //TODO: extension de fichier dans le FileChooser
        File file = fileChooser.showOpenDialog(MainTeacher.getStage());

        player.getChildren().remove(addMedia);
        anchorPane.getChildren().remove(uploadLogo);

        HBox hbox = new HBox();

        ProgressBar mediaProgressBar = new ProgressBar();

        playPauseButton = new Button();

        playPauseButton.getStyleClass().addAll("playPause","white");
        playPauseButton.setOnAction(actionEvent -> playPause());
        playPauseButton.setPrefWidth(25.0);

        muteButton = new Button();

        muteButton.getStyleClass().addAll("mute","white");
        muteButton.setOnAction(actionEvent -> mute());
        muteButton.setPrefWidth(25.0);

        mediaProgressBar.setPrefHeight(26.0);
        mediaProgressBar.setOnMouseClicked(mouseEvent -> {
            setMediaCursor(mouseEvent.getX()/mediaProgressBar.getWidth());
        });

        mediaPlayer = new MediaPlayer(new Media(file.toURI().toString()));
        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue)->{
            mediaProgressBar.setProgress(getPercentage(mediaPlayer));
        });

        MediaView mv = new MediaView();
        mv.setMediaPlayer(mediaPlayer);
        mv.setFitWidth(308.0);
        mv.setOnMouseClicked(mouseEvent -> playPause());

        mediaProgressBar.setPrefWidth(mv.getFitWidth()-playPauseButton.getWidth()-muteButton.getWidth()-2);

        hbox.getChildren().addAll(playPauseButton, mediaProgressBar, muteButton);
        player.getChildren().addAll(mv, hbox);

    }

    public void createOptionStage(){
        menuOption = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/teacherOptionMenuView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        menuOption.setTitle("Options");
        menuOption.setScene(new Scene(root, 441, 161));
        menuOption.setResizable(false);
    }

    public static Stage getMenuOption() {
        return menuOption;
    }
}
