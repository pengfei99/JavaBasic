package org.pengfei.pattern.adapter;

public class MediaAdapter implements MediaPlayer{


    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType){
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMusicPlayer=new VlcPlayer();
        }
        else if(audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer=new Mp4Player();
        }
        else throw new IllegalArgumentException("audio type must be mp4 or vlc");
    }

    public void play(String audioType, String fileName) {

        if(audioType.equalsIgnoreCase("vlc")){
            advancedMusicPlayer.playVlc(fileName);
        }
        else if(audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}
