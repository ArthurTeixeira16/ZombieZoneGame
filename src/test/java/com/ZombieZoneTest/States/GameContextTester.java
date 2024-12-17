package com.ZombieZoneTest.States;

import com.aor.ZombieZone.Model.Game;
import com.aor.ZombieZone.State.GameContext;
import com.aor.ZombieZone.State.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameContextTester {
    private State state;
    private GameContext context;

    @BeforeEach
    public void setUp(){
        state = mock(State.class);
        context = new GameContext(state);
    }

    @Test
    public void testInitialState(){

        assertEquals(state,context.getCurrentState());
    }
    @Test
    public void testSetStates(){

        State otherState = mock(State.class);
        context.setCurrentState(otherState);

        assertEquals(otherState,context.getCurrentState());
    }
    @Test
    public void CurrentStateContextRun() throws IOException {

        context.run();

        verify(state).run();
    }

    @Test
    public void CurrentStateHandleInputTest() throws IOException{

        context.handleInput();

        verify(state).handleInput();
    }
}
