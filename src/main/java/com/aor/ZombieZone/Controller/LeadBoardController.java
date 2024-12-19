package com.aor.ZombieZone.Controller;
import com.aor.ZombieZone.Model.LeadBoard;
import com.aor.ZombieZone.State.StateObserver;
import com.aor.ZombieZone.View.LeadBoardView;
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
    private List<StateObserver> Observers = new ArrayList<>();
    public LeadBoardController(LeadBoardView leadBoardView, LeadBoard leadBoard, Screen screen) {
        this.leadBoardView = leadBoardView;
        this.leadBoard = leadBoard;
        this.screen = screen;
    }
    public void setFalseLead(){
        running = false;
    }
    public void setTrueLead(){
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public void addObserver(StateObserver divisionObserver) {
        Observers.add(divisionObserver);
    }

    public List<StateObserver> getObservers() {
        return Observers;
    }

    public void run() {
        try{
            while(isRunning()){
                draw();
                handleInput();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw() throws IOException {
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
                setFalseLead();
            }
        }
    }

    public LeadBoard getLeadBoard() {
        return leadBoard;
    }

    public void setRunningTrue() {
        running = true;
    }
    public void setRunningFalse() { running = false;}
}