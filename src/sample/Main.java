package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.Model.StageManagement.StageManager;

public class Main extends Application {
    private static Stage primaryStage;

    @Override //Application Start through StageManager
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;

        StageManager stageManager = new StageManager();
        stageManager.start();
    }

    //Main Constructor
    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
