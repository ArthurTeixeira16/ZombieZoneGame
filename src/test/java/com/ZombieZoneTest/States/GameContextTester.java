package com.ZombieZoneTest.States;

import com.aor.ZombieZone.Model.Game;
import com.aor.ZombieZone.State.GameContext;
import com.aor.ZombieZone.State.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

        doThrow(new IOException("Stop the loop")).when(state).run();

        try {
            context.run();
        } catch (IOException e) {
            assertEquals("Stop the loop", e.getMessage());
        }

        verify(state,times(1)).run();
    }

    @Test
    public void CurrentStateHandleInputTest() throws IOException{

        context.handleInput();

        verify(state,times(1)).handleInput();
    }
}
