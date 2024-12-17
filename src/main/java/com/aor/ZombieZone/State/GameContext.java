package com.aor.ZombieZone.State;

import javax.swing.*;
import java.io.IOException;

public class GameContext {
    private State currentState;

    public GameContext(State initialState){
        this.currentState = initialState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State state) {
        this.currentState = state;
    }

    public void run() throws IOException{
        while(true) {
            currentState.run();
        }
    }
    public void handleInput() throws IOException {
        currentState.handleInput();
    }

}
