package org.pengfei.pattern.adapter;

import java.util.IllegalFormatException;

public class Mp4Player implements AdvancedMediaPlayer{
    public void playVlc(String fileName) {
        throw new IllegalArgumentException("Mp3 player can't play vlc format file");
    }

    public void playMp4(String fileName) throws IllegalFormatException {
        System.out.println("Playing mp4 format file");

    }
}
