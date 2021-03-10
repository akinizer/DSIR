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
        box.setPrefSize(250, 84);
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
        loginbutton.setOnAction(event -> {
            if (name.getText().equals(defaultstringName) || occupation.getText().equals(defaultstringOccupation))
                return;

            try {

                if (FileManager.checkUserExists(name.getText())) {
                    StatsManager.setName(name.getText());
                    StatsManager.setClasstype(occupation.getText());
                    isClosedFlag = true;

                    Timeline timer = new Timeline(new KeyFrame(Duration.millis(2), eventCloser -> loginStage.close()));
                    timer.setCycleCount(Animation.INDEFINITE);
                    timer.play();
                }



            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //Login Button
        Button registerButton = new Button("Register");
        registerButton.setOnAction(event -> {
            if (name.getText().equals(defaultstringName) || occupation.getText().equals(defaultstringOccupation))
                return;

            try {
                boolean isNewUser=!FileManager.checkUserExists(name.getText());

                if(isNewUser) {
                    StatsManager.setName(name.getText());
                    StatsManager.setClasstype(occupation.getText());
                    isClosedFlag = true;
                    FileManager.writeFileTest(name.getText() + "," + occupation.getText());

                    Timeline timer = new Timeline(new KeyFrame(Duration.millis(2), eventCloser -> {

                        loginStage.close();
                    }));
                    timer.setCycleCount(Animation.INDEFINITE);
                    timer.play();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        HBox entryBox = new HBox(loginbutton, registerButton);
        entryBox.setAlignment(Pos.CENTER);

        box.getChildren().addAll(name, occupation, entryBox);

        //Stage Settings
        //loginStage.getIcons().add(new ImageView(new Image(getClass().getResource("/sample/Resources/baguette.png").toExternalForm())).getImage());
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
