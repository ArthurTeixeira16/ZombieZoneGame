package com.aor.ZombieZone;
import com.aor.ZombieZone.Controller.GameController;
import com.aor.ZombieZone.Controller.GameOverController;
import com.aor.ZombieZone.Controller.LeadBoardController;
import com.aor.ZombieZone.Controller.MenuController;
import com.aor.ZombieZone.Model.*;
import com.aor.ZombieZone.Model.Menu;
import com.aor.ZombieZone.View.*;
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

public class Inicializer implements StatsObserver{
    private Screen screen;
    private List<String> entries;
    private int currentEntry = 0;
    /*
    * Valores privado Do Menu, MenuView e o Menu Controller
    */
    private Menu menu;
    private MenuView menuView;
    private MenuController menuController;
    /*
    * Valores privado Do Game, GameView e o GameController
     */
    private Game game;
    private GameView gameView;
    private GameController gameController;
    /*
     * Valores privado Do LeadBoard
     * LeadBoardView
     * e o GameController
     */
    private LeadBoard leadBoard;
    private LeadBoardView leadBoardView;
    private LeadBoardController leadBoardController;
    /*
     * Definição Do LeadBoard
     * LeadBoardView
     * e o LeadBoardController
     */
    private GameOver gameOver;
    private GameOverView gameOverView;
    private GameOverController gameOverController;

    public Inicializer() {
        try {
            URL resource = getClass().getClassLoader().getResource("square.ttf");
            if (resource == null) {
                throw new RuntimeException("Font resource not found!");
            }
            //Definição da tela ;
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

            this.entries = Arrays.asList("Menu", "Start", "Lead", "Game Over");
            /*
            * Definição Do Menu, MenuView e MenuController
            */
            menu = new Menu();
            menuView = new MenuView(menu);
            menuController = new MenuController(menu,menuView,screen);
            menuController.addobserver(this);
            /*
             * Definição Do Game, GameView e GameController
             */
            game = new Game();
            Hud hud = new Hud(game);
            HudView hudView = new HudView(hud);
            game.setHud(hud);
            Arena arena = new Arena(30,20);
            ArenaView arenaView = new ArenaView(arena);
            gameView = new GameView( arenaView , game);
            gameView.setHudView(hudView);
            gameController = new GameController( game , gameView , screen);
            game.addListener(gameController);
            gameController.addoberser(this);
            /*
             * Definição Do LeadBoard
             * LeadBoardView
             * e o LeadBoardController
             */
            leadBoard = new LeadBoard();
            leadBoardView = new LeadBoardView(30,21 , leadBoard);
            leadBoardController = new LeadBoardController(leadBoardView , leadBoard , screen);
            leadBoardController.addobserver(this);
            /*
             * Definição Do GameOver
             * GameOverView
             * e o GameViewController
             */
            gameOver = new GameOver(30,21);
            gameOverView = new GameOverView(gameOver);
            gameOverController = new GameOverController(gameOver, gameOverView, screen);
            gameOverController.addObserver(this);

        } catch (URISyntaxException | FontFormatException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void run() {
            while(true){
                if(this.currentEntry == 0){
                    menuController.setRunningTrue();
                    menuController.run();
                }
                else if(this.currentEntry == 1){
                    gameController.setRunningTrue();
                    gameController.run();
                }
                else if(this.currentEntry == 2){
                    leadBoardController.setTruetoLead();
                    leadBoardController.run();
                }
                else if(this.currentEntry == 3){
                    gameOverController.setRunningTrue();
                    gameOverController.run();
                }
            }
    }

    @Override
    public void changed(int where){
        this.currentEntry = where;


    }

}
