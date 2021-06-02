package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import reconstitution.MainStudent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentHomeController implements Initializable {

    @FXML
    AnchorPane anchorPane;

    @FXML
    Button launchButton;

    @FXML
    public void launchApp() throws IOException {
        MainStudent.changeView("view/sudentMainView.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
