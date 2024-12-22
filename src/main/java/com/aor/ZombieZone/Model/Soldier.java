package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Soldier extends Element implements HasLife,HasMovement {
    private int life;
    public Soldier(int x, int y) {
        super(x, y);
        this.life = 3;
    }

    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#000000"));
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()),"@");
    }
    public int getLife() {return this.life;}
    @Override
    public void hit(){
        this.life--;
    }
    @Override
    public boolean isDead(){
        return this.life <= 0;
    }
    @Override
    public void moveUp(){
        this.setPosition(new Position(this.getPosition().getX() , this.getPosition().getY() -1 ));
    }
    @Override
    public void moveDown(){
        this.setPosition(new Position(this.getPosition().getX() , this.getPosition().getY() + 1 ));
    }
    @Override
    public void moveLeft(){
        this.setPosition(new Position(this.getPosition().getX() -1 , this.getPosition().getY() ));
    }
    @Override
    public void moveRight(){
        this.setPosition(new Position(this.getPosition().getX() + 1 , this.getPosition().getY() ));
    }
}