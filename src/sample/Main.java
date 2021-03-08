package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.SuperiorManagement.SceneManagement.SceneManager;

public class Main extends Application {

    @Override //Application Start through SceneManager
    public void start(Stage primaryStage){
        SceneManager sceneManager = new SceneManager(primaryStage,getClass());
        sceneManager.start();
    }

    //Main Constructor
    public static void main(String[] args) {
        launch(args);
    }
}
