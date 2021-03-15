package sample.Model.UnitTestManagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Controller;
import sample.Main;

import java.util.List;

public abstract class UnitTestManager {

    //Unit Test1
    public static void UnitTest_getTownBuildings() throws Exception {
        Stage primaryStage=Main.getPrimaryStage();
        //access tabs via controller
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmls/sample.fxml")); //initialize loader using fxml file
        Parent root = loader.load(); //prepare loader
        primaryStage.setScene(new Scene(root));
        Controller controller = loader.getController(); //bind controller to loader
        controller.updateCharacterInfo("Johnny", "Warrior", 100, 1000);

        //retrieve contents
        List<Label> townbuildings = controller.getTownBuildings();
        Label dojobuilding = controller.getTownBuilding("dojo");
        System.out.println("Town: " + townbuildings);
        System.out.println("Dojo: " + dojobuilding);

        primaryStage.setTitle("DSIR");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //Unit Test2
    public static void UnitTest_getDojoBuilding() throws Exception {
        Stage primaryStage=Main.getPrimaryStage();
        //access tabs via controller
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmls/sample.fxml")); //initialize loader using fxml file
        Parent root = loader.load(); //prepare loader
        primaryStage.setScene(new Scene(root));
        Controller controller = loader.getController(); //bind controller to loader
        controller.updateCharacterInfo("Johnny", "Warrior", 100, 1000);

        //retrieve contents
        Label dojobuilding = controller.getTownBuilding("dojo");
        System.out.println("Dojo: " + dojobuilding);

        primaryStage.setTitle("DSIR");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //Unit Test3
    public static void UnitTest_getGameTabContents() throws Exception {
        Stage primaryStage=Main.getPrimaryStage();
        //access tabs via controller
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmls/sample.fxml")); //initialize loader using fxml file
        Parent root = loader.load(); //prepare loader
        primaryStage.setScene(new Scene(root));
        Controller controller = loader.getController(); //bind controller to loader
        controller.updateCharacterInfo("Johnny", "Warrior", 100, 1000);

        //retrieve contents
        for (AnchorPane node : controller.getGameTabContents()) {
            System.out.println(node.getId() + ": " + node.getChildren());
        }

        primaryStage.setTitle("DSIR");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //Unit Test4
    public static void UnitTest_runApplicationWithDefaultCharacterPresets() throws Exception {
        Stage primaryStage=Main.getPrimaryStage();
        //access tabs via controller
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmls/sample.fxml")); //initialize loader using fxml file
        Parent root = loader.load(); //prepare loader
        primaryStage.setScene(new Scene(root));
        Controller controller = loader.getController(); //bind controller to loader
        controller.updateCharacterInfo("Johnny", "Warrior", 100, 1000);

        primaryStage.setTitle("DSIR");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //Unit Test5
    public static void UnitTest_runApplicationWithAlertPage() throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Start DSIR?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            initDefaultStageLoader();
        }

    }

    ///stage Loaders
    private static void initDefaultStageLoader() throws Exception {
        Stage primaryStage=Main.getPrimaryStage();
        //access tabs via controller
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmls/sample.fxml")); //initialize loader using fxml file
        Parent root = loader.load(); //prepare loader
        primaryStage.setScene(new Scene(root));
        Controller controller = loader.getController(); //bind controller to loader
        controller.updateCharacterInfo("Johnny", "Warrior", 100, 1000);

        primaryStage.setTitle("DSIR");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private static void initDefaultStageLoaderParamaters(String name, String occupation) throws Exception {
        Stage primaryStage=Main.getPrimaryStage();
        //access tabs via controller
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmls/sample.fxml")); //initialize loader using fxml file
        Parent root = loader.load(); //prepare loader
        primaryStage.setScene(new Scene(root));
        Controller controller = loader.getController(); //bind controller to loader
        controller.updateCharacterInfo(name, occupation, 100, 1000);

        primaryStage.setTitle("DSIR");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
