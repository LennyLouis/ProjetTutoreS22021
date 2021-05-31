package reconstitution.etudiant;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ObservableList<Screen> screenSizes = Screen.getScreens();
        screenSizes.forEach(screen -> {
            System.out.println(screen.getBounds());
        });

        Parent home = FXMLLoader.load(getClass().getResource("./view/homeView.fxml"));
        Parent main = FXMLLoader.load(getClass().getResource("./view/mainView.fxml"));
        primaryStage.setTitle("Reconstitution (étudiant)");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../../reconstitution.assets/icon_reconstitution.png")));
        primaryStage.setScene(new Scene(home, 720, 480));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
