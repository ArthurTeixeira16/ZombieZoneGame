package com.aor.ZombieZone.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


public class SpawnTester {
    private Spawn spawn;
    private List<Zombie> zombieList;
    private Soldier soldier;
    @BeforeEach
    public void setUp(){
        spawn = new Spawn(40,30,soldier);
        zombieList = spawn.SpawnZombies();
    }

    @Test
    public void testHowManyZombies(){
        Assertions.assertEquals(4,zombieList.size());
    }

    @Test
    public void testZombieDistance(){
        for(int i=0; i<zombieList.size();i++){
            for(int j= i+1;j<zombieList.size();j++){
                Position zombie1 = zombieList.get(i).getPosition();
                Position zombie2 = zombieList.get(j).getPosition();

                Assertions.assertTrue(Math.abs(zombie1.getX()-zombie2.getX()) > 2 && Math.abs(zombie1.getY()-zombie2.getY()) > 2,"Zumbis muito próximos um do outro!");
            }
        }


    }
    @Test
    public void testZombieSpawnInLimits(){
        for(Zombie zombie:zombieList){
            Position pos = zombie.getPosition();

            Assertions.assertTrue(pos.getX() > 0 && pos.getX() < 40);
            Assertions.assertTrue(pos.getY() > 0 && pos.getY() < 40);
        }
    }

}
