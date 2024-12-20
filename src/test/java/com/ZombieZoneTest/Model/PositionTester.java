package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.Position;
import com.aor.ZombieZone.Model.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTester {
    private Position pos;
    private final int x = 1;
    private final int y = 1;
    @BeforeEach
    public void setUp() { pos = new Position(x,y);}
    @Test
    public void positionGetterTest() {
        assertEquals(x, pos.getX());
        assertEquals(y, pos.getY());
    }
    @Test
    public void positionMoveUpTest() {
        pos=pos.up();
        assertEquals(y-1, pos.getY());
    }
    @Test
    public void positionMoveDownTest() {
        pos=pos.down();
        assertEquals(y+1, pos.getY());
    }
    @Test
    public void positionMoveRightTest() {
        pos=pos.right();
        assertEquals(x+1, pos.getX());
    }
    @Test
    public void positionMoveLeftTest() {
        pos=pos.left();
        assertEquals(x-1, pos.getX());
    }
    @Test
    public void positionEqualsTest(){
        assertTrue(pos.equals(new Position(x,y)));
    }
    @Test
    public void positionNotEqualsTest(){
        assertFalse(pos.equals(new Wall(x,y)));
        assertFalse(pos.equals(null));
    }
}
