package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;


import static java.lang.Math.sqrt;

public abstract class Enemy extends Entity {
    protected int speed;
    protected long elapsed_time;

    public Enemy(int x,int y){
        super(x,y);
    }

    public void updateZombieWalk(Soldier soldier,Game game,long deltaTime){
        elapsed_time += deltaTime;
        int timeToMove = 1000/speed;

        while(elapsed_time>=timeToMove){
            track(soldier, game);
            elapsed_time -= timeToMove;
        }
    }
    public int[][] getPlaces(Soldier soldier, Game game){
        int[][] places = new int[game.getArena().getWidth()][game.getArena().getHeight()];
        List<Position> positionsOfWalls = game.getPositionsWalls();

        Position soldierPosition = soldier.getPosition();

        for(Position position : positionsOfWalls){
            places[position.getX()][position.getY()] = 2;
        }
        List<Position> positionsOfZombies = game.getPositionsZombies();
        for(Position position : positionsOfZombies){
            if(position != soldierPosition) {
                places[position.getX()][position.getY()] = 2;
            }
        }
        return places;
    }
    public void track(Soldier soldier, Game game) {
        int[][] places = new int[game.getArena().getWidth()][game.getArena().getHeight()];
        List<Position> positionsOfWalls = game.getPositionsWalls();

        Position soldierPosition = soldier.getPosition();

        for(Position position : positionsOfWalls){
            places[position.getX()][position.getY()] = 2;
        }
        List<Position> positionsOfZombies = game.getPositionsZombies();
        for(Position position : positionsOfZombies){
            if(position != soldierPosition) {
                places[position.getX()][position.getY()] = 2;
            }
        }

        TraceToHero(this.getPosition(), soldierPosition , places , game);
    }
    public void TraceToHero(Position zombiePosition, Position soldierPosition, int[][] places, Game game) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        Queue<Position> queue = new LinkedList<>();
        boolean[][] visited = new boolean[places.length][places[0].length];
        Position[][] predecessor = new Position[places.length][places[0].length];

        queue.add(zombiePosition);
        visited[zombiePosition.getX()][zombiePosition.getY()] = true;
        predecessor[zombiePosition.getX()][zombiePosition.getY()] = null;
        while (!queue.isEmpty()) {
            Position current = queue.poll();

            if (current.equals(soldierPosition)) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                int newX = current.getX() + dx[i];
                int newY = current.getY() + dy[i];
                Position nextPosition = new Position(newX, newY);

                if (newX >= 0 && newY >= 0 && newX < places.length && newY < places[0].length && places[newX][newY] != 2 && !visited[newX][newY]) {

                    visited[newX][newY] = true;
                    predecessor[newX][newY] = current;
                    queue.add(nextPosition);
                }
            }
        }
        Position step = soldierPosition;
        while (predecessor[step.getX()][step.getY()] != null && !predecessor[step.getX()][step.getY()].equals(zombiePosition)) {
            step = predecessor[step.getX()][step.getY()];
        }
        Random random = new Random();
        if (step != null) {
            if ( step == soldierPosition){
            if(sqrt(Math.pow(step.getX() - zombiePosition.getX(),2) +Math.pow(step.getY() - zombiePosition.getY(),2)) < 2){
            this.setPosition(step); }
            else{
                this.setPosition(new Position(zombiePosition.getX() + dx[random.nextInt(4)], zombiePosition.getY() + dy[random.nextInt(4)]));
            }
            }
            else{
                this.setPosition(step);
            }
        }
    }
}
