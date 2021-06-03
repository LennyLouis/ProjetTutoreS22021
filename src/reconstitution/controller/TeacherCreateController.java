package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import reconstitution.MainTeacher;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherCreateController implements Initializable {

    @FXML
    AnchorPane anchorPane;

    @FXML
    Label evaluationTime;

    @FXML
    Rectangle addMedia;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addMedia.setOnMouseClicked(mouseEvent -> openMedia());
        if(!TeacherMenuController.isEvaluation()){
            anchorPane.getChildren().removeAll(evaluationTime);
        }
    }

    public void openMedia(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un média");
        fileChooser.showOpenDialog(MainTeacher.getStage());
    }
}
