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
    private List<Enemy> zombies;
    private List<Wall> walls;
    private GameView gameView;
    private GameController gameController;
    private Hud hud;
    private HudView hudView;
    private List<Projectile> bullets;
    private Round round;
    private Score score;
    private long lastShotTime = 0;
    private int timetoShoot = 1000;
    private long lastHit = 0;
    private int SafeTime = 5000;// não sei se isso fica aqui, mas o deixo for now
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
            round = new Round();
            score = new Score();
            zombies = new Spawn(30,20,soldier).SpawnZombies(round);
            walls = WallCreator.createWalls(30,20);
            arena = new Arena(30,20,zombies,walls);
            hud = new Hud(soldier,arena,score,round);
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

    public boolean canShoot(long currentTime){
        if (currentTime - lastShotTime >= timetoShoot){
            lastShotTime = currentTime;
            return true;
        }
        return false;
    }

    public boolean canHit(long currentTime){
        if(currentTime - lastHit >= SafeTime){
            lastHit = currentTime;
            return true;
        }
        return false;
    }

    public void shoot(Position direction){
            Position soldierposition = getSoldier().getPosition();
            Projectile projectile = new Projectile(soldierposition.getX() + direction.getX(), soldierposition.getY() + direction.getY(), 7, 4);
            projectile.setDirection(direction);
            bullets.add(projectile);
    }

    public void checkDamage(long currentTime){
        for(Enemy zombie: zombies){
            if(zombie.getPosition().equals(soldier.getPosition())){
                if(canHit(currentTime)) {
                    soldier.hit();
                }
            }
        }
    }

    public void checkBulletsColisions(){
        for(Projectile bullet: bullets){
            for(Enemy zombie:zombies){
                if(bullet.getPosition().equals(zombie.getPosition())){
                    if(!bullet.isDestroyed()) {
                        bullet.destroy();
                        zombie.hit();
                    }
                }
            }
            for(Wall wall:walls){
                if(bullet.getPosition().equals(wall.getPosition())){
                    bullet.destroy();
                }
            }
        }
    }

    public void update(long deltaTime,long currentTime) throws IOException {
        if(soldier.isDead()){
            screen.close();
            System.exit(0);
            return;
        }
        for(Enemy zombie : zombies){
            zombie.updateZombieWalk(soldier,arena,deltaTime);
            checkDamage(currentTime);//agora o tempo absoluto permite a gente calcular os intervalos entre hits
        }
        if (zombies.isEmpty()){
            round.nextRound();
            zombies = new Spawn(30,20,soldier).SpawnZombies(round);
        }
        for(Projectile bullet: bullets){
            bullet.updateProjectile(deltaTime);
            checkBulletsColisions();
        }
        bullets.removeIf(Projectile::isDestroyed);
        for(Enemy zombie : zombies){
            if(zombie.isDead()){
                score.addScore();
            }
        }
        zombies.removeIf(Enemy::isDead);//não funcionou dentro da branch
        if (zombies.isEmpty()) {
            round.nextRound();
            zombies.clear();
            zombies.addAll(new Spawn(30, 20, soldier).SpawnZombies(round));
        }
    }

    public Arena getArena() {
        return arena;
    }
}
