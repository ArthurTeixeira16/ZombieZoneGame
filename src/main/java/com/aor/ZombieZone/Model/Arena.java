package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.TextColor;

public class Arena {
    private int width;
    private int height;
    private char[][] tiles;
    private TextColor[][] colors;
    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
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

    private TextColor randomGrayShade() {
        int grayValue = 180 + (int) (Math.random() * 50);
        String hexGray = String.format("#%02X%02X%02X", grayValue, grayValue, grayValue);
        return TextColor.Factory.fromString(hexGray);
    }
    public TextColor[][] getColors() {
        return colors;
    }
    public char[][] getTiles() {
        return tiles;
    }
}