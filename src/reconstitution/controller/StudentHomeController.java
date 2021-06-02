package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import reconstitution.MainStudent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentHomeController implements Initializable {

    private static Scene scene;
    @FXML
    Rectangle blackPane;
    @FXML
    ImageView img;

    @FXML
    public void launchApp() throws IOException {
        MainStudent.setView("/view/studentMainView.fxml");
    }

    @FXML
    public void test(){
        scene = MainStudent.getStage().getScene();
        MainStudent.getStage().widthProperty().addListener((obs, oldVal, newVal) -> {
            Double w = scene.getWidth();
            Double h = scene.getHeight();
            blackPane.setWidth(percent(30,w));
            AnchorPane.setLeftAnchor(img, percent(30, w)-124);
        });

        MainStudent.getStage().heightProperty().addListener((obs, oldVal, newVal) -> {
            Double w = scene.getWidth();
            Double h = scene.getHeight();
            blackPane.setHeight(h);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public double percent(double per, double total){
        return total*(per/100);
    }
}
