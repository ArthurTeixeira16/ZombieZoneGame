package com.ZombieZoneTest.States;

import com.aor.ZombieZone.Controller.GameController;
import com.aor.ZombieZone.State.GameState;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameStateTester {
    private GameController controllerMock;
    private  Screen screenMock;

    @BeforeEach
    public void setUp(){
        controllerMock = mock(GameController.class);
        screenMock = mock(Screen.class);
    }
    @Test
    public void testRunDelegation() throws IOException {

        GameState state = new GameState(screenMock){
            public GameController getController() {
                return controllerMock;
            }
        };

        state.run();

        verify(controllerMock).run();
    }

    @Test
    public void testHandleInput() throws IOException {

        GameState state = new GameState(screenMock){
            public GameController getController() {
                return controllerMock;
            }
        };

        state.handleInput();

        verify(controllerMock).handleInput();
    }

}
