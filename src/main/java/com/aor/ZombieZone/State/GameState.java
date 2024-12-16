package com.aor.ZombieZone.State;

import com.aor.ZombieZone.Controller.GameController;
import com.aor.ZombieZone.Model.Arena;
import com.aor.ZombieZone.Model.Game;
import com.aor.ZombieZone.Model.Hud;
import com.aor.ZombieZone.View.ArenaView;
import com.aor.ZombieZone.View.GameView;
import com.aor.ZombieZone.View.HudView;
import com.googlecode.lanterna.screen.Screen;

import javax.swing.*;
import java.io.IOException;

public class GameState implements State{
    private GameController gameController;
    private GameView gameView;
    private Game game;
    private Hud hud;

    public GameState(Screen screen){
        this.game = new Game();
        this.hud = new Hud(game);
        HudView hudView = new HudView(hud);
        game.setHud(hud);
        Arena arena = new Arena(30,20);
        ArenaView arenaView = new ArenaView(arena);
        gameView = new GameView( arenaView , game);
        gameView.setHudView(hudView);
        gameController = new GameController( game , gameView , screen);
    }

    public GameController getController() {
        return gameController;
    }

    @Override
    public void run() throws IOException {
     getController().setRunningTrue();
     getController().run();
    }

    @Override
    public void handleInput(KeyStroke key) throws IOException {
        getController().handleInput();
    }
}
