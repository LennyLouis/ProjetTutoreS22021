package reconstitution;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MainTeacher extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        ObservableList<Screen> screenSizes = Screen.getScreens();
        screenSizes.forEach(screen -> {
            System.out.println(screen.getBounds());
        });

        setView("/view/teacherHomeView.fxml");
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void setView(String path) throws IOException {
        Parent parent = FXMLLoader.load(MainStudent.class.getResource(path));
        stage.setTitle("Reconstitution (étudiant)");
        stage.getIcons().add(new Image(MainStudent.class.getResourceAsStream("/images/icon_reconstitution.png")));
        stage.setScene(new Scene(parent, 720, 480));
        stage.show();
    }
}
