package sample.SceneManagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.Controller;

import java.util.List;

public class SceneManager {

    public static void run(Stage primaryStage, Class mainclass) throws Exception{
        //access tabs via controller
        FXMLLoader loader = new FXMLLoader(mainclass.getResource("/sample/sample.fxml")); //initialize loader using fxml file
        Parent root = loader.load(); //prepare loader
        primaryStage.setScene(new Scene(root));
        Controller controller = loader.getController(); //bind controller to loader
        controller.updateCharacterInfo("Johnny","Warrior",100,1000);

        //retrieve contents
        List<Tab> gametabs = controller.getGameTabs();
        List<Label> townbuildings = controller.getTownBuildings();
        Label dojobuilding = controller.getTownBuilding("dojo");
        System.out.println("Town: "+townbuildings);
        System.out.println("Dojo: "+dojobuilding);

        primaryStage.setTitle("DSIR");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void runFXML(Stage primaryStage, Class mainclass) throws Exception{
        //access tabs via frame components
        TabPane tabpane = FXMLLoader.load(mainclass.getResource("/sample/sample.fxml")); //get tabpane from the fxml file
        primaryStage.setTitle("DSIR");

        Scene scene = new Scene(tabpane); //put tabpane into the scene
        primaryStage.setScene(scene);

        System.out.println("--List of Tabs Frame Component--");
        List<Tab> tabList = tabpane.getTabs(); //get tabs from tabpane
        for (Tab tab:tabList) {
            System.out.println(tab.getText());  //print tabs
        }

        System.out.println("-------------------------------------");

        //access tabs via controller
        FXMLLoader loader = new FXMLLoader(mainclass.getResource("/sample/sample.fxml")); //initialize loader using fxml file
        loader.load(); //prepare loader
        Controller controller = loader.getController(); //bind controller to loader

        for (Tab gametab:controller.getGameTabs()) { //execute function from controller via accessing controller injected elements, show the
            System.out.println(gametab.getText());
        }

        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void testNonFXML(Stage primaryStage){
        primaryStage.setTitle("DSIR");

        //First group element
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        Button button3 = new Button("Button 3");
        Button button4 = new Button("Button 4");

        HBox row1 = new HBox(button1,button2); //HBOX = Horizontal Box = row or group of items in a row --> row1 = btn1 + btn2
        HBox row2 = new HBox(button3,button4);
        row1.setSpacing(5);
        row2.setSpacing(5);

        VBox rows = new VBox(row1,row2); //VBOX = Vertical Box = group of rows --> row = row1 + row2

        //Second group element
        Label snakes = new Label("This is a label");

        //Group of elements, location required to avoid overlap
        Group group = new Group();
        group.getChildren().add(rows);
        group.getChildren().add(snakes);

        //Scene and Stage
        Scene scene = new Scene(group, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
