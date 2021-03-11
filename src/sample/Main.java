package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.Model.SceneManagement.SceneManager;
import sample.Model.UtilityManagement.FileManager;

public class Main extends Application {

    @Override //Application Start through SceneManager
    public void start(Stage primaryStage){
        FileManager.setClass(getClass());
        SceneManager sceneManager = new SceneManager(primaryStage,getClass());
        sceneManager.start();
    }

    //Main Constructor
    public static void main(String[] args) {
        launch(args);
    }
}
