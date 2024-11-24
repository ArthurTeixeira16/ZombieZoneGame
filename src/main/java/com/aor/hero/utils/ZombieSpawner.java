package com.aor.hero.utils;

import com.aor.hero.element.Zombie;
import com.aor.hero.element.Wall;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZombieSpawner {
    public static List<Zombie> spawnZombies(int width, int height, Random random, List<Wall> walls) {
        List<Zombie> zombies = new ArrayList<>();
        int i = 0;
        while (i < 5) {
            int num1;
            int num2;
            do { num1 = random.nextInt(height - 5) + 1; } while (num1 == width / 2);
            do { num2 = random.nextInt(width - 5) + 1; } while (num2 == height / 2);
            if (!walls.contains(new Wall(num1, num2))) {
                zombies.add(new Zombie(num1, num2));
                i++;
            }
        }
        return zombies;
    }
}
