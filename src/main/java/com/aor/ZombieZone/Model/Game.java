package com.aor.ZombieZone.Model;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private Arena arena;
    private Soldier soldier;
    private List<Enemy> zombies;
    private List<Wall> walls;
    private Hud hud;
    private List<Projectile> bullets;
    private Round round;
    private Score score;
    private long lastShotTime = 0;
    private int timetoShoot = 1000;
    private long lastHit = 0;
    private int SafeTime = 1000;// não sei se isso fica aqui, mas o deixo for now
    private List<GameListener> gameListeners = new ArrayList<>();
    public Game() {
            resetGame();
    }
    // Quando ele morrer nós resetamos essa arena
    public void resetGame(){
        soldier = new Soldier(15,10);
        round = new Round();
        score = new Score();
        zombies = new Spawn(30,20,soldier).SpawnZombies(round.getRound());
        walls = WallCreator.createWalls(30,20);
        arena = new Arena(30,20);
        bullets = new ArrayList<>();
    }
    public void setHud(Hud hud) {
        this.hud = hud;
    }

    // qm quer saber se o heroi morreu
    public void addListener(GameListener gameListener){
        gameListeners.add(gameListener);
    }

    public boolean canHit(long currentTime){
        if(currentTime - lastHit >= SafeTime){
            lastHit = currentTime;
            return true;
        }
        return false;
    }

    public List<Position> getPositionsWalls() {
        List<Position> positions = new ArrayList<>();
        for(Wall wall : walls) {
            positions.add(wall.getPosition());
        }
        return positions;
    }
    public List<Position> getPositionsZombies() {
        List<Position> positions = new ArrayList<>();
        for(Enemy zombie : zombies) {
            positions.add(zombie.getPosition());
        }
        return positions;
    }

    public boolean canShoot(long currentTime){
        if (currentTime - lastShotTime >= timetoShoot){
            lastShotTime = currentTime;
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

    public void update(long deltaTime, long currentTime) throws IOException {
        if(soldier.isDead()){
            for(GameListener gameListener: gameListeners){
                gameListener.EndGame();
            }
        }
        for(Enemy zombie : zombies){
            zombie.updateZombieWalk(soldier,this,deltaTime);
            checkDamage(currentTime);
        }
        if (zombies.isEmpty()){
            round.nextRound();
            zombies = new Spawn(30,20,soldier).SpawnZombies(round.getRound());
        }
        for(Projectile bullet: bullets){
            bullet.updateProjectile(deltaTime);
            checkBulletsColisions();
        }
        bullets.removeIf(Projectile::isDestroyed);
        for(Enemy zombie : zombies){
            if(zombie.isDead()){
                score.addScore();
                hud.getScore().addScore();
            }
        }
        zombies.removeIf(Enemy::isDead);
        if (zombies.isEmpty()) {
            round.nextRound();
            hud.getRound().nextRound();
            zombies.clear();
            zombies.addAll(new Spawn(30, 20, soldier).SpawnZombies(round.getRound()));
        }
    }
    public boolean canMoveTo(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return false;
            }
        }
        for (Enemy zombie : zombies) {
            if (zombie.getPosition().equals(position)) {
                return false;
            }
        }

        return true;
    }

    public Soldier getSoldier() {
        return soldier;
    }
    public Arena getArena() {
        return arena;
    }
    public List<Enemy> getZombies() {
        return zombies;
    }
    public List<Wall> getWalls() {
        return walls;
    }
    public Hud getHud() { return hud;}
    public List<Projectile> getBullets() {
        return bullets;
    }

}
