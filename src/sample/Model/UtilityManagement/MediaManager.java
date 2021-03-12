package sample.Model.UtilityManagement;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import sample.Main;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class MediaManager {

    private static MediaPlayer mediaPlayer;

    private static List<String> playlist = Arrays.asList(
            "/sample/Resources/soundfile/song.mp3",
            "/sample/Resources/soundfile/song2.mp3",
            "/sample/Resources/soundfile/song3.mp3",
            "/sample/Resources/soundfile/song4.mp3",
            "/sample/Resources/soundfile/song5.mp3");

    private static int previousIndex = -1;
    private static Iterator<String> itr = playlist.iterator();
    private static boolean isPlaying = false;

    private static void play() {
        Media song;
        int curindex = previousIndex;
        if (previousIndex == -1 || !isRepeat())
            curindex = ++previousIndex % 5;

        try {
            song = new Media(Main.class.getResource(playlist.get(curindex)).toURI().toString());
            mediaPlayer = new MediaPlayer(song);

            mediaPlayer.setOnEndOfMedia(() -> {
                if (!isRepeat() && isAutoplay())
                    play();
                else if (!isRepeat() && !isAutoplay())
                    isPlaying = false;
            });

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        mediaPlayer.stop();
        isPlaying = false;
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
        isPlaying = true;

    }

    public static void toggleAutoplay() {
        if (mediaPlayer.isAutoPlay()) {
            mediaPlayer.setAutoPlay(false);
            System.out.println("AutoPlay is disabled");
        } else {
            mediaPlayer.setAutoPlay(true);
            System.out.println("AutoPlay is enabled");
        }
    }

    public static void toggleRepeat() {
        if (mediaPlayer.getCycleCount() == MediaPlayer.INDEFINITE) {
            mediaPlayer.setCycleCount(1);
            System.out.println("Repeat is disabled");
        } else {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            System.out.println("Repeat is enabled");
        }
    }

    public static void toggleVolume() {
        if (mediaPlayer.getVolume() == 1) {
            mediaPlayer.setVolume(0);
            System.out.println("Volume is disabled");
        } else if (mediaPlayer.getVolume() == 0) {
            mediaPlayer.setVolume(1);
            System.out.println("Volume is enabled");
        }
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
        return mediaPlayer.isAutoPlay();
    }

    public static boolean isMediaPlayerActive() {
        return mediaPlayer!=null;
    }

    public static void setMode(boolean isShuffle){
        if(isShuffle) shuffle();
        else play();
    }

}
