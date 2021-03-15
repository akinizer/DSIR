package sample.Model.UtilityManagement;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sample.Main;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public abstract class MediaManager {

    private static MediaPlayer mediaPlayer;
    private static List<String> playlist = getSongs();

    private static String currentSongName = "";
    private static String songURL = "/sample/Resources/soundfile/playlist/";

    private static int previousIndex = -1;
    private static ListIterator<String> itr = playlist.listIterator();
    private static int ARS = -1;
    private static boolean isVolumeActive;

    //TODO:
    // FUSE Play and Shuffle
    // FUSE Repeat, AutoPlay
    // DIVIDE Play INTO Stop, Pause and Play

    //PLAY MODE
    private static void play(String str) {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
            mediaPlayer.play();
            return;
        }

        Media song;
        try {
            song = new Media(Main.class.getResource(songURL + str).toURI().toString());
            mediaPlayer = new MediaPlayer(song);
            mediaPlayer.setAutoPlay(true);

            mediaPlayer.setOnEndOfMedia(() -> {
                if (ARS == 1) {
                    if (!itr.hasNext()) reset();
                    play(itr.next());
                } else if (ARS == 2) {
                    play(str);
                } else if (ARS == 3) {
                    shuffle();
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        currentSongName = str.split(".mp")[0];
        System.out.println("Playing Song: " + str);
    }

    //SHUFFLE MODE
    private static void shuffle() {
        Media song;
        String lucky = "";

        try {
            lucky = playlist.get(getRandomIndex());
            song = new Media(Main.class.getResource(songURL + lucky).toURI().toString());
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

        currentSongName = lucky.split(".mp")[0];
        System.out.println("Playing Song: " + lucky);
    }

    //STOP SONG
    public static void stop() {
        mediaPlayer.stop();
    }

    //PAUSE SONG
    public static void pause() {
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
            mediaPlayer.pause();
    }

    public static void skip() {
        if (mediaPlayer != null)
            stop();

        if (ARS == 1 || ARS == 2) {
            if (itr.hasNext()) {
                play(itr.next());
            } else {
                reset();
                play(itr.next());
            }
        } else if (ARS == 3) {
            shuffle();
        }
    }

    //RANDOM INDEX GENERATOR
    private static int getRandomIndex() {
        Random rand = new Random();
        int curindex = rand.nextInt(playlist.size());

        if (curindex == previousIndex)
            return getRandomIndex();

        previousIndex = curindex;

        return curindex;
    }

    //ACTIVENESS CHECK
    private static boolean isAutoplay() {
        return ARS == 1;
    }

    private static boolean isRepeat() {
        return ARS == 2;
    }

    private static boolean isShuffle() {
        return ARS == 3;
    }

    public static boolean isMediaPlayerInActive() {
        return mediaPlayer == null;
    }

    public static boolean isPlayIconValid() {
        return mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }

    //MODES
    private static void setMode(boolean isShuffle) {
        if (isShuffle) shuffle();
        else {
            //reset the mediaplayer(not media) to first song instead of advancing onto next one after stop
            itr = playlist.listIterator();
            play(itr.next());
        }
    }

    public static void run() {
        if (ARS == 1 || ARS == 2)
            setMode(false);
        else if (ARS == 3)
            setMode(true);
    }

    private static void reset() {
        itr = playlist.listIterator();
    }

    //ARS
    private static void enableAutoPlay() {
        System.out.println("AutoPlay is enabled");
        ARS = 1;
    }

    private static void enableRepeat() {
        System.out.println("Repeat is enabled");
        ARS = 2;
    }

    private static void enableShuffle() {
        System.out.println("Shuffle is enabled");
        ARS = 3;
    }

    public static void setARS(int auto_repeat_shuffle_mode123) {
        if (auto_repeat_shuffle_mode123 == 1) {
            enableAutoPlay();
        } else if (auto_repeat_shuffle_mode123 == 2) {
            enableRepeat();
        } else if (auto_repeat_shuffle_mode123 == 3) {
            enableShuffle();
        }
    }

    public static int getARS() {
        return ARS;
    }

    //VOLUME
    public static void enableVolume() {
        System.out.println("Volume is enabled");
        mediaPlayer.setVolume(100);
        isVolumeActive = true;
    }

    public static void disableVolume() {
        System.out.println("Volume is disabled");
        mediaPlayer.setVolume(0);
        isVolumeActive = false;
    }

    public static void setVolume(double value) {
        if (value >= 0 && value <= 100) {
            double adjustedValue = value / 100;

            System.out.println("Volume is set to " + adjustedValue);
            mediaPlayer.setVolume(adjustedValue);
            isVolumeActive = true;
        }
    }

    // TITLE
    public static String getCurrentSongName() {
        return currentSongName;
    }

    //Gets all song files from playlist directory and lists their names
    private static void listSongs() {
        File file = new File(Paths.get("").toAbsolutePath().toString() + "/src/sample/Resources/soundfile/playlist");
        File[] files = file.listFiles();
        assert files != null;
        for (File song : files) {
            System.out.println(song.getName());
        }
    }

    //Gets all song files from playlist directory instead of specifying song names in a list
    private static List<String> getSongs() {
        File file = new File(Paths.get("").toAbsolutePath().toString() + "/src/sample/Resources/soundfile/playlist");
        File[] files = file.listFiles();
        assert files != null;

        List<String> songlist = new ArrayList<>();
        for (File song : files) {
            assert false;
            songlist.add(song.getName());
        }
        return songlist;
    }
}
