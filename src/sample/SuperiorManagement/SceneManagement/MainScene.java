package sample.SuperiorManagement.SceneManagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controller;
import sample.SuperiorManagement.StatsManagement.StatsManager;

public class MainScene extends GeneralScene{

    private Stage primaryStage;
    private Class mainclass;

    MainScene(Stage primaryStage,Class mainclass){
        this.primaryStage=primaryStage;
        this.mainclass=mainclass;
    }


    public void run() throws Exception{
        //access tabs via controller
        FXMLLoader loader = new FXMLLoader(mainclass.getResource("/sample/sample.fxml")); //initialize loader using fxml file
        Parent root = loader.load(); //prepare loader
        primaryStage.setScene(new Scene(root));

        Controller controller = loader.getController(); //bind controller to loader
        controller.setStatsManager(statsManager);
        controller.setCharacterBaseInfo(statsManager.getName(),statsManager.getClasstype());

        primaryStage.setTitle("DSIR");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
