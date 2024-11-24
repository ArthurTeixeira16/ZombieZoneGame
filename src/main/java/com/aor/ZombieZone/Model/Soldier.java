package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Soldier extends Element implements HasLife,HasMovement{

    public Soldier(int x, int y) {
        super(x, y);
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }


    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#000000"));
        screen.putString(getPosition().getX(), getPosition().getY(), "O");
    }
}