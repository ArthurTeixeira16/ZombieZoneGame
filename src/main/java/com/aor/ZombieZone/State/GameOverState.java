package com.aor.ZombieZone.State;

import com.aor.ZombieZone.Controller.GameOverController;
import com.aor.ZombieZone.Model.GameOver;
import com.aor.ZombieZone.View.GameOverView;
import com.googlecode.lanterna.screen.Screen;

import javax.swing.*;
import java.io.IOException;

public class GameOverState implements State{
    private GameOverController gameOverController;
    private GameOver gameOver;
    private GameOverView gameOverView;

    public GameOverState(Screen screen){
        this.gameOver = new GameOver(30,21);
        this.gameOverView = new GameOverView(gameOver);

        gameOverController = new GameOverController(gameOver,gameOverView,screen);

    }

    public GameOverController getGameOverController() {
        return gameOverController;
    }

    @Override
    public void run() throws IOException {
        getGameOverController().setRunningTrue();
        getGameOverController().run();


    }

    @Override
    public void handleInput() throws IOException {
        getGameOverController().handleInput();

    }
}
