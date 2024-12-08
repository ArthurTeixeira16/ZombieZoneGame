package com.aor.ZombieZone.Model;

public class Score {
    int score;
    public Score() {
        this.score = 0;
    }
    public void addScore(int n){
        score=score+n;
    }
    public int getScore() {
        return score;
    }
}
