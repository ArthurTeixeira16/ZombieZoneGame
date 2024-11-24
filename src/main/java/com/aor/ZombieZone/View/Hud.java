package com.aor.ZombieZone.View;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.aor.ZombieZone.Model.Soldier;

public class Hud {
    private int score;
    private int rounds;
    private Soldier soldier;

    public Hud(Soldier soldier) {
        this.score = 0;
        this.rounds = 1;
        this.soldier = soldier;
    }

    public void render(TextGraphics graphics) {
        graphics.putString(1, 1, "Score: " + score);
        graphics.putString(1, 2, "Rounds: " + rounds);
        graphics.putString(1, 3, "Lives: " + soldier.getLife());
    }

    public void increaseScore(int amount) {
        score += amount;
    }

    public void nextRound() {
        rounds++;
    }
}
