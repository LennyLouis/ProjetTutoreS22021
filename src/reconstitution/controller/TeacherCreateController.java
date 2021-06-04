package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import reconstitution.MainTeacher;

import java.io.IOException;
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

    @FXML
    public void homeButton() throws IOException {
        MainTeacher.setView("/view/teacherMenuView.fxml");
    }

    @FXML
    public void openOpt() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/teacherOptionMenuView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 450, 150));
        stage.show();
    }

    public void openMedia(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un média");
        fileChooser.showOpenDialog(MainTeacher.getStage());
    }
}
