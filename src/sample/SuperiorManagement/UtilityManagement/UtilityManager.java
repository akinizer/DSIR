package sample.SuperiorManagement.UtilityManagement;

import javafx.scene.paint.Color;

public abstract class UtilityManager {
    public static String getHexColor(Color color ){
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }
}
