package sample.Model.SceneManagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainScene extends GeneralScene {

    private Stage primaryStage;
    private Class mainclass;

    MainScene(Stage primaryStage, Class mainclass) {
        this.primaryStage = primaryStage;
        this.mainclass = mainclass;
    }

    public void run() throws Exception {
        //Load Scenes from FXML File and Bind to Stage
        FXMLLoader loader = new FXMLLoader(mainclass.getResource("/sample/sample.fxml")); //initialize loader using fxml file
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));

        //Bind Controller to Loader
        loader.getController();

        //Handle Stage Settings and Initiate the Interactive Application
        primaryStage.setTitle("DSIR");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
