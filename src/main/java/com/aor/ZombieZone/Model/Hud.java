package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hud {
    private int score;
    private int rounds;
    private Soldier soldier;
    private Arena arena;
    public Hud(Soldier soldier, Arena arena) {
        this.score = 0;
        this.rounds = 1;
        this.soldier = soldier;
        this.arena = arena;
    }
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#0000FF"));
        graphics.putString(1, arena.getHeight(), "Score: " + score);
        graphics.putString(10, arena.getHeight(), "Rounds: " + rounds);
        graphics.putString(20, arena.getHeight(), "Lives: " + soldier.getLife());
    }
    public void increaseScore(int amount) {
        score += amount;
    }

    public void nextRound() {
        rounds++;
    }
}
