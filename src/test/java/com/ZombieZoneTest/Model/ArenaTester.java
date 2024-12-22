package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.Arena;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArenaTester {
    private Arena arena;
    private final int WIDTH = 20;
    private final int HEIGHT = 20;
    @BeforeEach
    public void setUp() {
        arena = new Arena(WIDTH, HEIGHT);
    }
    @Test
    public void arenaTest() {
        assertEquals(WIDTH, arena.getWidth());
        assertEquals(HEIGHT, arena.getHeight());
    }
    @Test
    public void arenaTest2() {
        char[][] tiles = arena.getTiles();
        TextColor[][] colors = arena.getColors();

        assertNotNull(tiles);
        assertNotNull(colors);

        assertEquals(HEIGHT, tiles.length);
        assertEquals(WIDTH, tiles[0].length);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                assertEquals(' ', tiles[y][x]);
                assertNotNull(colors[y][x]);
            }
        }
    }

    @Test
    public void testRandomGrayShade() {
        TextColor[][] colors = arena.getColors();

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                TextColor color = colors[y][x];
                String hexColor = color.toString(); // TextColor.Factory creates from hex.
                String temp = hexColor.substring(5,8);
                int temp_int = Integer.parseInt(temp);
                assertTrue(temp_int >= 180 && temp_int <= 230 );
            }
        }
    }
}
