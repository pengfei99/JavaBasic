package org.pengfei.Lesson00_Java_Basics.S07_Interfaces_Packages.source;

public class BreakingBad implements Series {
    private int episodeToPlay=0;
    private int episodePlaying=0;

    @Override
    public int getNext() {
        episodeToPlay++;
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

    public int getPrevious(){
        episodeToPlay--;
        return episodeToPlay;
    }
}
