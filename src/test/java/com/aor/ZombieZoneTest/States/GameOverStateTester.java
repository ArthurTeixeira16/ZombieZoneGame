package com.aor.ZombieZoneTest.States;

import com.aor.ZombieZone.Controller.GameOverController;
import com.aor.ZombieZone.State.GameOverState;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameOverStateTester {
    private GameOverController controllerMock;
    private  Screen screenMock;


    @BeforeEach
    public void setUp(){
        controllerMock = mock(GameOverController.class);
        screenMock = mock(Screen.class);
    }

    @Test
    public void testRunDelegation() throws IOException {

        GameOverState state = new GameOverState(screenMock){
            public GameOverController getGameOverController() {
                return controllerMock;
            }
        };
        state.run();

        verify(controllerMock).setRunningTrue();
        verify(controllerMock).run();
    }

    @Test
    public void testHandleInputDelegation() throws IOException {

        GameOverState state = new GameOverState(screenMock){
            public GameOverController getGameOverController() {
                return controllerMock;
            }
        };

        state.handleInput();

        verify(controllerMock).handleInput();
    }
}
