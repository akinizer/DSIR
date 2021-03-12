package sample.Model.UtilityManagement;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import sample.Main;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class MediaManager {

    private static MediaPlayer mediaPlayer;
    private static List<String> playlist=Arrays.asList(
            "/sample/Resources/soundfile/song.mp3",
            "/sample/Resources/soundfile/song2.mp3",
            "/sample/Resources/soundfile/song3.mp3",
            "/sample/Resources/soundfile/song4.mp3",
            "/sample/Resources/soundfile/song5.mp3");

    public static void play(){
        Media song = null;
        try {
            song = new Media(Main.class.getResource(playlist.get(0)).toURI().toString());
            mediaPlayer = new MediaPlayer(song);
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public static void stop(){
        mediaPlayer.stop();
    }
    public static void shuffle(){
        for (String s:playlist) {
            System.out.println(s);
        }

        Media song = null;
        try {
            Random rand = new Random();
            String randomSongURL=playlist.get(rand.nextInt(playlist.size()));
            song = new Media(Main.class.getResource("/sample/Resources/soundfile/song.mp3").toURI().toString());
            mediaPlayer = new MediaPlayer(song);
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
