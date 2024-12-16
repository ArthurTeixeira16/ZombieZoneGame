package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Model.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
 // mocks do wall , zombie , hudview
 // arena view, bullets , game
public class GameViewTest {
    private GameView gameView;
    private ArenaView arenaView;
    private Arena arena;
    private HudView hudView;
    private Game game;
    private TextGraphics textGraphics;
    @BeforeEach
    public void SetUp(){
        textGraphics = Mockito.mock(TextGraphics.class);
        game = Mockito.mock(Game.class);
        List<Wall> walls = List.of(
                Mockito.mock(Wall.class),
                Mockito.mock(Wall.class),
                Mockito.mock(Wall.class),
                Mockito.mock(Wall.class),
                Mockito.mock(Wall.class),
                Mockito.mock(Wall.class)
        );
        List<Enemy> enemies = List.of(
                Mockito.mock(ZombieNormal.class),
                Mockito.mock(ZombieHeavy.class),
                Mockito.mock(ZombieSpeed.class),
                Mockito.mock(Enemy.class),
                Mockito.mock(ZombieHeavy.class),
                Mockito.mock(Enemy.class)
        );
        List<Projectile> projectiles = List.of(
                Mockito.mock(Projectile.class),
                Mockito.mock(Projectile.class),
                Mockito.mock(Projectile.class),
                Mockito.mock(Projectile.class),
                Mockito.mock(Projectile.class),
                Mockito.mock(Projectile.class)
        );
        Soldier soldier = Mockito.mock(Soldier.class);
        arenaView = Mockito.mock(ArenaView.class);
        hudView = Mockito.mock(HudView.class);
        gameView = new GameView(arenaView,game);
        gameView.setHudView(hudView);

        Mockito.when(game.getSoldier()).thenReturn(soldier);
        Mockito.when(game.getWalls()).thenReturn(walls);
        Mockito.when(game.getZombies()).thenReturn(enemies);
        Mockito.when(game.getProjectiles()).thenReturn(projectiles);
        gameView.render(textGraphics);
    }
    @Test
    public void renderTest(){
        Mockito.verify(arenaView, times(1)).render(textGraphics);
        Mockito.verify(hudView, times(1)).render(textGraphics);

        Soldier soldier = game.getSoldier();
        Mockito.verify(soldier, times(1)).draw(textGraphics);

        for(Wall wall : game.getWalls()){
            Mockito.verify(wall, times(1)).draw(textGraphics);
        }
        for (Enemy zombie : game.getZombies()) {
            Mockito.verify(zombie, times(1)).draw(textGraphics);
        }
        for (Projectile projectile : game.getProjectiles()) {
            Mockito.verify(projectile, times(1)).draw(textGraphics);
        }

    }
}
