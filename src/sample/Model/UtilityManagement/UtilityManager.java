package sample.Model.UtilityManagement;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import sample.Main;

public abstract class UtilityManager {

    private static String urlSoundfileRoot = "src/sample/Resources/soundfile/";

    //COLOR
    public static String getHexColor(Color color ){
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }

    //IMAGE FILE TO OBJECT
    public static ImageView getImageViewFromURL(String filename){
        return new ImageView(new Image(Main.class.getResource("/sample/Resources/imagefile/"+filename).toExternalForm()));
    }
    public static Image getImageFromURL(String filename){
        return new ImageView(new Image(Main.class.getResource("/sample/Resources/imagefile/"+filename).toExternalForm())).getImage();
    }

    public static Image getImageViewToImage(ImageView iv){
        return iv.getImage();
    }

    public static ImageView getImageToImageView(Image img){
        return new ImageView(img);
    }

    public static Image getSoundfileImage(String filename){
        return getImageFromURL(urlSoundfileRoot+filename);
    }

}
