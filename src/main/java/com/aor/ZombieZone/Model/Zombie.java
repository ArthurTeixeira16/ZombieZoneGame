package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Zombie extends Element implements HasLife,HasMovement {
    private int life;
    private int speed;
    private long elapsed_time;

    public Zombie(int x,int y,int speed,int life){
        super(x,y);
        this.speed = speed;
        this.life = life;

    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }

    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "Z");
    }

    public void updateZombieWalk(Soldier soldier,Arena arena,long deltaTime){
        elapsed_time += deltaTime;
        int timeToMove = 1000/speed;

        while(elapsed_time>=timeToMove){
           track( soldier, arena);
           elapsed_time -= timeToMove;
        }

    }

    public void track(Soldier soldier, Arena arena) {
        Position soldierPosition = soldier.getPosition();
        Position zombiePosition = getPosition();
        int deltaX = soldierPosition.getX() - zombiePosition.getX();
        int deltaY = soldierPosition.getY() - zombiePosition.getY();
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (deltaX > 0) {
                Position nextPosition = new Position(zombiePosition.getX() + 1, zombiePosition.getY());
                if (arena.canMoveTo(nextPosition)) {
                    moveRight();
                }
            } else {
                Position nextPosition = new Position(zombiePosition.getX() - 1, zombiePosition.getY());
                if (arena.canMoveTo(nextPosition)) {
                    moveLeft();
                }
            }
        } else {
            if (deltaY > 0) {
                Position nextPosition = new Position(zombiePosition.getX(), zombiePosition.getY() + 1);
                if (arena.canMoveTo(nextPosition)) {
                    moveDown();
                }
            } else {
                Position nextPosition = new Position(zombiePosition.getX(), zombiePosition.getY() - 1);
                if (arena.canMoveTo(nextPosition)) {
                    moveUp();
                }
            }
        }
    }
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

    @Override
    public void hit() {
         life --;
    }

    @Override
    public boolean isDead() {
        return life == 0;
    }
}
