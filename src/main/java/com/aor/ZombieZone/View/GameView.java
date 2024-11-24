package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Model.Soldier;
import com.aor.ZombieZone.Model.Wall;
import com.aor.ZombieZone.Model.Zombie;
import com.googlecode.lanterna.graphics.TextGraphics;


import java.util.List;

    public class GameView {
        private ArenaView arenaView;
        private Soldier soldier;
        private List<Zombie> zombies;
        private List<Wall> walls;
        private HudView hudView;

        public GameView(ArenaView arenaView, Soldier soldier, List<Zombie> zombies, List<Wall> walls, HudView hudView) {
            this.arenaView = arenaView;
            this.soldier = soldier;
            this.zombies = zombies;
            this.walls = walls;
            this.hudView = hudView;
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
        }
    }
