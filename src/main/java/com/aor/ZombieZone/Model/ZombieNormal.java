package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class ZombieNormal extends Enemy {
    public ZombieNormal(int x,int y){
        super(x,y);
        this.life = 2;
        this.speed = 2;

    }
    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "Z");
    }
}
