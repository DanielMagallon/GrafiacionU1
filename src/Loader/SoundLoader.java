package Loader;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SoundLoader {

    public static Clip coinSound = loadSound("coin.wav");
    public static Clip ghostSound = loadSound("ghost.wav");
    public static Clip pipeIn = loadSound("pipeIn.wav");
    public static Clip finished = loadSound("finished.wav");
    public static Clip theme1 = loadSound("themedown_1.wav");
//    public static Clip itsme = loadSound("itsme.wav");
    public static Clip helpTrack = loadSound("help.wav");
    public static Clip startSoundTrack = loadSound("startsoundtrack.wav");

    public static Clip loadSound(String path) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new URL((SoundLoader.class.getResource
                    ("/rsc/sounds/")+path)));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            return clip;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
