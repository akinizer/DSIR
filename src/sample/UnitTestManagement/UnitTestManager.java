package sample.UnitTestManagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Controller;

import java.util.ArrayList;
import java.util.List;

public class UnitTestManager {

    public static void UnitTest_getTownBuildings(Stage primaryStage, Class mainclass) throws Exception{
        //access tabs via controller
        FXMLLoader loader = new FXMLLoader(mainclass.getResource("/sample/sample.fxml")); //initialize loader using fxml file
        Parent root = loader.load(); //prepare loader
        primaryStage.setScene(new Scene(root));
        Controller controller = loader.getController(); //bind controller to loader
        controller.updateCharacterInfo("Johnny","Warrior",100,1000);

        //retrieve contents
        List<Label> townbuildings = controller.getTownBuildings();
        Label dojobuilding = controller.getTownBuilding("dojo");
        System.out.println("Town: "+townbuildings);
        System.out.println("Dojo: "+dojobuilding);

        primaryStage.setTitle("DSIR");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void UnitTest_getDojoBuilding(Stage primaryStage, Class mainclass) throws Exception{
        //access tabs via controller
        FXMLLoader loader = new FXMLLoader(mainclass.getResource("/sample/sample.fxml")); //initialize loader using fxml file
        Parent root = loader.load(); //prepare loader
        primaryStage.setScene(new Scene(root));
        Controller controller = loader.getController(); //bind controller to loader
        controller.updateCharacterInfo("Johnny","Warrior",100,1000);

        //retrieve contents
        Label dojobuilding = controller.getTownBuilding("dojo");
        System.out.println("Dojo: "+dojobuilding);

        primaryStage.setTitle("DSIR");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void UnitTest_getGameTabContents(Stage primaryStage, Class mainclass) throws Exception{
        //access tabs via controller
        FXMLLoader loader = new FXMLLoader(mainclass.getResource("/sample/sample.fxml")); //initialize loader using fxml file
        Parent root = loader.load(); //prepare loader
        primaryStage.setScene(new Scene(root));
        Controller controller = loader.getController(); //bind controller to loader
        controller.updateCharacterInfo("Johnny","Warrior",100,1000);

        //retrieve contents
        for (AnchorPane node:controller.getGameTabContents()) {
            System.out.println(node.getId() + ": " + node.getChildren());
        }

        primaryStage.setTitle("DSIR");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
