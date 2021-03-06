package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.SceneManagement.SceneManager;
import sample.UnitTestManagement.UnitTestManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        UnitTestManager.UnitTest_runApplicationWithLoginPage(primaryStage,getClass());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
