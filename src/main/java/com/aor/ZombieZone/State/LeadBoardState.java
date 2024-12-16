package com.aor.ZombieZone.State;

import com.aor.ZombieZone.Controller.LeadBoardController;
import com.aor.ZombieZone.Model.LeadBoard;
import com.aor.ZombieZone.View.LeadBoardView;
import com.googlecode.lanterna.screen.Screen;

import javax.swing.*;
import java.io.IOException;

public class LeadBoardState implements State {
    private LeadBoardController leadBoardController;
    private LeadBoard leadBoard;
    private LeadBoardView leadBoardView;

    public LeadBoardState(Screen screen){
        this.leadBoard = new LeadBoard();
        this.leadBoardView = new LeadBoardView(30,21,leadBoard);
        leadBoardController = new LeadBoardController(leadBoardView,leadBoard,screen);

    }

    public LeadBoardController getController() {
        return leadBoardController;
    }

    @Override
    public void run() throws IOException {
        getController().setRunningTrue();
        getController().run();

    }

    @Override
    public void handleInput(KeyStroke key) throws IOException {
        getController().handleInput();
    }
}
