package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Model.Menu;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;

public class MenuView {
    private Menu menu;

    public MenuView(Menu menu) {
        this.menu = menu;
    }

    public void render(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        screen.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        screen.putString(5, 5, "Menu", SGR.BOLD);
        for (int i = 0; i < menu.getNumberEntries(); i++) {
            if (menu.isSelected(i)) {
                screen.setForegroundColor(TextColor.Factory.fromString("#FFD700"));
            } else {
                screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            }
            screen.putString(5, 8 + i * 3, menu.getEntry(i));
        }
    }
}
