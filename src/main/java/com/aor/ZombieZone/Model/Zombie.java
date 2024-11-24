package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Zombie extends Element implements HasLife,HasMovement {
    private int life;
    public Zombie(int x,int y){
        super(x,y);
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }

    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        screen.putString(getPosition().getX(), getPosition().getY(), "Z");
    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveRight() {

    }
}
