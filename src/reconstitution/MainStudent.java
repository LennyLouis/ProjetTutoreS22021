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

import java.io.IOException;

public class MainStudent extends Application {

    private static Stage stage;
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        ObservableList<Screen> screenSizes = Screen.getScreens();
        screenSizes.forEach(screen -> {
            System.out.println(screen.getBounds());
        });
        setView("/view/studentHomeView.fxml");
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void setView(String path) throws IOException {
        Parent parent = FXMLLoader.load(MainStudent.class.getResource(path));
        stage.setTitle("Reconstitution (étudiant)");
        stage.getIcons().add(new Image(MainStudent.class.getResourceAsStream("/images/icon_reconstitution.png")));
        scene = new Scene(parent, 720, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    public static Scene getScene() {
        return scene;
    }
}
