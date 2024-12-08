package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.Wall;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class WallTester {
    private final int width = 40; // Limite horizontal
    private final int height = 40; // Limite vertical
    //Preferimos criar setUps diferentes para cada teste, para facilitar a mudança de cada setUp
    private Wall setUp1() {
        return new Wall(10, 51); // X válido, Y fora do limite
    }
    private Wall setUp2() {
        return new Wall(-1, 5); // X fora do limite, Y válido
    }
    private Wall setUp3() {
        return new Wall(-5, -5); // X e Y fora dos limites
    }
    private Wall setUp4() {
        return new Wall(10, 5); // X e Y válidos
    }
    @Test
    void testWallPosition1() {
        Wall wall = setUp1();
        assertTrue(wall.getPosition().getX() >= 0 && wall.getPosition().getX() < width,
                "Wall X position is out of bounds");
        assertFalse(wall.getPosition().getY() >= 0 && wall.getPosition().getY() < height,
                "Wall Y position is out of bounds");
    }

    @Test
    void testWallPosition2() {
        Wall wall = setUp2();
        assertFalse(wall.getPosition().getX() >= 0 && wall.getPosition().getX() < width,
                "Wall X position is out of bounds");
        assertTrue(wall.getPosition().getY() >= 0 && wall.getPosition().getY() < height,
                "Wall Y position is out of bounds");
    }

    @Test
    void testWallPosition3() {
        Wall wall = setUp3();
        assertFalse(wall.getPosition().getX() >= 0 && wall.getPosition().getX() < width,
                "Wall X position is out of bounds");
        assertFalse(wall.getPosition().getY() >= 0 && wall.getPosition().getY() < height,
                "Wall Y position is out of bounds");
    }

  /*  @Test
    void testDrawMethod() {
        Wall wall = setUp4();
        TextGraphics graphics = Mockito.mock(TextGraphics.class);
        wall.draw(graphics);
        Mockito.verify(graphics).setForegroundColor(Mockito.eq(TextColor.Factory.fromString("#38291A")));
        Mockito.verify(graphics).putString(Mockito.eq(new TerminalPosition(10, 5)), Mockito.eq("█"));
    } */
}