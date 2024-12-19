package com.ZombieZoneTest.Model;
import com.aor.ZombieZone.Model.Wall;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class WallTester {
    private Wall wall;
    private TextGraphics textGraphics;
    private final TextColor FOREGROUNDCOLOR = TextColor.Factory.fromString("#4B2E1F");

    @BeforeEach
    public void setUp() {
        wall = new Wall(3,3);
        textGraphics = Mockito.mock(TextGraphics.class);
    }
    @Test
    public void drawTest(){
        wall.draw(textGraphics);
        verify(textGraphics, times(1)).setForegroundColor(FOREGROUNDCOLOR);
        verify(textGraphics, times(1)).putString( new TerminalPosition( wall.getPosition().getX() , wall.getPosition().getY()) , "█");
    }
    @Test
    public void equalsTest(){
        Wall wall2 = new Wall(3,4);
        Assertions.assertFalse(wall.equals(wall2));
        wall2 = new Wall(4,3);
        Assertions.assertFalse(wall.equals(wall2));
        wall2 = new Wall(3,3);
        Assertions.assertTrue(wall.equals(wall2));
    }

}
