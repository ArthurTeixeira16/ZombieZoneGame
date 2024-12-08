package com.aor.ZombieZone.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Spawn {
    private int height;
    private int width;
    private Random random;
    private int speed = 1;
    List<Position> occupiedPos = new ArrayList<>();
    public Spawn(int width,int height,Soldier soldier){
        this.height = height;
        this.width = width;
        this.random = new Random();
        occupiedPos.add(soldier.getPosition());
    }
    public List<Zombie> SpawnZombies(){//não sei pq mas os zumbis ainda não tão respeitando a caceta das boudaries
        List<Zombie> zombies = new ArrayList<>();
        int i = 0;
        while(i < 4) {//Loop para quantidade de zumbis que quero spawnar (depois alteraremos quando fizermos os rounds)
            int x = random.nextInt(1,width-1);
            int y = random.nextInt(1,height-1);
            Position current_position = new Position(x,y);//as coordenadas a serem testadas
            for(Position pos:occupiedPos){
                while((Math.abs(current_position.getX() - pos.getX()) < 3 && Math.abs(current_position.getY() - pos.getY()) < 3)){
                    x= random.nextInt(2,width-1);
                    y = random.nextInt(2,height-1);
                    current_position = new Position(x,y);
                }
            }
            Zombie zombie = new Zombie(x,y,speed);
            zombies.add(zombie);
            occupiedPos.add(current_position);
            i++;
        }
        return zombies;
    }
}
