package com.aor.ZombieZoneTest.States;

import com.aor.ZombieZone.Controller.LeadBoardController;
import com.aor.ZombieZone.State.LeadBoardState;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LeadBordStateTester {
    private LeadBoardController controllerMock;
    private Screen screenMock;

    @BeforeEach
    public void setUp(){
        controllerMock = mock(LeadBoardController.class);
        screenMock = mock(Screen.class);
    }

    @Test
    public void testRunDelegation() throws IOException {

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
    public void testHandleInputDelegation() throws IOException{

        LeadBoardState state = new LeadBoardState(screenMock) {
            public LeadBoardController getController() {
                return controllerMock;
            }
        };
        state.handleInput();

        verify(controllerMock).handleInput();
    }
}
