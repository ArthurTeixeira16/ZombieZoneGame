package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnemyTest {
    private ZombieNormal enemy;
    private Soldier soldier;
    private Game game;
    private Arena arena;
    private final Integer TIMETOMOVEDOZOMBIENORMAL = 1000/2;
    @BeforeEach
    public void SetUp(){
        soldier = Mockito.mock(Soldier.class);
        game = Mockito.mock(Game.class);
        arena = Mockito.mock(Arena.class);
    }
    public void SetUpForTrack(){
        //Estou usando ZombieNormal apenas
        // para testar o track e o TraceToHero,
        // e assim n tenho de voltar a fazer nos
        // outros filhos porque todos herdam o
        // mesmo metodo dito.
        enemy = Mockito.spy(new ZombieNormal(5,5));
    }

    @Test
    public void updateZombieWalkTest(){
        SetUpForTrack();
        //Precisamos de que exatamente a cada TIMETOMOVE ele mova, para seguir o tempo dado.
        Mockito.doNothing().when(enemy).track(soldier,game);
        enemy.updateZombieWalk(soldier, game, TIMETOMOVEDOZOMBIENORMAL/2 );
        Mockito.verify(enemy,times(0)).track(soldier,game);
        enemy.updateZombieWalk(soldier,game, (TIMETOMOVEDOZOMBIENORMAL/2) - 1);
        Mockito.verify(enemy,times(0)).track(soldier,game);
        enemy.updateZombieWalk(soldier,game,   1);
        Mockito.verify(enemy,times(1)).track(soldier,game);
        enemy.updateZombieWalk(soldier, game, TIMETOMOVEDOZOMBIENORMAL*2);
        Mockito.verify(enemy,times(3)).track(soldier,game);
    }
    @Test
    public void TrackTest(){
        //Sinto-me orgulhoso te ter aprendido o when, list.of e etc... são uma belissima combinação <3
        enemy = Mockito.spy(new ZombieNormal(2,2));
        Mockito.when(game.getArena()).thenReturn(arena);
        Mockito.when(game.getArena().getWidth()).thenReturn(4);
        Mockito.when(game.getArena().getHeight()).thenReturn(4);
        List<Position> positionsWall = List.of(
                new Position(0,0),
                new Position(0,1)
        );
        Mockito.when(game.getPositionsWalls()).thenReturn(positionsWall);
        Position positionSoldier = new Position(1,0);
        Mockito.when(soldier.getPosition()).thenReturn(positionSoldier);
        List<Position> positionsZombie = List.of(
                new Position(2,2)
        );
        Mockito.when(game.getPositionsZombies()).thenReturn(positionsZombie);
        int[][] temp_places = {
                {2, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0}
        };
        assertTrue(Arrays.deepEquals(enemy.getPlaces(soldier, game), temp_places));
    }
    @Test
    public void TraceToHeroTest(){
        enemy = Mockito.spy(new ZombieNormal(2, 2));
        soldier = Mockito.mock(Soldier.class);
        game = Mockito.mock(Game.class);
        arena = Mockito.mock(Arena.class);
        Mockito.when(game.getArena()).thenReturn(arena);
        Mockito.when(game.getArena().getWidth()).thenReturn(5);
        Mockito.when(game.getArena().getHeight()).thenReturn(5);
        Position soldierPosition = new Position(4, 4);
        Mockito.when(soldier.getPosition()).thenReturn(soldierPosition);
        List<Position> positionsWall = List.of(
                new Position(1, 1), new Position(3, 3)
        );
        Mockito.when(game.getPositionsWalls()).thenReturn(positionsWall);
        List<Position> positionsZombie = List.of(
                new Position(2, 2)
        );
        Mockito.when(game.getPositionsZombies()).thenReturn(positionsZombie);
        int[][] places = new int[5][5];
        for (int[] place : places) {
            Arrays.fill(place, 0);
        }
        places[1][1] = 2;
        places[3][3] = 2;
        enemy.TraceToHero(enemy.getPosition(), soldier.getPosition(), places, game);
        verify(enemy,times(1)).setPosition(new Position(3, 2));
        enemy.TraceToHero(enemy.getPosition(), soldier.getPosition(), places, game);
        verify(enemy,times(1)).setPosition(new Position(4, 2));
        enemy.TraceToHero(enemy.getPosition(), soldier.getPosition(), places, game);
        verify(enemy,times(1)).setPosition(new Position(4, 3));
        enemy.TraceToHero(enemy.getPosition(), soldier.getPosition(), places, game);
        verify(enemy,times(1)).setPosition(new Position(4, 4));
    }
    @Test
    public void moveUpTest(){
        enemy = Mockito.spy(new ZombieNormal(2,2));
        enemy.moveUp();
        verify(enemy,times(1)).setPosition(new Position(2, 1));
    }
    @Test
    public void moveDownTest(){
        enemy = Mockito.spy(new ZombieNormal(2,2));
        enemy.moveDown();
        verify(enemy,times(1)).setPosition(new Position(2, 3));
    }
    @Test
    public void moveLeftTest(){
        enemy = Mockito.spy(new ZombieNormal(2,2));
        enemy.moveLeft();
        verify(enemy,times(1)).setPosition(new Position(1, 2));
    }
    @Test
    public void moveRightTest(){
        enemy = Mockito.spy(new ZombieNormal(2,2));
        enemy.moveRight();
        verify(enemy,times(1)).setPosition(new Position(3, 2));
    }
    @Test
    public void hitTest(){
        enemy = Mockito.spy(new ZombieNormal(2,2));
        enemy.hit();
        assertFalse(enemy.isDead());
        enemy.hit();
        assertTrue(enemy.isDead());
    }

}
