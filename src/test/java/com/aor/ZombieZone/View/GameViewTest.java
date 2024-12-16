package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Model.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class GameViewTest {
    private GameView gameView;
    private ArenaView arenaView;
    private HudView hudView;
    private Game game;
    private TextGraphics textGraphics;

    public void SetUp1(){
        game = new Game();
        arenaView = Mockito.mock(ArenaView.class);
        gameView = new GameView(arenaView , game);
        Hud hud = new Hud(game);
        game.setHud(hud);
        hudView = new HudView(hud);
        gameView.setHudView(hudView);
        textGraphics = Mockito.mock(TextGraphics.class);
        gameView.render(textGraphics);
    }
    @Test
    public void renderTest1(){
        SetUp1();
        Mockito.verify(arenaView, times(1)).render(textGraphics);
        Mockito.verify(hudView, times(1)).render(textGraphics);
        Mockito.verify(game, times(1)).getSoldier().draw(textGraphics);
        for(Wall wall : game.getWalls()){
            Mockito.verify(wall, times(game.getWalls().size())).draw(textGraphics);
        }
        for(Projectile projectile : game.getProjectiles()){
            Mockito.verify(projectile, times(game.getProjectiles().size())).draw(textGraphics);
        }
        for(Enemy enemy : game.getZombies()){
            Mockito.verify(enemy , times(game.getZombies().size())).draw(textGraphics);
        }
    }
    /*public void SetUp2(){

    }*/
}
