package sample.Model.UtilityManagement;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sample.Main;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public abstract class MediaManager {

    private static MediaPlayer mediaPlayer;
    private static List<String> playlistActual = Arrays.asList(
            "/sample/Resources/soundfile/song.mp3",
            "/sample/Resources/soundfile/song2.mp3",
            "/sample/Resources/soundfile/song3.mp3",
            "/sample/Resources/soundfile/song4.mp3",
            "/sample/Resources/soundfile/song5.mp3");

    private static List<String> playlist = Arrays.asList(
            "/sample/Resources/soundfile/song.mp3",
            "/sample/Resources/soundfile/song2.mp3",
            "/sample/Resources/soundfile/song3.mp3");

    private static int previousIndex = -1;
    private static ListIterator<String> itr = playlist.listIterator();
    private static int ARS=-1;
    private static int mode=-1;
    private static boolean isVolumeActive;

    //TODO:
    // FUSE Play and Shuffle
    // FUSE Repeat, AutoPlay
    // DIVIDE Play INTO Stop, Pause and Play

    //PLAY MODE
    private static void play(String str) {
        List<String> lucky = Arrays.asList(str.split("/"));
        System.out.println("Playing Song: " + lucky.get(lucky.size() - 1));

        Media song;
        try {
            song = new Media(Main.class.getResource(str).toURI().toString());
            mediaPlayer = new MediaPlayer(song);
            mediaPlayer.setAutoPlay(true);

            mediaPlayer.setOnEndOfMedia(() -> {
                if(ARS==1) {
                    if (!itr.hasNext()) reset();
                    play(itr.next());
                }
                else if(ARS==2) {
                    play(str);
                }
                else if(ARS==3) {
                    shuffle();
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //SHUFFLE MODE
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

    //STOP SONG
    public static void stop() {
        mediaPlayer.stop();
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

    //ACTIVE CHECK
    private static boolean isAutoplay() {
        return ARS==1;
    }
    private static boolean isRepeat() {
        return ARS==2;
    }
    private static boolean isShuffle() {
        return ARS==3;
    }

    public static boolean isMediaPlayerInActive() {
        return mediaPlayer == null;
    }

    private static void setMode(boolean isShuffle) {
        if (isShuffle) shuffle();
        else {
            //reset the mediaplayer(not media) to first song instead of advancing onto next one after stop
            itr=playlist.listIterator();
            play(itr.next());
        }
    }

    public static void run(){
        if(ARS==1 || ARS==2)
            setMode(false);
        else if(ARS==3)
            setMode(true);
    }

    //ARS
    public static void enableAutoPlay() {
        System.out.println("AutoPlay is enabled");
        ARS=1;
    }
    public static void enableRepeat() {
        System.out.println("Repeat is enabled");
        //mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        ARS=2;
    }
    public static void enableShuffle() {
        System.out.println("Shuffle is enabled");
        ARS=3;
    }
    public static void disableAutoPlay() {
        System.out.println("AutoPlay is disabled");
    }
    public static void disableRepeat() {
        System.out.println("Repeat is disabled");
    }
    public static void disableShuffle() {
        System.out.println("Shuffle is disabled");
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
            isVolumeActive=true;
        }
    }



    private static void reset() {
        itr = playlist.listIterator();
    }

    public static void setARS(int auto_repeat_shuffle_mode123) {
        if (auto_repeat_shuffle_mode123 == 1) {
            enableAutoPlay();
            disableRepeat();
            disableShuffle();
        } else if (auto_repeat_shuffle_mode123 == 2) {
            disableAutoPlay();
            enableRepeat();
            disableShuffle();
        } else if (auto_repeat_shuffle_mode123 == 3) {
            disableAutoPlay();
            disableRepeat();
            enableShuffle();
        }
    }

    public static int getARS(){
        return ARS;
    }


}
