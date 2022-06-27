package gui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class SoundEffects {
    static Clip clip;
    private static final File click = new File("src/resources/Click.wav");
    public static void moveSound() {
        try {
            final AudioInputStream input = AudioSystem.getAudioInputStream(click);
            clip = AudioSystem.getClip();
            clip.open(input);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    private static final File click1 = new File("src/resources/First_Click.wav");
    public static void clickSound() {
        try {
            final AudioInputStream input = AudioSystem.getAudioInputStream(click1);
            clip = AudioSystem.getClip();
            clip.open(input);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    private static final File start = new File("src/resources/StartSound.wav");
    public static void startSound() {
        try {
            final AudioInputStream input = AudioSystem.getAudioInputStream(start);
            clip = AudioSystem.getClip();
            clip.open(input);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private static final File update = new File("src/resources/UpdateSound.wav");
    public static void updateSound() {
        try {

            final AudioInputStream input = AudioSystem.getAudioInputStream(update);
            clip = AudioSystem.getClip();
            clip.open(input);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


}
