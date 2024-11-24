package com.aor.ZombieZone.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ZombieTester {
    private Arena arena;
    private Zombie zombie;
    @BeforeEach
    public void setUp(){
        arena = new Arena(40,30);
        zombie = new Zombie(10, 15);
    }

    @Test
    public void testInitialZombiePosition(){
        Position InitialPosition = zombie.getPosition();
        Assertions.assertEquals(10, InitialPosition.getX());
        Assertions.assertEquals(15, InitialPosition.getY());
    }
}
