package org.pengfei.Lesson00_Java_Basics.S07_Interfaces_Packages.source;

public class GameOfThrone implements Series {
    private int episodeToPlay=0;
    private int episodePlaying=0;

    @Override
    public int getNext() {
        this.episodeToPlay+=2;
        return episodeToPlay;
    }

    @Override
    public void reset() {
        episodeToPlay=episodePlaying;
    }

    @Override
    public void setStart(int x) {
        episodeToPlay=x;
        episodePlaying=x;
    }

}
