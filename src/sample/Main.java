package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.SuperiorManagement.SceneManagement.SceneManager;
import sample.SuperiorManagement.UtilityManagement.FileManager;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main extends Application {

    @Override //Application Start through SceneManager
    public void start(Stage primaryStage) throws Exception {


        FileManager.setClass(getClass());
        SceneManager sceneManager = new SceneManager(primaryStage,getClass());
        sceneManager.start();


    }

    //Main Constructor
    public static void main(String[] args) {
        launch(args);
    }
}
