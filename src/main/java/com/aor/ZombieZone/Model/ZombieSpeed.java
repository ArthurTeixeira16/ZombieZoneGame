package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class ZombieSpeed extends Enemy {
    public ZombieSpeed(int x,int y){
        super(x,y);
        this.life = 1;
        this.speed = 4;

    }
    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#0000ff"));
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "S");
    }
}
