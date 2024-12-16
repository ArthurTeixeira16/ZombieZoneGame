package com.aor.ZombieZone.Controller;
import com.aor.ZombieZone.State.*;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Inicializer implements StateObserver {
    private Screen screen;
    private List<String> entries;
    private GameContext gameContext;

    private MenuController menuController;
    private MenuState menuState;

    private GameController gameController;
    private GameState gameState;

    private LeadBoardController leadBoardController;
    private LeadBoardState leadBoardState;

    private GameOverState gameOverState;

    private GameOverController gameOverController;

    public Inicializer() {
        try {
            URL resource = getClass().getClassLoader().getResource("square.ttf");
            if (resource == null) {
                throw new RuntimeException("Font resource not found!");
            }
            File fontFile = new File(resource.toURI());
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            DefaultTerminalFactory factory = new DefaultTerminalFactory();
            Font loadedFont = font.deriveFont(Font.PLAIN, 25);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
            factory.setTerminalEmulatorFontConfiguration(fontConfig);
            factory.setForceAWTOverSwing(true);
            factory.setInitialTerminalSize(new TerminalSize(30, 21));
            Terminal terminal = factory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

            menuState = new MenuState(screen);
            menuController = menuState.getController();
            menuController.addControllerObserver(this);

            gameContext = new GameContext(menuState);

            gameState = new GameState(screen);
            gameController = gameState.getController();
            gameController.getGame().addListener(gameController);
            gameController.addControllerObserver(this);

            leadBoardState = new LeadBoardState(screen);
            leadBoardController = leadBoardState.getController();
            leadBoardController.addObserver(this);

            gameController.getGame().addScoreObserver(leadBoardController.getLeadBoard());

            gameOverState = new GameOverState(screen);
            gameOverController = gameOverState.getGameOverController();
            gameOverController.addObserver(this);

        } catch (URISyntaxException | FontFormatException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void run() {
        try{
            gameContext.run();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void changed(int where){
        if(where==0){
            gameContext.setCurrentState(menuState);
        } else if (where == 1) {
            gameContext.setCurrentState(gameState);
        }else if(where == 2){
            gameContext.setCurrentState(leadBoardState);
        }else if (where == 3){
            gameContext.setCurrentState(gameOverState);
        }


    }

}
