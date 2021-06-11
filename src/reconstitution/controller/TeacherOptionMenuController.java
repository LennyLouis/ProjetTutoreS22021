package reconstitution.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static reconstitution.controller.TeacherCreateController.*;

public class TeacherOptionMenuController implements Initializable {

    @FXML
    CheckBox caseSensitiv, timeLimit, showSolution, realTime;

    @FXML
    TextField timeLimitValue;

    @FXML
    ComboBox occultChar;

    @FXML
    RadioButton disableMot, threeLettersMot, twoLettersMot;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void validateEval(){
        setCaseSensitivVar(caseSensitiv.isSelected());
        setTimeLimitVar(timeLimit.isSelected());
        setShowSolutionVar(showSolution.isSelected());
        setRealTimeVar(realTime.isSelected());
        setOccultCharVar(occultChar.getValue().toString().charAt(0));
        setTimeLimitValueVar(Integer.parseInt(timeLimitValue.getText()));
        if(disableMot.isSelected()) setLettersMotVar(1);
        if(twoLettersMot.isSelected()) setLettersMotVar(2);
        if(threeLettersMot.isSelected()) setLettersMotVar(3);
        getMenuOption().close();
    }

    @FXML
    public void validateEntr(){
        setCaseSensitivVar(caseSensitiv.isSelected());
        setTimeLimitVar(timeLimit.isSelected());
        setOccultCharVar(occultChar.getValue().toString().charAt(0));
        setTimeLimitValueVar(Integer.parseInt(timeLimitValue.getText()));
        getMenuOption().close();
    }

    @FXML
    public void cancel(){
        getMenuOption().close();
    }
}
