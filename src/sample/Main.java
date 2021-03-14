package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.Model.SceneManagement.SceneManager;

public class Main extends Application {
    private static Stage primaryStage;

    @Override //Application Start through SceneManager
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;

        SceneManager sceneManager = new SceneManager();
        sceneManager.start();
    }

    //Main Constructor
    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
