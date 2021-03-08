package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.SuperiorManagement.SceneManagement.LoginScene;
import sample.SuperiorManagement.SceneManagement.SceneManager;
import sample.SuperiorManagement.UnitTestManagement.UnitTestManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        SceneManager sceneManager = new SceneManager(primaryStage,getClass());
        sceneManager.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
