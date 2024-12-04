package com.aor.ZombieZone.Model;
import com.aor.ZombieZone.Controller.GameController;
import com.aor.ZombieZone.View.ArenaView;
import com.aor.ZombieZone.View.GameView;
import com.aor.ZombieZone.View.HudView;
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
import java.util.ArrayList;
import java.util.List;

public class Game {
    private Screen screen;
    private Arena arena;
    private Soldier soldier;
    private List<Zombie> zombies;
    private List<Wall> walls;
    private GameView gameView;
    private GameController gameController;
    private Hud hud;
    private HudView hudView;
    private List<Projectile> bullets;

    public Game() {
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
            factory.setInitialTerminalSize(new TerminalSize(40, 40));
            Terminal terminal = factory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            soldier = new Soldier(15,10);
            zombies = new Spawn(30,20,soldier).SpawnZombies();
            walls = WallCreator.createWalls(30,20);
            arena = new Arena(30,20,zombies,walls);
            hud = new Hud(soldier,arena);
            hudView= new HudView(hud);
            bullets = new ArrayList<>();
            gameView = new GameView(new ArenaView(arena), soldier, zombies,walls,hudView,bullets);
            gameController = new GameController(this, gameView, screen);

        } catch (URISyntaxException | FontFormatException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void run() {
        gameController.run();
    }

    public Soldier getSoldier() {
        return soldier;
    }

    public void shoot(Position direction){
        Position soldierposition = getSoldier().getPosition();
        Projectile projectile = new Projectile(soldierposition.getX(),soldierposition.getY(),7,1);
        projectile.setDirection(direction);
        bullets.add(projectile);
    }

    public void update(long deltaTime) {
        for(Zombie zombie : zombies){
            zombie.track(soldier,arena);
        }
        for(Projectile bullet: bullets){
            bullet.updateProjectile(deltaTime);
        }
        bullets.removeIf(Projectile::isDestroyed);
    }

    public List<Projectile> getBullets() {
        return this.bullets;
    }

    public Arena getArena() {
        return arena;
    }
}
