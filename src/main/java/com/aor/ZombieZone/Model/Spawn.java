package com.aor.ZombieZone.Model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spawn {
    private int height;
    private int width;
    private Random random;
    private boolean[][] occupiedGrid;
    private List<Wall> walls;
    private Soldier soldier;

    private int zombies_per_round = 3;
    private int speedzombie_percentage = 5;
    private int  heavyzombie_percentage = 10;
    private int MINZOMBIES = 5;

    public Spawn(int width,int height,Soldier soldier,List<Wall> walls){
        this.height = height;
        this.width = width;
        this.random = new Random();
        this.soldier = soldier;
        this.walls=walls;

        occupiedGrid = new boolean[width][height];
    }
    public List<Enemy> SpawnZombies(int round){
        clearOccupiedGrid();
        markWallsAsOccupied();

        List<Enemy> zombiess = new ArrayList<>();
        int zombiequantity = (round * zombies_per_round);

        while(zombiequantity > 0) {
            boolean available = false;
            for (boolean[] booleans : occupiedGrid) {
                for (boolean aBoolean : booleans) {
                    if (!aBoolean) {
                        available = true;
                        break;
                    }
                }
                if (available) {
                    break;
                }
            }
            if (!available) {
                return zombiess;
            }
            Position current_position = GeneratePositions();

            if (zombiequantity < MINZOMBIES) {
                Enemy zombie = new ZombieNormal(current_position.getX(), current_position.getY());
                zombiess.add(zombie);
                occupiedGrid[current_position.getX()][current_position.getY()] = true;
                zombiequantity--;
            } else {
                int chance = random.nextInt(101);
                if (chance < speedzombie_percentage * round) {
                    Enemy zombie = new ZombieSpeed(current_position.getX(), current_position.getY());
                    zombiess.add(zombie);
                    occupiedGrid[current_position.getX()][current_position.getY()] = true;
                    zombiequantity -= 2;
                } else if (chance < heavyzombie_percentage * round) {
                    Enemy zombie = new ZombieHeavy(current_position.getX(), current_position.getY());
                    zombiess.add(zombie);
                    occupiedGrid[current_position.getX()][current_position.getY()] = true;
                    zombiequantity -= 2;
                } else {
                    Enemy zombie = new ZombieNormal(current_position.getX(), current_position.getY());
                    zombiess.add(zombie);
                    occupiedGrid[current_position.getX()][current_position.getY()] = true;
                    zombiequantity--;
                }
            }
        }
        return zombiess;
    }

    private Position GeneratePositions(){
        int x,y;
        Position position;
        do {
            x = random.nextInt(0, width );
            y = random.nextInt(0, height );
            position = new Position(x, y);
        } while(occupiedGrid[x][y]);
        return position;
    }

    private void markPositionAsOccupied(Position position) {
        occupiedGrid[position.getX()][position.getY()] = true;
    }

    private void markWallsAsOccupied() {
        for (Wall wall : walls) {
            Position pos = wall.getPosition();
            occupiedGrid[pos.getX()][pos.getY()] = true;
        }
        markPositionAsOccupied(soldier.getPosition());
    }
    private void clearOccupiedGrid() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                occupiedGrid[x][y] = false; // Redefine todas as posições como livres
            }
        }
    }
}
