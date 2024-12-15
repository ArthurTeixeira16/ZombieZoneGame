package com.aor.ZombieZone.Controller;
import com.aor.ZombieZone.Model.Menu;
import com.aor.ZombieZone.Model.Game;
import com.aor.ZombieZone.Model.LeadBoard;
import com.aor.ZombieZone.Model.Position;
import com.aor.ZombieZone.Model.Projectile;
import com.aor.ZombieZone.StatsObserver;
import com.aor.ZombieZone.View.GameView;
import com.aor.ZombieZone.View.LeadBoardView;
import com.aor.ZombieZone.View.MenuView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.*;

public class LeadBoardController {
    private LeadBoardView leadBoardView;
    private LeadBoard leadBoard;
    private Screen screen;
    private boolean continuaLead = true;
    private List<StatsObserver> Observers = new ArrayList<>();
    public LeadBoardController(LeadBoardView leadBoardView, LeadBoard leadBoard, Screen screen) {
        this.leadBoardView = leadBoardView;
        this.leadBoard = leadBoard;
        this.screen = screen;
    }
    public void addobserver(StatsObserver divisionObserver) {
        Observers.add(divisionObserver);
    }
    public void setTruetoLead(){
        continuaLead = true;
    }
    public void setFalsetoLead(){
        continuaLead = false;
    }

    public void run() {
        try{
            leadBoard.addToListOfScore(10);
            leadBoard.addToListOfScore(100);
            leadBoard.addToListOfScore(341);
            leadBoard.addToListOfScore(43);
            leadBoard.addToListOfScore(2);
            leadBoard.addToListOfScore(912);
            leadBoard.addToListOfScore(10023);
            leadBoard.addToListOfScore(73);
            leadBoard.addToListOfScore(77);
            leadBoard.addToListOfScore(422);
            leadBoard.addToListOfScore(333);

            while(continuaLead){
                draw();
                handleInput();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    private void draw() throws IOException {
        screen.clear();
        leadBoardView.render(screen.newTextGraphics());
        screen.refresh();
    }
    private void handleInput() throws IOException {
        KeyStroke key = screen.readInput();
        if (key.getKeyType() == KeyType.Character) {
            if (key.getCharacter() == 'q') {
                System.out.println("FORAAAAA");
                Observers.getFirst().changed(0);
                screen.clear();
                setFalsetoLead();
            }
        }
    }

}