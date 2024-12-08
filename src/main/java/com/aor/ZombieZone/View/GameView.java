package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Model.*;
import com.googlecode.lanterna.graphics.TextGraphics;


import java.util.List;

    public class GameView {
        private ArenaView arenaView;
        private Soldier soldier;
        private List<Zombie> zombies;
        private List<Wall> walls;
        private List<Projectile> bullets;
        private HudView hudView;

        public GameView(ArenaView arenaView, Soldier soldier, List<Zombie> zombies, List<Wall> walls, HudView hudView, List<Projectile> bullets) {
            this.arenaView = arenaView;
            this.soldier = soldier;
            this.zombies = zombies;
            this.walls = walls;
            this.hudView = hudView;
            this.bullets = bullets;
        }

        public void render(TextGraphics screen) {
            arenaView.render(screen);
            hudView.render(screen);
            soldier.draw(screen);
            for (Wall wall : walls) {
                wall.draw(screen);
            }
            for (Zombie zombie : zombies) {
                zombie.draw(screen);
            }
            if(bullets!=null && !bullets.isEmpty()) {
                for (Projectile bullet : bullets) {
                    bullet.draw(screen);
                }
            }
        }
    }
