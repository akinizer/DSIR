package sample.SuperiorManagement.SceneManagement;

import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Main;

public class SceneManager {

    private LoginScene loginScene;
    private MainScene mainScene;

    public SceneManager(Stage primaryStage,Class mainclass){
        loginScene = new LoginScene(primaryStage,mainclass);
        mainScene = new MainScene(primaryStage,mainclass);
    }

    public void start(){
        getLoginScene().run();
    }

    private LoginScene getLoginScene(){
        return loginScene;
    }
    private MainScene getMainScene(){
        return mainScene;
    }

    private void disposeLoginScene(){}
    private void disposeMainScene(){}

}
