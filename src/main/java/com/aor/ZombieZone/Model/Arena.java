package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public class Arena {
    private int width;
    private int height;
    private char[][] tiles;
    private TextColor[][] colors;
    private Soldier soldier;
    private List<Zombie> zombies;
    private List<Wall> walls;
    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new char[height][width];
        this.colors = new TextColor[height][width];
        this.soldier = new Soldier(width / 2, height / 2);
        this.zombies = new Spawn(width,height,soldier).SpawnZombies();
        this.walls = WallCreator.createWalls(width,height);
        initializeArena();
    }

    private void initializeArena() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[y][x] = ' ';
                colors[y][x] = randomGrayShade();
            }
        }
    }

    public void draw(TextGraphics screen) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                screen.setBackgroundColor(colors[y][x]);
                TextColor foregroundColor = colors[y][x];
                screen.setForegroundColor(foregroundColor);
                screen.putString(x, y, String.valueOf(tiles[y][x]));
            }
        }
        soldier.draw(screen);
        for (Wall wall : walls) {
            wall.draw(screen);
        }
        for (Zombie zombie : zombies) {
            zombie.draw(screen);
        }
    }

    private TextColor randomGrayShade() {
        int grayValue = 180 + (int) (Math.random() * 50);
        String hexGray = String.format("#%02X%02X%02X", grayValue, grayValue, grayValue);
        return TextColor.Factory.fromString(hexGray);
    }
}