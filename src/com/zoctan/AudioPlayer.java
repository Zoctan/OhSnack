package com.zoctan;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 播放器
 *
 * @author Zoctan
 */
public class AudioPlayer extends Thread {

    Player player;
    BufferedInputStream buffer;
    ExecutorService executorService;
    Future future;

    public AudioPlayer(String music) {
        this.buffer = new BufferedInputStream(this.getClass().getResourceAsStream(music));
        try {
            this.player = new Player(this.buffer);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
        executorService = Executors.newSingleThreadExecutor();
    }

    public void play() {
        if (future != null) {
            try {
                this.player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        } else {
            future = executorService.submit(this);
        }
    }

    public void close() {
        this.player.close();
    }

    @Override
    public void run() {
        super.run();
        try {
            this.player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}