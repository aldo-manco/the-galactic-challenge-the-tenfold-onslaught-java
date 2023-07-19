package org.aldomanco.sound;

// Support - Sound ---

import javax.sound.sampled.*;
import java.net.URL;

/**
 * Created by unieuro on 1/4/2020.
 */
public class Sound implements LineListener {

    private Clip track;

    public Sound(String pathTrack) {

        try {
            URL url = getClass().getResource(pathTrack);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            track = (Clip) AudioSystem.getLine(info);
            track.open(audioInputStream);
            track.addLineListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(LineEvent event) {

        if (event.getType().equals(LineEvent.Type.STOP)) {
            track.setFramePosition(1);
        }
    }

    public void play() {
        track.start();
    }

    public void stop() {
        track.stop();
    }

    public void loop() {
        track.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public boolean isPlaying(){
        return track.isRunning();
    }
}
