package sample.Model.StageManagement;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class StageManager {

    //Attributes
    private LoginStage loginStage;
    private MainStage mainStage;

    //Constructor: Manages Child Stages
    public StageManager() {
        loginStage = new LoginStage();
        mainStage = new MainStage();
    }

    //Execution
    public void start() {
        loginStage.run();

        //Start Main Scene when Login Stage is closed
        Timeline timer = new Timeline();
        timer.getKeyFrames().add(new KeyFrame(Duration.millis(1), event -> {
            if (loginStage.isClosed()) {
                try {
                    System.out.println("Login Scene is closed");
                    System.out.println("Main Scene is showing");
                    mainStage.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                timer.stop();
            }
        }));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }
}
