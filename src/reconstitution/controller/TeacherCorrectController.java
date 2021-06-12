package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import reconstitution.MainTeacher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherCorrectController implements Initializable {


    @FXML
    public void homeButton() throws IOException {
        MainTeacher.setView("/view/teacherMenuView.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
