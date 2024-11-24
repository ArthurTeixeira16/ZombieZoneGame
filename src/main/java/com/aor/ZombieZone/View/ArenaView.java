package com.aor.ZombieZone.View;
import com.aor.ZombieZone.Model.Arena;
import com.googlecode.lanterna.graphics.TextGraphics;
public class ArenaView {

    private Arena arena;

    public ArenaView(Arena arena) {
        this.arena = arena;
    }

    public void render(TextGraphics screen) {
        arena.draw(screen);
    }
}
