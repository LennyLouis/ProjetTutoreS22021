package reconstitution;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import reconstitution.controller.StudentHomeController;
import reconstitution.controller.StudentMainController;

import java.io.IOException;

public class MainStudent extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        ObservableList<Screen> screenSizes = Screen.getScreens();
        screenSizes.forEach(screen -> {
            System.out.println(screen.getBounds());
        });
        setView("/view/studentHomeView.fxml");
    }

    @Override
    public void stop(){
        // Stop le timer de la partie étudiant
        StudentMainController.timer.cancel();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void setView(String path) throws IOException {
        Parent parent = FXMLLoader.load(MainStudent.class.getResource(path));
        stage.setTitle("Reconstitution (étudiant)");
        stage.getIcons().add(new Image(MainStudent.class.getResourceAsStream("/images/icon_reconstitution.png")));
        Scene scene = new Scene(parent, 720, 480);
        scene.getStylesheets().add(String.valueOf(MainStudent.class.getResource("/style.css")));
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }
}
