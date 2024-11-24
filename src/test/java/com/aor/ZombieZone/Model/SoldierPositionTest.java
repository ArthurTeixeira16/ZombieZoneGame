package com.aor.ZombieZone.Model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SoldierPositionTest {
    private Arena arena;
    private Soldier soldier;

    @BeforeEach
    public void setUp(){
        arena = new Arena(40,30);
        soldier = new Soldier(20,15);
    }

    @Test
    public void testInitialPosition(){
        Position initialPosition = soldier.getPosition();
        Assertions.assertEquals(20,initialPosition.getX());
        Assertions.assertEquals(15,initialPosition.getY());
    }

    @Test
    public void testSoldierInLimits(){
        Position soldierPosition = soldier.getPosition();
        Assertions.assertTrue(soldierPosition.getX() > 0 && soldierPosition.getX() < 40);
        Assertions.assertTrue(soldierPosition.getY() > 0 && soldierPosition.getY() < 30);
    }



}
