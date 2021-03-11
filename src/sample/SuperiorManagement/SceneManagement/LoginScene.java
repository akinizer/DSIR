package sample.SuperiorManagement.SceneManagement;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.SuperiorManagement.StatsManagement.StatsManager;
import sample.SuperiorManagement.UtilityManagement.FileManager;
import sample.SuperiorManagement.UtilityManagement.UtilityManager;

public class LoginScene extends GeneralScene {

    //Attributes
    private Stage loginStage;
    private boolean isClosedFlag;

    LoginScene() {
        //Stage Implementation
        loginStage = new Stage();
    }

    public void run() {
        //Container
        VBox box = new VBox();
        box.setPrefSize(250, 80);
        box.setAlignment(Pos.TOP_CENTER);

        //Name Entry
        String defaultstringName = "Enter Name";
        TextField name = new TextField(defaultstringName);
        name.setPrefSize(125, 28);
        name.setOnMouseClicked(mouseEvent -> {
            if (name.getText().isEmpty() || name.getText().isEmpty())
                name.setText(defaultstringName);
            else
                name.selectAll();
        });

        //Occupation Entry
        String defaultstringOccupation = "Enter Occupation";
        TextField occupation = new TextField(defaultstringOccupation);
        occupation.setPrefSize(256, 28);
        occupation.setOnMouseClicked(mouseEvent -> {
            if (occupation.getText().isEmpty() || occupation.getText().isEmpty())
                occupation.setText(defaultstringOccupation);
            else
                occupation.selectAll();
        });

        //Login Button
        Button loginbutton = new Button("Login");
        loginbutton.setPrefSize(box.getPrefWidth()/2,box.getPrefWidth() - name.getPrefWidth() - occupation.getPrefWidth());
        loginbutton.setOnAction(event -> {
            if (name.getText().equals(defaultstringName) || occupation.getText().equals(defaultstringOccupation))
                return;

            //Login validation condition
            boolean isValid=FileManager.checkLoginValid(name.getText(),occupation.getText());

            try {
                //check name and occupation matches and apply login action
                if (isValid) {
                    System.out.println("Logged in");

                    StatsManager.setName(name.getText());
                    StatsManager.setClasstype(occupation.getText());
                    isClosedFlag = true;

                    Timeline timer = new Timeline(new KeyFrame(Duration.millis(2), eventCloser -> loginStage.close()));
                    timer.setCycleCount(Animation.INDEFINITE);
                    timer.play();
                }
                else System.out.println("Username or Occupation do not match");

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //Login Button
        Button registerButton = new Button("Register");
        registerButton.setPrefSize(box.getPrefWidth()/2,box.getPrefWidth() - name.getPrefWidth() - occupation.getPrefWidth());
        registerButton.setOnAction(event -> {
            if (name.getText().equals(defaultstringName) || occupation.getText().equals(defaultstringOccupation))
                return;

            //Register validation condition
            boolean isNewUser=!FileManager.checkUserExists(name.getText());

            try {
                //check name matches and apply register action
                if(isNewUser) {
                    System.out.println("New user: "+name.getText() + "-"+occupation.getText());

                    StatsManager.setName(name.getText());
                    StatsManager.setClasstype(occupation.getText());
                    isClosedFlag = true;
                    FileManager.writeFileTest(name.getText() + "," + occupation.getText());

                    Timeline timer = new Timeline(new KeyFrame(Duration.millis(2), eventCloser -> loginStage.close()));
                    timer.setCycleCount(Animation.INDEFINITE);
                    timer.play();
                }
                else System.out.println("User already exists");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //Add Components to Containers
        HBox entryBox = new HBox(loginbutton, registerButton);
        entryBox.setAlignment(Pos.CENTER);

        box.getChildren().addAll(name, occupation, entryBox);

        //Stage Settings
        loginStage.getIcons().add(UtilityManager.getImageFromURL("baguette.png", getClass()));
        loginStage.setTitle("DSIR");
        loginStage.setScene(new Scene(box));
        loginStage.setResizable(false);
        loginStage.show();
        isClosedFlag = false;

        //Timeout
        Timeline timer = new Timeline(new KeyFrame(Duration.minutes(5), event -> loginStage.close()));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }

    //Check Closed Status
    boolean isClosed() {
        return isClosedFlag;
    }
}
