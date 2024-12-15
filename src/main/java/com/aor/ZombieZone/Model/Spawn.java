package com.aor.ZombieZone.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Random;


public class Spawn {
    private int height;
    private int width;
    private Random random;
    public List<Position> occupiedPos = new ArrayList<>();
    private Soldier soldier;

    private int zombies_per_round = 3;
    private int speedzombie_percentage = 5;
    private int  heavyzombie_percentage = 10;

    public Spawn(int width,int height,Soldier soldier){
        this.height = height;
        this.width = width;
        this.random = new Random();
        this.soldier = soldier;
    }
    public List<Enemy> SpawnZombies(Round round){
        occupiedPos.clear();
        occupiedPos.add(soldier.getPosition());

        List<Enemy> zombies = new ArrayList<>();
        int zombiequantity = (round.getRound() * zombies_per_round);

        while(zombiequantity > 0) {
            Position current_position = GeneratePositions();

            if(zombiequantity < 5) {
                Enemy zombie = new ZombieNormal(current_position.getX(),current_position.getY());
                zombies.add(zombie);
                occupiedPos.add(current_position);
                zombiequantity--;
            }
            else{
                int chance = random.nextInt(101);
                if(chance < speedzombie_percentage * round.getRound()) {
                    Enemy zombie = new ZombieSpeed(current_position.getX(),current_position.getY());
                    zombies.add(zombie);
                    occupiedPos.add(current_position);
                    zombiequantity-=2;
                }
                else if(chance < heavyzombie_percentage * round.getRound()){
                    Enemy zombie = new ZombieHeavy(current_position.getX(),current_position.getY());
                    zombies.add(zombie);
                    occupiedPos.add(current_position);
                    zombiequantity-=2;
                } else{
                    Enemy zombie = new ZombieNormal(current_position.getX(),current_position.getY());
                    zombies.add(zombie);
                    occupiedPos.add(current_position);
                    zombiequantity--;
                }
            }
        }
        return zombies;
    }

    private Position GeneratePositions(){
        int x,y;
        Position position;
        do {
            x = random.nextInt(2, width - 1);
            y = random.nextInt(2, height - 1);
            position = new Position(x,y);
        }while(!isOccupied(position));
        return position;
    }
    private boolean isOccupied(Position position){
        for(Position pos:occupiedPos){
            if(Math.abs(position.getX() - position.getX()) < 3 && Math.abs(position.getY() - pos.getY()) < 3){
                return true;
            }
        }
        return false;
    }
}
