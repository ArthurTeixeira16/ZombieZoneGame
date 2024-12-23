package com.aor.ZombieZoneTest.View;

import com.aor.ZombieZone.Model.*;
import com.aor.ZombieZone.View.ArenaView;
import com.aor.ZombieZone.View.GameView;
import com.aor.ZombieZone.View.HudView;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class GameViewTester {
    private GameView gameView;
    private ArenaView arenaView;
    private HudView hudView;
    private Game game;
    private TextGraphics textGraphics;

    @BeforeEach
    public void setUp() {
        textGraphics = mock(TextGraphics.class);
        game = mock(Game.class);
        arenaView = mock(ArenaView.class);
        hudView = mock(HudView.class);
        gameView = new GameView(arenaView, game);
        gameView.setHudView(hudView);
    }

    @Test
    public void testRenderWalls_CallsDrawForEachWall() {
        List<Wall> walls = List.of(mock(Wall.class), mock(Wall.class));
        when(game.getWalls()).thenReturn(walls);

        gameView.renderWalls(textGraphics);

        for (Wall wall : walls) {
            verify(wall, times(1)).draw(textGraphics);
        }
    }

    @Test
    public void testRenderWalls_HandlesEmptyWallList() {
        when(game.getWalls()).thenReturn(List.of());

        gameView.renderWalls(textGraphics);

        verifyNoInteractions(textGraphics);
    }

    @Test
    public void testRenderEnemies_CallsDrawForEachZombie() {
        List<Enemy> zombies = List.of(mock(Enemy.class), mock(Enemy.class));
        when(game.getZombies()).thenReturn(zombies);

        gameView.renderEnemies(textGraphics);

        for (Enemy zombie : zombies) {
            verify(zombie, times(1)).draw(textGraphics);
        }
    }

    @Test
    public void testRenderWalls_HandlesEmptyEnemyList() {
        when(game.getZombies()).thenReturn(List.of());

        gameView.renderEnemies(textGraphics);

        verifyNoInteractions(textGraphics);
    }

    @Test
    public void testRenderProjectiles_CallsDrawForEachProjectile() {
        List<Projectile> projectiles = List.of(mock(Projectile.class), mock(Projectile.class));
        when(game.getProjectiles()).thenReturn(projectiles);

        gameView.renderProjectiles(textGraphics);

        for (Projectile projectile : projectiles) {
            verify(projectile, times(1)).draw(textGraphics);
        }
    }

    @Test
    public void testRenderProjectiles_HandlesEmptyList() {
        when(game.getProjectiles()).thenReturn(null);

        gameView.renderProjectiles(textGraphics);

        verifyNoInteractions(textGraphics);
    }

    @Test
    public void testRender_CallsAllRenderMethods() {
        Soldier soldier = mock(Soldier.class);
        when(game.getSoldier()).thenReturn(soldier);

        gameView.render(textGraphics);

        verify(arenaView, times(1)).render(textGraphics);
        verify(hudView, times(1)).render(textGraphics);
        verify(soldier, times(1)).draw(textGraphics);
        verify(game, times(1)).getWalls();
        verify(game, times(1)).getZombies();
        verify(game, times(2)).getProjectiles();
    }
}
