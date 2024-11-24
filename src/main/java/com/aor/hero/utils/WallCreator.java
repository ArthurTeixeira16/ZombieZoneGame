package com.aor.hero.utils;

import com.aor.hero.element.Wall;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WallCreator {
    public static List<Wall> createWalls(int width, int height, Random random) {
        List<Wall> walls = new ArrayList<>();

        // Adiciona as paredes das bordas
        for (int x = 0; x < width; x++) {
            // Adiciona as paredes superior e inferior
            walls.add(new Wall(x, 0)); // Paredes superior
            walls.add(new Wall(x, height - 1)); // Paredes inferior
        }
        for (int y = 0; y < height; y++) {
            // Adiciona as paredes esquerda e direita
            walls.add(new Wall(0, y)); // Paredes esquerda
            walls.add(new Wall(width - 1, y)); // Paredes direita
        }

        // Gera paredes aleatórias no interior
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width - 2) + 1;
            int y = random.nextInt(height - 2) + 1;
            Wall newWall = new Wall(x, y);

            if (!walls.contains(newWall)) {
                walls.add(newWall);
            } else {
                i--; // Se tiver um na mesma posição deminuimos o i
            }
        }

        return walls;
    }

}
