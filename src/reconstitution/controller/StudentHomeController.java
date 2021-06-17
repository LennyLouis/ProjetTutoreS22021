package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import reconstitution.MainStudent;
import reconstitution.MainTeacher;
import reconstitution.models.Exercice;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentHomeController implements Initializable {

    private static Scene scene;
    public static Exercice exo;
    private static Stage param;

    @FXML
    Rectangle blackPane;
    @FXML
    ImageView img;

    @FXML
    public void openParam() throws IOException {
        param.show();
    }

    @FXML
    public void launchApp() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un exercice");
        File file = fileChooser.showOpenDialog(MainTeacher.getStage());
        if(file!=null) {
            try {
                exo = (Exercice) Exercice.ouvrir(file.getAbsolutePath());
            } catch (Exception e){
                e.printStackTrace();
                return;
            }
            MainStudent.setView("/view/studentMainView.fxml","Reconstitution (étudiant) - " + exo.getTitre());
        }
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
