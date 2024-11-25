package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Soldier extends Element implements HasLife,HasMovement{
    private int life = 3;

    public int getLife() {
        return life;
    }

    public Soldier(int x, int y) {
        super(x, y);
    }



    @Override
    public Position getPosition() {
        return super.getPosition();
    }

    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#000000"));
        screen.putString(getPosition().getX(), getPosition().getY(), "@");
    }

    @Override
    public void moveUp() {
        Position newPosition = new Position(getPosition().x, getPosition().y - 1);
        this.setPosition(newPosition);
    }

    @Override
    public void moveDown() {
        Position newPosition = new Position(getPosition().x, getPosition().y + 1);
        this.setPosition(newPosition);
    }

    @Override
    public void moveLeft() {
        Position newPosition = new Position(getPosition().x - 1, getPosition().y);
        this.setPosition(newPosition);
    }

    @Override
    public void moveRight() {
        Position newPosition = new Position(getPosition().x + 1, getPosition().y);
        this.setPosition(newPosition);
    }
}