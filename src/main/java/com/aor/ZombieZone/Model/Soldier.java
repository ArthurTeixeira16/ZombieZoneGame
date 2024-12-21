package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Soldier extends Entity {
    public Soldier(int x, int y) {
        super(x, y);
        this.life = 3;
    }

    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#000000"));
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()),"@");
    }
}