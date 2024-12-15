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
    private boolean running = true;
    private List<StatsObserver> Observers = new ArrayList<>();
    public LeadBoardController(LeadBoardView leadBoardView, LeadBoard leadBoard, Screen screen) {
        this.leadBoardView = leadBoardView;
        this.leadBoard = leadBoard;
        this.screen = screen;
    }
    public void addObserver(StatsObserver divisionObserver) {
        Observers.add(divisionObserver);
    }
    public void setTruetoLead(){ running = true;}
    public void setFalsetoLead(){
        running = false;
    }

    public void run() {
        try{
            while(running){
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
    public void handleInput() throws IOException {
        KeyStroke key = screen.readInput();
        if (key.getKeyType() == KeyType.Character) {
            if (key.getCharacter() == 'q') {
                Observers.getFirst().changed(0);
                screen.clear();
                setFalsetoLead();
            }
        }
    }

    public LeadBoard getLeadBoard() {
        return leadBoard;
    }

    public void setRunningTrue() {
        running = true;
    }
}