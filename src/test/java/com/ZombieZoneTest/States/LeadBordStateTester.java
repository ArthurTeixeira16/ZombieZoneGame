package com.ZombieZoneTest.States;

import com.aor.ZombieZone.Controller.LeadBoardController;
import com.aor.ZombieZone.Model.LeadBoard;
import com.aor.ZombieZone.State.LeadBoardState;
import com.aor.ZombieZone.View.LeadBoardView;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LeadBordStateTester {

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void testRunDelegation() throws IOException {
        LeadBoard leadbordMock = mock(LeadBoard.class);
        LeadBoardView leadBoardViewMock = mock(LeadBoardView.class);
        LeadBoardController controllerMock = mock(LeadBoardController.class);
        Screen screenMock = mock(Screen.class);

        LeadBoardState state = new LeadBoardState(screenMock) {
            public LeadBoardController getController() {
                return controllerMock;
            }
        };


        state.run();

        verify(controllerMock).setRunningTrue();
        verify(controllerMock).run();

    }

    @Test
    public void testHandleInput() throws IOException{
        LeadBoard leadbordMock = mock(LeadBoard.class);
        LeadBoardView leadBoardViewMock = mock(LeadBoardView.class);
        LeadBoardController controllerMock = mock(LeadBoardController.class);
        Screen screenMock = mock(Screen.class);
        KeyStroke keyMock = mock(KeyStroke.class);

        LeadBoardState state = new LeadBoardState(screenMock) {
            public LeadBoardController getController() {
                return controllerMock;
            }
        };

        state.handleInput(keyMock);
    }
}
