package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Controller.GameController;
import com.aor.ZombieZone.Model.Arena;
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

        public GameView(ArenaView arenaView, Soldier soldier, List<Zombie> zombies, List<Wall> walls) {
            this.arenaView = arenaView;
            this.soldier = soldier;
            this.zombies = zombies;
            this.walls = walls;
        }

        public void render(TextGraphics screen) {
            arenaView.render(screen);
            soldier.draw(screen);
            for (Wall wall : walls) {
                wall.draw(screen);
            }
            for (Zombie zombie : zombies) {
                zombie.draw(screen);
            }
        }
    }
