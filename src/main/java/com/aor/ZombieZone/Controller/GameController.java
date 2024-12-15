package com.aor.ZombieZone.Controller;
import com.aor.ZombieZone.DivisionObserver;
import com.aor.ZombieZone.Model.Game;
import com.aor.ZombieZone.Model.GameListener;
import com.aor.ZombieZone.Model.Menu;
import com.aor.ZombieZone.Model.Position;
import com.aor.ZombieZone.View.GameView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GameController implements GameListener {
    private Game game;
    private GameView gameView;
    private Screen screen;
    private boolean running = true;
    List<DivisionObserver> obersers = new ArrayList<>();

    public GameController(Game game, GameView gameView, Screen screen) {
        this.game = game;
        this.gameView = gameView;
        this.screen = screen;
    }

    public void addoberser(DivisionObserver observer) {
        obersers.add(observer);
    }
    public void run() {
        try {
            new Thread(() -> {
                long lastTime = System.currentTimeMillis();
                while (running) {
                    long currentTime = System.currentTimeMillis();
                    long deltaTime = currentTime - lastTime;
                    lastTime = currentTime;
                    synchronized (game) {
                        try {
                            game.update(deltaTime,currentTime);//mudei aqui pra pegar os parametros do tempo absoluto também
                            draw();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            while (running) {
                handleInput();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void EndGame(){
        obersers.getFirst().changed(0);
        running = false;
        game.resetGame();
    }

    private void draw() throws IOException {
        screen.clear();
        gameView.render(screen.newTextGraphics());
        screen.refresh();
    }
    public void setRunningTrue(){
        running = true;
    }

    private void handleInput() throws IOException {
            KeyStroke key = screen.readInput();
            Position currentPosition = game.getSoldier().getPosition();
            Position newPosition = null;

            long currentTime = System.currentTimeMillis();
            if (key.getKeyType() == KeyType.ArrowUp && game.canShoot(currentTime)) {
                game.shoot(new Position(0, -1));
            } else if (key.getKeyType() == KeyType.ArrowDown && game.canShoot(currentTime)) {
                game.shoot(new Position(0, 1));

            } else if (key.getKeyType() == KeyType.ArrowLeft && game.canShoot(currentTime)) {
                game.shoot((new Position(-1, 0)));

            } else if (key.getKeyType() == KeyType.ArrowRight && game.canShoot(currentTime)) {
                game.shoot(new Position(1, 0));
            }


            if(key.getKeyType()==KeyType.Character && key.getCharacter()!= null) {
                switch (key.getCharacter()) {
                    case 'w':
                        newPosition = currentPosition.up();
                        break;
                    case 's':
                        newPosition = currentPosition.down();
                        break;
                    case 'a':
                        newPosition = currentPosition.left();
                        break;
                    case 'd':
                        newPosition = currentPosition.right();
                        break;

                    case 'q':
                        obersers.getFirst().changed(0);
                        running = false;
                }
            }

            if (newPosition != null && game.canMoveTo(newPosition)) {
                game.getSoldier().setPosition(newPosition);
            }
    }
}

