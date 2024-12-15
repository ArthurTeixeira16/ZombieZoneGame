package com.aor.ZombieZone.Model;

public class Round {
    private int round;
    public Round() {
        this.round = 1;
    }
    public void nextRound(){
        round++;
    }
    public int getRound() {
        return round;
    }
    public void setRound(int round) {
        this.round = round;
    }
}
