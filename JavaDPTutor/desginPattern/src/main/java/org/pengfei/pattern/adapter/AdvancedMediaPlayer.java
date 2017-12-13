package org.pengfei.pattern.adapter;

import java.util.IllegalFormatException;

public interface AdvancedMediaPlayer {
    public void playVlc(String fileName);
    public void playMp4(String fileName) throws IllegalFormatException;
}
