package com.aor.ZombieZone.Model;

public class Hud {
    private Score score = new Score();
    private Round round = new Round();
    private Game game;
    public Hud(Game game) {
        this.game = game;
    }
    public void resetHud(){
        score = new Score();
        round = new Round();
    }
    public Score getScore() {
        return score;
    }
    public void setScore(Score score) {
        this.score = score;
    }
    public Round getRound() {
        return round;
    }
    public int getHeight() {
        return game.getArena().getHeight();
    }
    public int getWidth() {
        return game.getArena().getWidth();
    }
    public Soldier getSoldier() {
        return game.getSoldier();
    }

}
