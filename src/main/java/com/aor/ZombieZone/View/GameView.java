package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Model.Enemy;
import com.aor.ZombieZone.Model.Game;
import com.aor.ZombieZone.Model.Projectile;
import com.aor.ZombieZone.Model.Wall;
import com.googlecode.lanterna.graphics.TextGraphics;

    public class GameView {
        private ArenaView arenaView;
        private HudView hudView;
        private Game game;

        public GameView(ArenaView arenaView, Game game) {
            this.arenaView = arenaView;
            this.game = game;
        }
        public void setHudView(HudView hudView) {
            this.hudView = hudView;
        }


        public void render(TextGraphics screen) {
            arenaView.render(screen);
            hudView.render(screen);
            game.getSoldier().draw(screen);
            for (Wall wall : game.getWalls()) {
                wall.draw(screen);
            }
            if(game.getProjectiles()!=null && !game.getProjectiles().isEmpty()) {
                for (Projectile bullet : game.getProjectiles()) {
                    bullet.draw(screen);
                }
            }
            for (Enemy zombie : game.getZombies()) {
                zombie.draw(screen);
            }

        }
    }
