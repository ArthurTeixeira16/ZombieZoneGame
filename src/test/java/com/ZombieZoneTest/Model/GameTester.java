package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameTester {
    private Game game;
    private Hud hudMock;
    private WallCreator wallCreatorMock;
    private Spawn spawnMock;

    @BeforeEach
    public void setUp() {
        game = new Game();
        hudMock = mock(Hud.class);
        wallCreatorMock = mock(WallCreator.class);
        spawnMock = mock(Spawn.class);
        game.setHud(hudMock);
    }

    @Test
    public void testResetGame(){
        game.resetGame();

        assertNotNull(game.getSoldier());
        assertNotNull(game.getArena());
        assertNotNull(game.getWalls());
        assertNotNull(game.getZombies());
        assertNotNull(game.getProjectiles());
        verify(hudMock, times(1)).resetHud();
    }
    @Test
    void testCanShoot() {
        long currentTime = System.currentTimeMillis();
        assertTrue(game.canShoot(currentTime + 1000));
        assertFalse(game.canShoot(currentTime));
    }
    @Test
    void testCanMoveTo() {
        Position position = new Position(5, 5);
        Wall wallMock = mock(Wall.class);
        when(wallMock.getPosition()).thenReturn(position);
        game.getWalls().add(wallMock);

        assertFalse(game.canMoveTo(position));

        Enemy zombieMock = mock(Enemy.class);
        when(zombieMock.getPosition()).thenReturn(position);
        game.getZombies().add(zombieMock);

        assertFalse(game.canMoveTo(position));
        assertTrue(game.canMoveTo(new Position(1, 1)));
    }



}
