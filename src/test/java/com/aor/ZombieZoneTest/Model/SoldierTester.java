package com.aor.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.Position;
import com.aor.ZombieZone.Model.Soldier;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SoldierTester {
    private Soldier soldier;
    private TextGraphics textGraphics;
    private final TextColor FOREGROUNDCOLOR = TextColor.Factory.fromString("#000000");

    @BeforeEach
    public void setUp() {
        soldier = Mockito.spy( new Soldier(2,2));
        textGraphics = Mockito.spy(TextGraphics.class);
    }
    
    @Test
    public void drawTest(){
        soldier.draw(textGraphics);
        Mockito.verify(textGraphics , times(1)).setForegroundColor(FOREGROUNDCOLOR);
        Mockito.verify(textGraphics , times(1)).putString(new TerminalPosition(soldier.getPosition().getX(), soldier.getPosition().getY()),"@");
    }

    @Test
    public void getLifeTest(){
        assertEquals(3, soldier.getLife());
    }
    @Test
    public void moveUpTest(){
        soldier.moveUp();
        verify(soldier,times(1)).setPosition(new Position(2, 1));
    }
    @Test
    public void moveDownTest(){
        soldier.moveDown();
        verify(soldier,times(1)).setPosition(new Position(2, 3));
    }
    @Test
    public void moveLeftTest(){
        soldier.moveLeft();
        verify(soldier,times(1)).setPosition(new Position(1, 2));
    }
    @Test
    public void moveRightTest(){
        soldier.moveRight();
        verify(soldier,times(1)).setPosition(new Position(3, 2));
    }
    @Test
    public void hitTest(){
        soldier.hit();
        assertFalse(soldier.isDead());
        soldier.hit();
        assertFalse(soldier.isDead());
        soldier.hit();
        assertTrue(soldier.isDead());
    }
}
