package com.aor.ZombieZone.Controller;

import com.aor.ZombieZone.Model.GameOver;
import com.aor.ZombieZone.StatsObserver;
import com.aor.ZombieZone.View.GameOverView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.menu;

public class GameOverController {
    private GameOver gameOver;
    private GameOverView gameOverView;
    private Screen screen;
    private boolean running = true;
    private List<StatsObserver> observers = new ArrayList<>();

    public GameOverController(GameOver gameOver, GameOverView gameOverView, Screen screen) {
        this.gameOver = gameOver;
        this.gameOverView = gameOverView;
        this.screen = screen;
    }
    public void addObserver(StatsObserver observer) {
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
            while (running) {
                draw();
                handleInput();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void draw() throws IOException{
        screen.clear();
        gameOverView.render(screen.newTextGraphics());
        screen.refresh();
    }
    private void handleInput() throws IOException {
        KeyStroke key = screen.readInput();
        if(key.getKeyType()== KeyType.ArrowUp){
            gameOver.moveUp();
        } else if (key.getKeyType()==KeyType.ArrowDown) {
            gameOver.moveDown();

        } else if (key.getKeyType()==KeyType.Enter) {
            if (gameOver.isSelectedMenu()) {
                setRunningFalse();
                for(StatsObserver observer : observers) {
                    observer.changed(0);
                }
            }
            if (gameOver.isSelectedTryAgain()){
                System.out.println("ALLOOOO");
                setRunningFalse();
                for(StatsObserver observer : observers) {
                    System.out.println("BANANAN");
                    observer.changed(1);
                }
            }
        }
    }
}
