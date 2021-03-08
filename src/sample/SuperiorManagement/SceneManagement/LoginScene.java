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

public class LoginScene extends GeneralScene{

    //Attributes
    private Stage loginStage;
    private boolean isClosedFlag;

    LoginScene(){
        //Stage Implementation
        loginStage=new Stage();
    }

    public void run(){
        //Container
        VBox box = new VBox();
        box.setPrefSize(250,250);

        //Name Entry
        TextField name = new TextField("Enter Name");
        name.setPrefSize(125,28);
        name.setOnMouseClicked(mouseEvent -> {
            if(name.getText().isEmpty()||name.getText().isEmpty())
                name.setText("Enter Name");
            else
                name.selectAll();
        });

        //Occupation Entry
        TextField occupation = new TextField("Enter Occupation");
        occupation.setPrefSize(256,28);
        occupation.setOnMouseClicked(mouseEvent -> {
            if(occupation.getText().isEmpty()||occupation.getText().isEmpty())
                occupation.setText("Enter Occupation");
            else
                occupation.selectAll();
        });

        //OK Button
        Button button = new Button("OK");
        button.setOnAction(event -> {
            try {
                //initDefaultStageLoaderParamaters(name.getText(),occupation.getText());
                statsManager.setName(name.getText());
                statsManager.setClasstype(occupation.getText());
                isClosedFlag=true;

                Timeline timer = new Timeline(new KeyFrame(Duration.millis(2),eventCloser->{
                    loginStage.close();
                }));
                timer.setCycleCount(Animation.INDEFINITE);
                timer.play();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        box.getChildren().addAll(name,occupation,button);

        //Stage Settings
        loginStage.setScene(new Scene(box));
        loginStage.setResizable(false);
        loginStage.show();
        isClosedFlag=false;

        //Timeout
        Timeline timer = new Timeline(new KeyFrame(Duration.minutes(5), event -> {
            loginStage.close();
        }));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }

    //Check Closed Status
    boolean isClosed(){
        return isClosedFlag;
    }
}
