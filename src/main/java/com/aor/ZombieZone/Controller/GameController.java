package com.aor.ZombieZone.Controller;
import com.aor.ZombieZone.Model.Game;
import com.aor.ZombieZone.Model.Position;
import com.aor.ZombieZone.Model.Projectile;
import com.aor.ZombieZone.View.GameView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;


public class GameController {
    private Game game;
    private GameView gameView;
    private Screen screen;


    public GameController(Game game, GameView gameView, Screen screen) {
        this.game = game;
        this.gameView = gameView;
        this.screen = screen;
    }

    public void run() {
        try {
            new Thread(() -> {
                long lastTime = System.currentTimeMillis();
                while (true) {
                    long currentTime = System.currentTimeMillis();
                    long deltaTime = currentTime - lastTime;
                    lastTime = currentTime;
                    synchronized (game) {
                        try {
                            game.update(deltaTime);
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

            while (true) {
                handleInput();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        gameView.render(screen.newTextGraphics());
        screen.refresh();
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
                        screen.close();
                        System.exit(0);
                        return;
                }
            }

            if (newPosition != null && game.getArena().canMoveTo(newPosition)) {
                game.getSoldier().setPosition(newPosition);
            }
    }
}

