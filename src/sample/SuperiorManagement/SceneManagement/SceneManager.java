package sample.SuperiorManagement.SceneManagement;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneManager {

    //Attributes
    private LoginScene loginScene;
    private MainScene mainScene;

    //Constructor: Manages Child Scenes
    public SceneManager(Stage primaryStage,Class mainclass){
        loginScene = new LoginScene();
        mainScene = new MainScene(primaryStage,mainclass);
    }

    //Execution
    public void start(){
        loginScene.run();

        //Start Main Scene when Login Scene is closed
        Timeline timer = new Timeline();
        timer.getKeyFrames().add(new KeyFrame(Duration.millis(1),event -> {
            if(loginScene.isClosed()) {
                try {
                    System.out.println("Login Scene is closed");
                    System.out.println("Main Scene is showing");
                    mainScene.run();
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
