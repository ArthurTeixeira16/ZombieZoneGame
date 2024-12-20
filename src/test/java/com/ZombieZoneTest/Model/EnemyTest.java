package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
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
        //Estou usando ZombieNormal apenas
        // para testar o track e o TraceToHero,
        // e assim n tenho de voltar a fazer nos
        // outros filhos porque todos herdam o
        // mesmo metodo dito.
        enemy = Mockito.spy(new ZombieNormal(4,2));
        //Sinto-me orgulhoso te ter aprendido o when, list.of e etc... são uma belissima combinação <3
        Mockito.when(game.getArena()).thenReturn(arena);
        Mockito.when(game.getArena().getWidth()).thenReturn(5);
        Mockito.when(game.getArena().getHeight()).thenReturn(5);
        Position soldierPosition = new Position(4, 4);
        Mockito.when(soldier.getPosition()).thenReturn(soldierPosition);
        List<Position> positionsWall = List.of(
                new Position(2,2),
                new Position(4,3)
        );
        Mockito.when(game.getPositionsWalls()).thenReturn(positionsWall);
        List<Position> positionsZombie = List.of(
                (new Position(enemy.getPosition().getX(),enemy.getPosition().getY())),
                (new Position(4,4))
        );
        Mockito.when(game.getPositionsZombies()).thenReturn(positionsZombie);
    }
    @Test
    public void updateZombieWalkTest(){
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
        int[][] temp_places = {
                {0, 0, 0, 0 , 0},
                {0, 0, 0, 0 , 0},
                {0, 0, 2, 0 , 0},
                {0, 0, 0, 0 , 0},
                {0, 0, 2, 2 , 0},
        };
        int[][] places = enemy.getPlaces(soldier,game);
        assertTrue(Arrays.deepEquals(places, temp_places));
    }
    @Test
    public void TraceToHeroTest_while_break(){
        enemy.track(soldier,game);
        verify(enemy,times(1)).setPosition(new Position(3, 2));
        enemy.track(soldier,game);
        verify(enemy,times(1)).setPosition(new Position(3, 3));
        enemy.track(soldier,game);
        verify(enemy,times(1)).setPosition(new Position(3, 4));
        enemy.track(soldier,game);
        verify(enemy,times(1)).setPosition(new Position(4, 4));
    }
    @Test
    public void TraceToHero_soldierBlocked(){
        Position soldierPosition = new Position(0, 0);
        Mockito.when(soldier.getPosition()).thenReturn(soldierPosition);
        List<Position> positionsWall = List.of(
                new Position(1,0),
                new Position(0,1),
                new Position(1,1)
        );
        Mockito.when(game.getPositionsWalls()).thenReturn(positionsWall);
        enemy.track(soldier,game);
        Mockito.verify( enemy , times(0)).setPosition(soldierPosition);
    }

}
