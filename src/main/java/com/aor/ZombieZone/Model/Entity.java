package com.aor.ZombieZone.Model;

public abstract class Entity extends Element implements HasLife,HasMovement{
    protected int life;
    public Entity(int x, int y) {super(x, y);}
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
