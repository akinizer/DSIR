package sample.Model.SceneManagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controller;
import sample.Model.StatsManagement.StatsManager;

public class MainScene extends GeneralScene {

    private Stage primaryStage;
    private Class mainclass;

    MainScene(Stage primaryStage, Class mainclass) {
        this.primaryStage = primaryStage;
        this.mainclass = mainclass;
    }

    public void run() throws Exception {
        //access tabs via controller
        FXMLLoader loader = new FXMLLoader(mainclass.getResource("/sample/sample.fxml")); //initialize loader using fxml file
        Parent root = loader.load(); //prepare loader
        primaryStage.setScene(new Scene(root));

        loader.getController(); //bind controller to loader

        primaryStage.setTitle("DSIR");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
