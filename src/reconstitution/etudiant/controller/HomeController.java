package reconstitution.etudiant.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    AnchorPane anchorPane;

    @FXML
    Button launchButton;

    @FXML
    public void launchApp() throws IOException {
        //FXMLLoader main = FXMLLoader.load(getClass().getResource("../view/homeView.fxml"));
        //TODO: faire le changement de scene au clique
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
