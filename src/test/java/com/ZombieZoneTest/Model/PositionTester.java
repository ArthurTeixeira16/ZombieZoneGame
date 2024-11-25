package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTester {
    private Position position;
    @BeforeEach
    public void setUp() {
        position = new Position(0,0);
    }
    @Test
    public void testEquals1(){
        Position position1 = new Position(0,0);
        assertTrue(position.equals(position1));
    }
    @Test
    public void testEquals2(){
        Position position2 = new Position(1,0);
        assertFalse(position.equals(position2));
    }
}