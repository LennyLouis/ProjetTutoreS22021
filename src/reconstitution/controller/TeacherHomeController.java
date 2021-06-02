package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import reconstitution.MainTeacher;

import java.io.IOException;

public class TeacherHomeController {

    @FXML
    public void launchApp() throws IOException {
        MainTeacher.setView("/view/teacherMainView.fxml");
    }

}
