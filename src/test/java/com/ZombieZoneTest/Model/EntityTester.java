package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.MockHandler;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EntityTester {
    private Entity entity;
    private final Integer TIMETOMOVEDOZOMBIENORMAL = 1000/2;
    @BeforeEach
    public void SetUp(){
        entity = Mockito.spy(new Soldier(2,2));
    }
    @Test
    public void getLifeTest(){
        assertEquals(3, entity.getLife());
    }
    @Test
    public void moveUpTest(){
        entity.moveUp();
        verify(entity,times(1)).setPosition(new Position(2, 1));
    }
    @Test
    public void moveDownTest(){
        entity.moveDown();
        verify(entity,times(1)).setPosition(new Position(2, 3));
    }
    @Test
    public void moveLeftTest(){
        entity.moveLeft();
        verify(entity,times(1)).setPosition(new Position(1, 2));
    }
    @Test
    public void moveRightTest(){
        entity.moveRight();
        verify(entity,times(1)).setPosition(new Position(3, 2));
    }
    @Test
    public void hitTest(){
        entity.hit();
        assertFalse(entity.isDead());
        entity.hit();
        assertFalse(entity.isDead());
        entity.hit();
        assertTrue(entity.isDead());
    }

}
