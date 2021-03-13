package sample.Model.UtilityManagement;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sample.Main;

import java.net.URISyntaxException;
import java.util.*;

public abstract class MediaManager {

    private static MediaPlayer mediaPlayer;

    private static List<String> playlist = Arrays.asList(
            "/sample/Resources/soundfile/song.mp3",
            "/sample/Resources/soundfile/song2.mp3",
            "/sample/Resources/soundfile/song3.mp3",
            "/sample/Resources/soundfile/song4.mp3",
            "/sample/Resources/soundfile/song5.mp3");

    private static int previousIndex = -1;
    private static ListIterator<String> itr = playlist.listIterator();
    private static boolean isAutoSwitchActive = false;

    //TODO:
    // FUSE Play and Shuffle
    // FUSE Repeat, AutoPlay
    // DIVIDE Play INTO Stop, Pause and Play
    private static void play(String str) {
        List<String> lucky= Arrays.asList(str.split("/"));
        System.out.println("Playing Song: " + lucky.get(lucky.size() - 1));

        Media song;
        try {
                song = new Media(Main.class.getResource(str).toURI().toString());
                mediaPlayer = new MediaPlayer(song);
                mediaPlayer.setAutoPlay(true);

                mediaPlayer.setOnEndOfMedia(() -> {
                    if(!itr.hasNext()) reset();
                    play(itr.next());
                });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        mediaPlayer.stop();
    }

    private static void shuffle() {
        Media song;
        String lucky = "";

        try {
            lucky = playlist.get(getRandomIndex());
            song = new Media(Main.class.getResource(lucky).toURI().toString());
            mediaPlayer = new MediaPlayer(song);
            mediaPlayer.setAutoPlay(true);

            mediaPlayer.setOnEndOfMedia(() -> {
                if (!isRepeat()) {
                    shuffle();
                }
            });

            mediaPlayer.play();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        List<String> luckylist = Arrays.asList(lucky.split("/"));
        System.out.println("Playing Song: " + luckylist.get(luckylist.size() - 1));
    }

    private static int getRandomIndex() {
        Random rand = new Random();
        int curindex = rand.nextInt(playlist.size());

        if (curindex == previousIndex)
            return getRandomIndex();

        previousIndex = curindex;

        return curindex;
    }

    private static boolean isRepeat() {
        return mediaPlayer.getCycleCount() == MediaPlayer.INDEFINITE;
    }

    private static boolean isAutoplay() {
        return isAutoSwitchActive;
    }

    public static boolean isMediaPlayerInActive() {
        return mediaPlayer == null;
    }

    public static void setMode(boolean isShuffle) {
        if (isShuffle) shuffle();
        else play(itr.next());
    }

    public static void enableAutoPlay() {
        System.out.println("AutoPlay is enabled");
        isAutoSwitchActive = true;
    }

    public static void disableAutoPlay() {
        System.out.println("AutoPlay is disabled");
        isAutoSwitchActive = false;
    }

    public static void enableRepeat() {
        System.out.println("Repeat is enabled");
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public static void disableRepeat() {
        System.out.println("Repeat is disabled");
        mediaPlayer.setCycleCount(1);
    }

    public static void enableVolume() {
        System.out.println("Volume is enabled");
        mediaPlayer.setVolume(1);
    }

    public static void disableVolume() {
        System.out.println("Volume is disabled");
        mediaPlayer.setVolume(0);
    }

    public static void setVolume(double value) {
        if(value>=0 && value<=100) {
            double adjustedValue=value/100;

            System.out.println("Volume is set to " + adjustedValue);
            mediaPlayer.setVolume(adjustedValue);
        }
    }

    private static void reset(){
        itr = playlist.listIterator();
    }


}
