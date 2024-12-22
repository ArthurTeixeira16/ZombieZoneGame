package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class ZombieHeavy extends Enemy {
    public ZombieHeavy(int x,int y){
        super(x,y);
        this.life = 4;
        this.speed = 1;

    }
    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#4B5320"));
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "T");
    }
}
