package sample.SuperiorManagement.SceneManagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import javafx.scene.Scene;
import sample.Controller;
import sample.Main;
import sample.SuperiorManagement.UnitTestManagement.UnitTestManager;

public class SceneManager {

    private LoginScene loginScene;
    private MainScene mainScene;

    public SceneManager(){
        loginScene = new LoginScene();
    }

    public LoginScene getLoginScene(){
        return loginScene;
    }

    public void disposeLoginScene(){}

}
