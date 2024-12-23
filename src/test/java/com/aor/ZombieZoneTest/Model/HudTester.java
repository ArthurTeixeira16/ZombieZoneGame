package com.aor.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HudTester {
    private Hud hud;
    private Game game;
    private Soldier soldier;
    private Arena arena;
    @BeforeEach
    public void setUp() {
        game = Mockito.mock(Game.class);
        soldier = Mockito.mock(Soldier.class);
        arena = Mockito.mock(Arena.class);
        Mockito.when(game.getArena()).thenReturn(arena);
        Mockito.when(game.getSoldier()).thenReturn(soldier);
        Mockito.when(game.getArena().getWidth()).thenReturn(100);
        Mockito.when(game.getArena().getHeight()).thenReturn(500);
        hud = Mockito.spy(new Hud(game));

    }
    @Test
    public void ScoreGetterTest() {
        assertEquals(new Score().getScore(),hud.getScore().getScore());
    }
    @Test
    public void ScoreSetterTest() {
        Score score = new Score();
        score.addScore();
        hud.setScore(score);
        assertEquals( 1 ,hud.getScore().getScore());
    }
    @Test
    public void RoundGetterTest() {
        assertEquals(new Round().getRound(),hud.getRound().getRound());
    }
    @Test
    public void RoundSetterTest() {
        Round round = new Round();
        round.nextRound();
        round.nextRound();
        round.nextRound();
        hud.setRound(round);
        assertEquals( 4 ,hud.getRound().getRound());
    }
    @Test
    public void getHeightTest(){
        hud.getHeight();
        assertEquals(500,hud.getHeight());
    }
    @Test
    public void getWidthTest(){
        hud.getWidth();
        assertEquals(100,hud.getWidth());
    }
    @Test
    public void resetHudTest(){
        Round round = new Round();
        round.nextRound();
        round.nextRound();
        round.nextRound();
        hud.setRound(round);
        Score score = new Score();
        score.addScore();
        hud.setScore(score);
        hud.resetHud();
        assertEquals( 0 ,hud.getScore().getScore());
        assertEquals( 1 ,hud.getRound().getRound());
    }
    @Test
    public void getSoldierTest(){
        hud.getSoldier();
        Mockito.verify(game,Mockito.times(1)).getSoldier();
    }

}
