package com.aor.ZombieZoneTest.States;

import com.aor.ZombieZone.Controller.MenuController;
import com.aor.ZombieZone.State.MenuState;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MenuStateTester {
    private MenuController controllerMock;
    private Screen screenMock;

    @BeforeEach
    public void setUp(){
        controllerMock = mock(MenuController.class);
        screenMock = mock(Screen.class);
    }
    @Test
    public void testRunDelegation() throws IOException {

        MenuState state = new MenuState(screenMock){
            public MenuController getController(){
                return controllerMock;
            }
        };

        state.run();

        verify(controllerMock).setRunningTrue();
        verify(controllerMock).run();
    }

    @Test
    public void testHandleInputDelegation() throws  IOException{

        MenuState state = new MenuState(screenMock){
            public MenuController getController(){
                return controllerMock;
            }
        };

        state.handleInput();

        verify(controllerMock).handleInput();
    }
}
