package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Model.*;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;

public class ArenaViewerTester {
    private Arena arena;
    private ArenaView arenaView;
    private Soldier soldier;
    private List<Zombie> zombies;
    private List<Wall> walls;
    private HudView hudView;
    private Hud hud;
    private GameView gameView;
    private TextGraphics textGraphics;

    @BeforeEach
    public void setUp() {
        arena = new Arena(30,20);
        arenaView = new ArenaView(arena);
        soldier = new Soldier(15,10);
        zombies = Arrays.asList(new Zombie(15,15), new Zombie(20,20));
        walls = Arrays.asList(new Wall(0,0) , new Wall(10,10), new  Wall(25,5));
        hud = new Hud(soldier,arena);
        hudView= new HudView(hud);
        gameView = new GameView(arenaView, soldier, zombies,walls,hudView);
        textGraphics = Mockito.mock(TextGraphics.class);
        gameView.render(textGraphics);
    }

    @Test
    public void drawWalls() {
        Mockito.verify(textGraphics, times(1)).putString(Mockito.eq(new TerminalPosition(0, 0)), Mockito.eq("█"));
        Mockito.verify(textGraphics, times(1)).putString(Mockito.eq(new TerminalPosition(10, 10)), Mockito.eq("█"));
        Mockito.verify(textGraphics, times(1)).putString(Mockito.eq(new TerminalPosition(25, 5)), Mockito.eq("█"));

        Mockito.verify(textGraphics, times(3)).putString(Mockito.any(TerminalPosition.class), Mockito.eq("█"));
    }

   @Test
    void drawZombies() throws IOException {

        Mockito.verify(textGraphics, times(1)).putString(Mockito.eq(new TerminalPosition(15, 15)), Mockito.eq("Z"));
        Mockito.verify(textGraphics, times(1)).putString(Mockito.eq(new TerminalPosition(20, 20)), Mockito.eq("Z"));
        Mockito.verify(textGraphics, times(2)).putString(Mockito.any(TerminalPosition.class), Mockito.eq("Z"));
    }
    /*@Test
    void drawSoldier() throws IOException {

        Mockito.verify(textGraphics, times(1)).putString(Mockito.eq(new TerminalPosition(15, 10)), Mockito.eq("@"));
        Mockito.verify(textGraphics, times(1)).putString(Mockito.any(TerminalPosition.class), Mockito.eq("@"));
    }*/



}
