package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import reconstitution.MainTeacher;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherCreateController implements Initializable {

    @FXML
    Rectangle addMedia;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addMedia.setOnMouseClicked(mouseEvent -> openMedia());
    }

    public void openMedia(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un média");
        fileChooser.showOpenDialog(MainTeacher.getStage());
    }
}
