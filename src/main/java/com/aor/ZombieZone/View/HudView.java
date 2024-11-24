package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Model.Hud;
import com.googlecode.lanterna.graphics.TextGraphics;


public class HudView {
    private Hud hud;

    public HudView(Hud hud) {
        this.hud=hud;
    }

    public void render(TextGraphics graphics) {
        hud.draw(graphics);
    }
}
