package sample;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Model.StageManagement.StageManager;
import sample.Model.UtilityManagement.MediaManager;

import java.io.File;
import java.lang.module.ModuleDescriptor;
import java.nio.file.Path;
import java.nio.file.Paths;

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
