package sample.SuperiorManagement.SceneManagement;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Controller;

public class LoginScene{

    private Stage primaryStage;
    private Class mainclass;

    LoginScene(Stage primaryStage,Class mainclass){
        this.primaryStage=primaryStage;
        this.mainclass=mainclass;
    }

    public void run(){
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
                initDefaultStageLoaderParamaters(name.getText(),occupation.getText());
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
    private void initDefaultStageLoaderParamaters(String name, String occupation) throws Exception{
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
