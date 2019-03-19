package org.pengfei.Lesson13_Common_Data_Structure.L13_S1_Arrays;

public class GameEntry {
    private String name;
    private int score;

    public GameEntry(String GameName,int GameScore){
        name=GameName;
        score=GameScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "GameEntry{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
