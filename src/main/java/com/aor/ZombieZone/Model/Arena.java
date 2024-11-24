package com.aor.ZombieZone.Model;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public class Arena {
    private int width;
    private int height;
    private char[][] tiles;
    private TextColor[][] colors;
    private List<Zombie> zombies;
    private List<Wall> walls;
    public Arena(int width, int height, List<Zombie> zombies, List<Wall> walls) {
        this.width = width;
        this.height = height;
        this.zombies = zombies;
        this.walls = walls;
        this.tiles = new char[height][width];
        this.colors = new TextColor[height][width];
        initializeArena();
    }
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
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
    }

    private TextColor randomGrayShade() {
        int grayValue = 180 + (int) (Math.random() * 50);
        String hexGray = String.format("#%02X%02X%02X", grayValue, grayValue, grayValue);
        return TextColor.Factory.fromString(hexGray);
    }
    public boolean canMoveTo(Position position) {
        if (position.getX() < 0 || position.getX() >= width ||
                position.getY() < 0 || position.getY() >= height) {
            return false;
        }
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return false;
            }
        }
        for (Zombie zombie : zombies) {
            if (zombie.getPosition().equals(position)) {
                return false;
            }
        }

        return true;
    }
}