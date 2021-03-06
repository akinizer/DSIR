package sample.UnitTestManagement;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
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

    public static void UnitTest_runApplicationWithDefaultCharacterPresets(Stage primaryStage, Class mainclass) throws Exception{
        //access tabs via controller
        FXMLLoader loader = new FXMLLoader(mainclass.getResource("/sample/sample.fxml")); //initialize loader using fxml file
        Parent root = loader.load(); //prepare loader
        primaryStage.setScene(new Scene(root));
        Controller controller = loader.getController(); //bind controller to loader
        controller.updateCharacterInfo("Johnny","Warrior",100,1000);

        primaryStage.setTitle("DSIR");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void UnitTest_runApplicationWithLoginPage(Stage primaryStage, Class mainclass) {
        Stage loginStage=new Stage();

        VBox box = new VBox();
        box.setPrefSize(250,250);

        TextField name = new TextField("Enter Name");
        name.setPrefSize(125,28);
        name.setOnMouseClicked(mouseEvent -> {
            if(name.getText().isEmpty()||name.getText().isEmpty())
                name.setText("Enter Name");
            else
                name.selectAll();
        });

        TextField occupation = new TextField("Enter Occupation");
        occupation.setPrefSize(256,28);
        occupation.setOnMouseClicked(mouseEvent -> {
            if(occupation.getText().isEmpty()||occupation.getText().isEmpty())
                occupation.setText("Enter Occupation");
            else
                occupation.selectAll();
        });

        Button button = new Button("OK");
        button.setOnAction(event -> {
            try {
                initDefaultStageLoaderParamaters(primaryStage, mainclass,name.getText(),occupation.getText());
                loginStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        box.getChildren().addAll(name,occupation,button);

        loginStage.setScene(new Scene(box));
        loginStage.setResizable(false);
        loginStage.show();


        //Timeout
        Timeline timer = new Timeline(new KeyFrame(Duration.minutes(5), event -> {
            loginStage.close();
        }));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();



    }
    public static void UnitTest_runApplicationWithAlertPage(Stage primaryStage, Class mainclass) throws Exception{


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Start DSIR?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            initDefaultStageLoader(primaryStage, mainclass);
        }
/*      Stage secondaryStage=new Stage();
        secondaryStage.setScene(new Scene(new HBox(4,new Label("Second Window"))));
        secondaryStage.show();
        */

    }


    private static void initDefaultStageLoader(Stage primaryStage, Class mainclass) throws Exception{
        //access tabs via controller
        FXMLLoader loader = new FXMLLoader(mainclass.getResource("/sample/sample.fxml")); //initialize loader using fxml file
        Parent root = loader.load(); //prepare loader
        primaryStage.setScene(new Scene(root));
        Controller controller = loader.getController(); //bind controller to loader
        controller.updateCharacterInfo("Johnny","Warrior",100,1000);

        primaryStage.setTitle("DSIR");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    private static void initDefaultStageLoaderParamaters(Stage primaryStage, Class mainclass, String name, String occupation) throws Exception{
        //access tabs via controller
        FXMLLoader loader = new FXMLLoader(mainclass.getResource("/sample/sample.fxml")); //initialize loader using fxml file
        Parent root = loader.load(); //prepare loader
        primaryStage.setScene(new Scene(root));
        Controller controller = loader.getController(); //bind controller to loader
        controller.updateCharacterInfo(name,occupation,100,1000);

        primaryStage.setTitle("DSIR");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
