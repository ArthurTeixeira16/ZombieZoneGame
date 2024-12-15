package com.aor.ZombieZone.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Random;


public class Spawn {
    private int height;
    private int width;
    private Random random;
    private int speed = 2;
    private int life = 2;
    private List<Position> occupiedPos = new ArrayList<>();
    public Spawn(int width,int height,Soldier soldier){
        this.height = height;
        this.width = width;
        this.random = new Random();
        occupiedPos.add(soldier.getPosition());
    }
    public List<Enemy> SpawnZombies(Round round){
        List<Enemy> zombies = new ArrayList<>();
        int i = (round.getRound()*3);
        while(i !=0) {
            int x = random.nextInt(1, width - 1);
            int y = random.nextInt(1, height - 1);
            Position current_position = new Position(x, y);
            for (Position pos : occupiedPos) {
                while ((Math.abs(current_position.getX() - pos.getX()) < 3 && Math.abs(current_position.getY() - pos.getY()) < 3)) {
                    x = random.nextInt(2, width - 1);
                    y = random.nextInt(2, height - 1);
                    current_position = new Position(x, y);
                }
            }
            if(i < 5) {
                Enemy zombie = new ZombieNormal(x, y);
                zombies.add(zombie);
                occupiedPos.add(current_position);
                i--;
            }
            else{
                Random random = new Random();
                int chance = random.nextInt(101);
                if(chance < 51) {
                    Enemy zombie = new ZombieSpeed(x, y);
                    zombies.add(zombie);
                    occupiedPos.add(current_position);
                    i-=3;
                }
                else{
                    Enemy zombie = new ZombieHeavy(x, y);
                    zombies.add(zombie);
                    occupiedPos.add(current_position);
                    i-=3;
                }
            }

        }
        return zombies;
    }
}
