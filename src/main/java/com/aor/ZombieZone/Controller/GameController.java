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
            long lastTime = System.currentTimeMillis();
            while (true) {
                long currentTime = System.currentTimeMillis();
                long deltaTime = currentTime - lastTime;
                lastTime = currentTime;
                draw();
                handleInput();
                game.update(deltaTime);
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

            if(key.getKeyType()==KeyType.ArrowUp){
                game.shoot(new Position(0, -1));
            } else if (key.getKeyType()==KeyType.ArrowDown) {
                game.shoot(new Position(0, 1));
                
            } else if (key.getKeyType()==KeyType.ArrowLeft) {
                game.shoot((new Position(-1,0)));

            }else if(key.getKeyType()==KeyType.ArrowRight){
                game.shoot(new Position(1,0));
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
                        return; // Ensure method exits
                }
            }

            if (newPosition != null && game.getArena().canMoveTo(newPosition)) {
                game.getSoldier().setPosition(newPosition);
            }
    }
}

