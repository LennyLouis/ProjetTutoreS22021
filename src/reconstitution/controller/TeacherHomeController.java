package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import reconstitution.MainTeacher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherHomeController implements Initializable {

    private static Stage param;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void launchApp() throws IOException {
        MainTeacher.setView("/view/teacherHomeMenuView.fxml");
    }

    @FXML
    public void openParam() throws IOException {
        param.show();
    }
}
