package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Model.Arena;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.times;

public class ArenaViewTest {
    private Arena arena;
    private TextGraphics textGraphics;
    private ArenaView arenaView;
    public void SetUp1(){
        arena = new Arena(30,20);
        arenaView = new ArenaView(arena);
        textGraphics = Mockito.mock(TextGraphics.class);
        arenaView.render(textGraphics);
    }
    @Test
    public void renderTest1(){
        SetUp1();
        for( int y = 0; y< arena.getHeight(); y++){
            for( int x = 0; x< arena.getWidth(); x++){
                Mockito.verify(textGraphics, atMost((arena.getWidth()) * arena.getHeight())).setBackgroundColor(arena.getColors()[y][x]);
                Mockito.verify(textGraphics, atMost((arena.getWidth()) * arena.getHeight())).setForegroundColor(arena.getColors()[y][x]);
                Mockito.verify(textGraphics, atMost((arena.getWidth()) * arena.getHeight())).putString(x,y,String.valueOf(arena.getTiles()[y][x]));
            }
        }
    }
    public void SetUp2(){
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
            arenaView.render(textGraphics);
    }
    public void RenderTest2(){
        SetUp2();
        for( int y = 0; y< arena.getHeight(); y++){
            for( int x = 0; x< arena.getWidth(); x++){
                Mockito.verify(textGraphics, times(1)).setForegroundColor(arena.getColors()[y][x]);
                Mockito.verify(textGraphics, times(1)).setForegroundColor(arena.getColors()[y][x]);
                Mockito.verify(textGraphics, times(1)).putString(x,y,String.valueOf(arena.getTiles()[y][x]));
            }
        }
    }
}
