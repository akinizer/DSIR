package sample.SuperiorManagement.SceneManagement;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Main;

public class SceneManager {

    private LoginScene loginScene;
    private MainScene mainScene;

    public SceneManager(Stage primaryStage,Class mainclass){
        loginScene = new LoginScene();
        mainScene = new MainScene(primaryStage,mainclass);
    }

    public void start(){
        loginScene.run();

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
