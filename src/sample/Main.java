package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.SuperiorManagement.SceneManagement.LoginScene;
import sample.SuperiorManagement.SceneManagement.SceneManager;
import sample.SuperiorManagement.UnitTestManagement.UnitTestManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        //UnitTestManager.UnitTest_runApplicationWithLoginPage(primaryStage,getClass());
        SceneManager sceneManager = new SceneManager();
        sceneManager.getLoginScene().run(primaryStage,getClass());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
