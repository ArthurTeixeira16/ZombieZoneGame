package com.aor.ZombieZone.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WallCreator {
    private static Random random;
    public static List<Wall> createWalls(int width, int height) {
        List<Wall> walls = new ArrayList<>();
        random = new Random();
        for (int x = 0; x < width; x++) {
            walls.add(new Wall(x, 0));
            walls.add(new Wall(x, height - 1));
        }
        for (int y = 0; y < height; y++) {
            walls.add(new Wall(0, y));
            walls.add(new Wall(width - 1, y));
        }

        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width - 2) + 1;
            int y = random.nextInt(height - 2) + 1;
            Wall newWall = new Wall(x, y);

            if (!walls.contains(newWall)) {
                walls.add(newWall);
            } else {
                i--;
            }
        }
        return walls;
    }
}