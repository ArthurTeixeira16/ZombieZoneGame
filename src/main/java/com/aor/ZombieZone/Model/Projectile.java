package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Projectile extends Element implements HasMovement{

    private final int range;
    private int Travelled_distance = 0;
    private int speed;
    private long elapsedTime;
    private Position direction;
    private boolean isDestroyed = false;

    public Projectile(int x, int y, int range, int speed) {
        super(x, y);
        this.range = range;
        this.speed = speed;

    }
    public void updateProjectile(long deltaTime){
        elapsedTime += deltaTime;

        int timeToMove = 1000/speed;

        while(elapsedTime>=timeToMove){
            move();
            Travelled_distance++;
            elapsedTime -= timeToMove;

            if(Travelled_distance>=range){
                destroy();
                System.out.println("Bala destruída");
                break;
            }
        }
    }

    public void destroy(){
        this.isDestroyed = true;
    }
     public boolean isDestroyed(){
        return isDestroyed;
     }

    @Override
    public Position getPosition() {return super.getPosition();}

    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        screen.putString(getPosition().getX(), getPosition().getY(), ".");
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
    public void move(){

        if(direction.equals(new Position(0,-1))){
            moveUp();
        } else if (direction.equals(new Position(0,1))) {
            moveDown();
        } else if (direction.equals(new Position(-1,0))) {
            moveLeft();
        } else if (direction.equals(new Position(1,0))) {
            moveRight();
        }
    }

    public void setDirection(Position direction) {
        this.direction = direction;
    }
}
