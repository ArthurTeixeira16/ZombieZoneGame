package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

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
    public void testSetHud(){
        assertEquals(hudMock,game.getHud());
    }
    @Test
    void testAddListener() {
        GameListener gameListener = mock(GameListener.class);
        game.addListener(gameListener);

        assertTrue(game.getGameListeners().contains(gameListener));
    }
    @Test
    public void testGetListeners(){
        GameListener gameListener = mock(GameListener.class);
        game.addListener(gameListener);

        List<GameListener> listeners = game.getGameListeners();

        assertNotNull(listeners);
        assertTrue(listeners.contains(gameListener));
    }

    @Test
    void testAddScoreObserver() {
        ScoreObserver scoreObserver = mock(ScoreObserver.class);
        game.addScoreObserver(scoreObserver);

        assertTrue(game.getScoreObservers().contains(scoreObserver));
    }
    @Test
    public void testGetObservers(){
        ScoreObserver scoreObserver = mock(ScoreObserver.class);
        game.addScoreObserver(scoreObserver);

        List<ScoreObserver> observers = game.getScoreObservers();

        assertNotNull(observers);
        assertTrue(observers.contains(scoreObserver));
    }
    @Test
    void testCanHit_WhenEnoughTimeHasPassed() {
        long currentTime = System.currentTimeMillis();
        game.canHit(currentTime - game.getSafeTime());

        boolean result = game.canHit(currentTime);

        assertTrue(result);
    }

    @Test
    void testCanHit_WhenNotEnoughTimeHasPassed() {
        long currentTime = System.currentTimeMillis();
        game.canHit(currentTime);

        boolean result = game.canHit(currentTime + 100);

        assertFalse(result);
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
        assertTrue(game.canMoveTo(new Position(7, 4)));
    }
    @Test
    void testShoot() {
        Position direction = new Position(1, 0);

        game.shoot(direction);

        assertEquals(1, game.getProjectiles().size());
        assertEquals(new Position(16, 10), game.getProjectiles().getFirst().getPosition());
    }
    @Test
    void testCheckDamage() {
        Enemy zombieMock = mock(Enemy.class);
        when(zombieMock.getPosition()).thenReturn(game.getSoldier().getPosition());
        game.getZombies().add(zombieMock);

        long currentTime = System.currentTimeMillis();
        game.checkDamage(currentTime);

        assertEquals(2, game.getSoldier().getLife());
    }
    @Test
    void testCheckBulletsCollisions() {
        Projectile bulletMock = mock(Projectile.class);
        Enemy zombieMock = mock(Enemy.class);
        Wall wallMock = mock(Wall.class);

        when(bulletMock.getPosition()).thenReturn(new Position(5, 5));
        when(zombieMock.getPosition()).thenReturn(new Position(5, 5));
        when(wallMock.getPosition()).thenReturn(new Position(5, 5));

        game.getProjectiles().add(bulletMock);
        game.getZombies().add(zombieMock);
        game.getWalls().add(wallMock);

        game.checkBulletsCollisions();

        verify(bulletMock, times(2)).destroy();
        verify(zombieMock, times(1)).hit();
    }
    @Test
    public void testUpdate() throws IOException {
        final long currentTime = System.currentTimeMillis();
        long deltaTime = 100;

        game.getSoldier().setPosition(new Position(5, 5));
        game.getZombies().getFirst().setPosition(new Position(6, 5));

        assertDoesNotThrow(() -> game.update(deltaTime, currentTime));

        game.update(deltaTime, currentTime);
        game.shoot(new Position(1, 0));
        assertEquals(1, game.getProjectiles().size());

        long newCurrent = System.currentTimeMillis();
        newCurrent = System.currentTimeMillis();
        game.update(deltaTime, newCurrent);

        assertFalse(game.getSoldier().isDead());
        assertFalse(game.getZombies().isEmpty());
        assertTrue(game.getProjectiles().isEmpty());
    }
    @Test
    public void testGetSoldier() {
        Soldier soldier = game.getSoldier();
        assertNotNull(soldier);
    }

    @Test
    public void testGetArena() {
        Arena arena = game.getArena();
        assertNotNull(arena);
    }

    @Test
    public void testGetZombies() {
        List<Enemy> zombies = game.getZombies();
        assertNotNull(zombies);
        assertFalse(zombies.isEmpty());
    }

    @Test
    public void testGetWalls() {
        List<Wall> walls = game.getWalls();
        assertNotNull(walls);
        assertFalse(walls.isEmpty());
    }

    @Test
    public void testGetHud() {
        Hud hud = game.getHud();
        assertNotNull(hud);
    }

    @Test
    public void testGetProjectiles() {
        List<Projectile> projectiles = game.getProjectiles();
        assertNotNull(projectiles);
        assertTrue(projectiles.isEmpty());
    }

    @Test
    public void testGetScore() {
        Score score = game.getScore();
        assertNotNull(score);
    }

    @Test
    public void testGetSafeTime() {
        int safeTime = game.getSafeTime();
        assertEquals(3000, safeTime);
    }
}








