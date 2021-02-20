package sample;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    @FXML private Tab towntab;
    @FXML private Tab dungeontab;
    @FXML private Tab colessiumntab;
    @FXML private Tab storetab;
    @FXML private Tab neighborhoodtab;

    public List<Tab> getGameTabs(){
        return Arrays.asList(towntab,dungeontab,colessiumntab,storetab,neighborhoodtab);
    }
}
