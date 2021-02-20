package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.SceneManagement.SceneManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        SceneManager.run(primaryStage, getClass());
    }



    public static void main(String[] args) {
        launch(args);
    }
}
