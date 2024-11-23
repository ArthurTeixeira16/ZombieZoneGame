package Game;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.*;

public class Arena {
    private int height;
    private int width;
    private Random random;
    private static final TextColor BACKGROUND_COLOR = TextColor.Factory.fromString("#808080");
    private List<Wall> walls;

    public Arena(int width, int height) {
        /*Criações dos atributos privados sem valor*/
        this.width = width;
        this.height = height;
        this.random = new Random();
        this.walls = creatWalls();
    }

    public void draw(TextGraphics screen) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                /*O fundo fica inteiramente gray, já não temos gray diferentes em cada pixel*/
                screen.setBackgroundColor(BACKGROUND_COLOR);

                char tile = (y == 0 || y == height - 1 || x == 0 || x == width - 1) ? '#' : ' ';
                TextColor foregroundColor = tile == '#' ? TextColor.Factory.fromString("#6A4E23") : BACKGROUND_COLOR;
                screen.setForegroundColor(foregroundColor);
                screen.putString(x, y, String.valueOf(tile));
            }
        }
        for (Wall wall : walls) {
            wall.draw(screen);
        }
    }

    public List<Wall> creatWalls() {
        List<Wall> walls = new ArrayList<>();
        List<Wall> doors = new ArrayList<>();
        Random random = new Random();

        // Adiciona obstáculos horizontais com portas
        for (int y = 1; y <= height; y += height / 5) {
            int doorPosition = random.nextInt(width) + 1;
            for (int x = 1; x <= width; x++) {
                if (x == doorPosition) {
                    doors.add(new Wall(x, y));
                } else {
                    walls.add(new Wall(x, y));
                }
            }
        }

        // Adiciona obstáculos verticais com portas
        for (int x = 1; x <= width; x += width / 5) {
            int doorPosition = random.nextInt(height) + 1;
            for (int y = 1; y <= height; y++) {
                if (y == doorPosition) {
                    doors.add(new Wall(x, y));
                } else {
                    walls.add(new Wall(x, y));
                }
            }
        }

        // Adiciona obstáculos aleatórios
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width) + 1;
            int y = random.nextInt(height) + 1;
            if (!walls.contains(new Wall(x, y)) && !doors.contains(new Wall(x, y))) {
                walls.add(new Wall(x, y));
            }
        }

        // Combina paredes e portas
        walls.addAll(doors);
        return walls;
    }
}