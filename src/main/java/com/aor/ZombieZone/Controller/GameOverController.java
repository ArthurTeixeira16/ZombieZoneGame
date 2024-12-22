package com.aor.ZombieZone.Controller;

import com.aor.ZombieZone.Model.GameOver;
import com.aor.ZombieZone.State.StateObserver;
import com.aor.ZombieZone.View.GameOverView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameOverController {
    private GameOver gameOver;
    private GameOverView gameOverView;
    private Screen screen;
    private boolean running = true;
    private List<StateObserver> observers = new ArrayList<>();

    public GameOverController(GameOver gameOver, GameOverView gameOverView, Screen screen) {
        this.gameOver = gameOver;
        this.gameOverView = gameOverView;
        this.screen = screen;
    }

    public List<StateObserver> getObservers() {
        return observers;
    }

    public boolean isRunning() {
        return running;
    }

    public void addObserver(StateObserver observer) {
        observers.add(observer);
    }
    public void setRunningTrue() {
        running = true;
    }
    public void setRunningFalse() {
        running = false;
    }

    public void run(){
        try{
            while (isRunning()) {//mudei aqui -gouve
                draw();
                handleInput();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw() throws IOException{
        screen.clear();
        gameOverView.render(screen.newTextGraphics());
        screen.refresh();
    }
    public void handleInput() throws IOException {
        KeyStroke key = screen.readInput();
        if(key.getKeyType()== KeyType.ArrowUp){
            gameOver.moveUp();
        } else if (key.getKeyType()==KeyType.ArrowDown) {
            gameOver.moveDown();

        } else if (key.getKeyType()==KeyType.Enter) {
            if (gameOver.isSelectedMenu()) {
                setRunningFalse();
                for(StateObserver observer : observers) {
                    observer.changed(0);
                }
            }
            else if (gameOver.isSelectedTryAgain()){
                setRunningFalse();
                for(StateObserver observer : observers) {
                    observer.changed(1);
                }
            }
        }
    }
}
