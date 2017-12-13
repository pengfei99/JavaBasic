package org.pengfei.pattern.adapter;

import java.util.IllegalFormatException;

public class VlcPlayer implements AdvancedMediaPlayer {
    public void playVlc(String fileName) {
        System.out.println("Playing vlc format file. Name: "+fileName);
    }

    public void playMp4(String fileName) {
       throw new IllegalArgumentException("Vlc Player can't play mp4 format file");
    }
}
