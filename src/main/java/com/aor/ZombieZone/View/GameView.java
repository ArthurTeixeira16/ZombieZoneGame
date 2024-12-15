package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Model.*;
import com.googlecode.lanterna.graphics.TextGraphics;


import java.util.List;

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
            if(game.getBullets()!=null && !game.getBullets().isEmpty()) {
                for (Projectile bullet : game.getBullets()) {
                    bullet.draw(screen);
                }
            }
            for (Enemy zombie : game.getZombies()) {
                zombie.draw(screen);
            }

        }
    }
