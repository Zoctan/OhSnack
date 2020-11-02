package com.zoctan;

import javax.swing.*;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class Player extends Thread {
    AudioClip player;
    String music;

    public Player(String music) {
        this.music = music;
    }

    public void play() {
        player = JApplet.newAudioClip(this.getClass().getResource(music));
        player.play();
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