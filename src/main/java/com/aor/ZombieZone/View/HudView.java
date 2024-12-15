package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Model.Hud;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class HudView {
    private Hud hud;

    public HudView(Hud hud) {
        this.hud=hud;
    }

    public void render(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#0000FF"));
        graphics.putString(1, hud.getHeight(), "Score:" + hud.getScore().getScore());
        graphics.putString(10, hud.getHeight(), "Round: " + hud.getRound().getRound());
        graphics.putString(20, hud.getHeight(), "Lives: " + hud.getSoldier().getLife());
    }
}
