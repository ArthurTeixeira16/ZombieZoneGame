package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Model.Menu;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class MenuView {
    private Menu menu;

    public MenuView(Menu menu) {
        this.menu = menu;
    }

    public void render(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        screen.fill(' ');
        screen.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        screen.putString(10, 5, "Zombie Zone", SGR.BOLD);
        for (int i = 0; i < menu.getNumberEntries(); i++) {
            if (menu.isSelected(i)) {
                screen.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
            } else {
                screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            }
            screen.putString(13, 8 + i * 3, menu.getEntry(i));
        }
    }
}
