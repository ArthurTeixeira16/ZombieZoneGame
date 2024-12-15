package com.aor.ZombieZone.View;
import com.aor.ZombieZone.Model.Arena;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
public class ArenaView {

    private Arena arena;

    public ArenaView(Arena arena) {
        this.arena = arena;
    }

    public void render(TextGraphics screen) {
        for (int y = 0; y < arena.getHeight(); y++) {
            for (int x = 0; x < arena.getWidth(); x++) {
                screen.setBackgroundColor(arena.getColors()[y][x]);
                TextColor foregroundColor = arena.getColors()[y][x];
                screen.setForegroundColor(foregroundColor);
                screen.putString(x, y, String.valueOf(arena.getTiles()[y][x]));
            }
        }
    }
}
