package sample.Model.StageManagement;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;

import java.io.IOException;

public class FleetStage extends Stage {
    public FleetStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/sample/fxmls/fleetpanel.fxml")); //initialize loader using fxml file
        Pane root = loader.load();

        ObservableList<Node> children=root.getChildren();
        for (Node child:children) {
            Label label=(Label)child;

            if(label.getText().equals("< >")){
                label.setText("> <");
                break;
            }
        }
        setScene(new Scene(root));
        setResizable(false);
        setTitle("Fleet Window");
        initStyle(StageStyle.TRANSPARENT);
        show();
    }
}
