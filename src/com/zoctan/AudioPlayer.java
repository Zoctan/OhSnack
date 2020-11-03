package com.zoctan;

import javax.swing.*;
import java.applet.AudioClip;

/**
 * 播放器
 *
 * @author Zoctan
 */
public class AudioPlayer extends Thread {
    AudioClip player;
    String music;

    public AudioPlayer(String music) {
        this.music = music;
    }

    public void play() {
        this.player = JApplet.newAudioClip(this.getClass().getResource(this.music));
        this.player.play();
    }

    public void close() {
        this.player.stop();
        this.interrupt();
    }

    @Override
    public void run() {
        super.run();
        this.play();
    }

}