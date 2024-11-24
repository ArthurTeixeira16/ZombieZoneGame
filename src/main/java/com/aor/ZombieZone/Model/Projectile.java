package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Projectile extends Element implements HasMovement{

    private final int range;
    private int Travelled_distance = 0;

    public Projectile(int x, int y, int range) {
        super(x, y);
        this.range = range;
    }

    @Override
    public Position getPosition() {return super.getPosition();}

    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        screen.putString(getPosition().getX(), getPosition().getY(), "-");
    }

    @Override
    public void moveUp() {
        Position newPosition = new Position(getPosition().x,getPosition().y-1);
        this.setPosition(newPosition);

    }

    @Override
    public void moveDown() {
        Position newPosition = new Position(getPosition().x,getPosition().y+1);
        this.setPosition(newPosition);

    }

    @Override
    public void moveLeft() {
        Position newPosition = new Position(getPosition().x-1,getPosition().y);
        this.setPosition(newPosition);

    }

    @Override
    public void moveRight() {
        Position newPosition = new Position(getPosition().x+1,getPosition().y);
        this.setPosition(newPosition);

    }

    @Override
    public void canMoveTo() {

    }
}
