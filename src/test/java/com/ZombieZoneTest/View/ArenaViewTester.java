package com.ZombieZoneTest.View;

import com.aor.ZombieZone.Model.Arena;
import com.aor.ZombieZone.View.ArenaView;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class ArenaViewTester {
    private Arena arena;
    private TextGraphics textGraphics;
    private ArenaView arenaView;
    @BeforeEach
    public void SetUp(){
            arena = Mockito.mock(Arena.class);
            TextColor[][] colors = new TextColor[][]{
                    {TextColor.ANSI.RED, TextColor.ANSI.GREEN},
                    {TextColor.ANSI.BLUE, TextColor.ANSI.YELLOW}
            };
            char[][] tiles = new char[][]{
                    {'A', 'B'},
                    {'C', 'D'}
            };
            Mockito.when(arena.getWidth()).thenReturn(2);
            Mockito.when(arena.getHeight()).thenReturn(2);
            Mockito.when(arena.getColors()).thenReturn(colors);
            Mockito.when(arena.getTiles()).thenReturn(tiles);
            arenaView = new ArenaView(arena);
            textGraphics = Mockito.mock(TextGraphics.class);
    }
    @Test
    public void RenderTest(){
        arenaView.render(textGraphics);
        for( int y = 0; y< arena.getHeight(); y++){
            for( int x = 0; x< arena.getWidth(); x++){
                Mockito.verify(textGraphics, times(1)).setForegroundColor(arena.getColors()[y][x]);
                Mockito.verify(textGraphics, times(1)).setForegroundColor(arena.getColors()[y][x]);
                Mockito.verify(textGraphics, times(1)).putString(x,y,String.valueOf(arena.getTiles()[y][x]));
            }
        }
    }
}
