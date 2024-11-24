package com.aor.ZombieZone.Controller;
import com.aor.ZombieZone.Model.Game;
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
            while (true) {
                draw();
                handleInput();
                game.update();
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
        if (key.getKeyType() == KeyType.Character) {
            switch (key.getCharacter()) {
                    case 'w':
                        game.getSoldier().moveUp();
                        break;
                    case 's':
                        game.getSoldier().moveDown();
                        break;
                    case 'a':
                        game.getSoldier().moveLeft();
                        break;
                    case 'd':
                        game.getSoldier().moveRight();
                        break;
                    case 'q':
                        screen.close();
                        System.exit(0);
                        break;
            }
        }
    }
}
