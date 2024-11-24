package com.aor.hero;

import com.aor.hero.element.Soldier;
import com.aor.hero.element.Wall;
import com.aor.hero.element.Zombie;
import com.aor.hero.utils.WallCreator;
import com.aor.hero.utils.ZombieSpawner;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;
import java.util.Random;

public class Arena {
    private int height;
    private int width;
    private Random random;
    private static final TextColor BACKGROUND_COLOR = TextColor.Factory.fromString("#808080");
    public List<Wall> walls;
    public Soldier soldier;
    public List<Zombie> zombies;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.random = new Random();
        this.walls = WallCreator.createWalls(width, height, random);
        this.soldier = new Soldier(width / 2, height / 2);
        this.zombies = ZombieSpawner.spawnZombies(width, height, random, walls);
    }

    public void draw(TextGraphics screen) {
        for (Wall wall : walls) {
            wall.draw(screen);
        }
        soldier.draw(screen);
        for (Zombie zombie : zombies) {
            zombie.draw(screen);

        }
    }
}
