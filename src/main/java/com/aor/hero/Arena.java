package com.aor.hero;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.*;

public class Arena {
    private int height;
    private int width;
    private Random random;
    private static final TextColor BACKGROUND_COLOR = TextColor.Factory.fromString("#808080");
    public List<Wall> walls;
    public Soldier soldier;
    public List<Zombie> zombies;

    public Arena(int width, int height) {
        /*Criações dos atributos privados sem valor*/
        this.width = width;
        this.height = height;
        this.random = new Random();
        this.walls = creatWalls();
        this.soldier = new Soldier(width/2,height/2);//c
        this.zombies = SpawnZombies();
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
        // Draw das Walls
        for (Wall wall : walls) {
            wall.draw(screen);
        }
        //Draw do Soldado e dos Zombies
        soldier.draw(screen);
        for(Zombie zombie:zombies){
            zombie.draw(screen);
        }
    }

    public List<Wall> creatWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int i = 0; i < 50; i++) { // Gera exatamente 20 paredes
            int x = random.nextInt(width-2)+1;  // Posição x aleatória dentro do limite
            int y = random.nextInt(height-2)+1; // Posição y aleatória dentro do limite
            Wall newWall = new Wall(x, y);

            // Garante que a nova parede não esteja em uma posição já usada
            if (!walls.contains(newWall)) {
                walls.add(newWall);
            } else {
                i--; // Tenta novamente caso haja colisão
            }
        }

        return walls;
    }

    //Função da List SpawnZombies
    private List<Zombie> SpawnZombies(){
        List<Zombie> zombies = new ArrayList<>();
        int i = 0;
        while(i < 5) {
            int num1;
            int num2;
            do { num1 = random.nextInt(height-5) + 1;
            } while( num1 == width/2);
            do { num2 = random.nextInt(width-5) + 1;
            } while( num2 == height/2);
            final int temp1 = num1;
            final int temp2 = num2;
            if ((!walls.contains(new Wall(temp1,temp2))) &&
                    (!zombies.contains(new Zombie(temp1, temp2)))) {
                zombies.add( new Zombie(num1,num2));
                i++;// Adiciona o Zombie apenas se a posição não estiver ocupada
            }
        }
        return zombies;
    }
}