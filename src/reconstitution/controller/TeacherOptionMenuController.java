package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherOptionMenuController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void validate(){
        TeacherCreateController.getMenuOption().close();
    }

    @FXML
    public void cancel(){
        TeacherCreateController.getMenuOption().close();
    }
}
