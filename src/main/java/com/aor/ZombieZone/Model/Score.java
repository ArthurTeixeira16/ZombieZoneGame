package com.aor.ZombieZone.Model;

public class Score {
    int score;
    public Score() {
        this.score = 0;
    }
    public void addScore(){
        score+=1;
    }
    public int getScore() {
        return score;
    }
}
