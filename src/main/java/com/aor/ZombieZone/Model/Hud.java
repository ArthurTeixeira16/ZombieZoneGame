package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hud {
    private Score score;
    private Round round;
    private Soldier soldier;
    private Arena arena;
    public Hud(Soldier soldier, Arena arena, Score score,Round round) {
        this.score = score;
        this.round = round;
        this.soldier = soldier;
        this.arena = arena;
    }
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#0000FF"));
        graphics.putString(1, arena.getHeight(), "Score:" + score.getScore());
        graphics.putString(10, arena.getHeight(), "Round: " + round.getRound());
        graphics.putString(20, arena.getHeight(), "Lives: " + soldier.getLife());
    }
}
