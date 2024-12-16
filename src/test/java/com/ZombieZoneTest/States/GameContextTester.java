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


    @BeforeEach
    public void setUp(){
    }

    @Test
    public void testInitialState(){

        State initialState = mock(State.class);
        GameContext context = new GameContext(initialState);

        assertEquals(initialState,context.getCurrentState());
    }
    @Test
    public void testSetStates(){

        State initialState = mock(State.class);
        State otherState = mock(State.class);
        GameContext context = new GameContext(initialState);
        context.setCurrentState(otherState);

        assertEquals(context.getCurrentState(), otherState);
    }
    @Test
    public void CurrentStateContextRun() throws IOException {

        State state = mock(State.class);
        GameContext context = new GameContext(state);

        context.run();

        verify(state).run();
    }

    @Test
    public void CurrentStateHandleInputTest() throws IOException{
        State state = mock(State.class);
        KeyStroke key = mock(KeyStroke.class);
        GameContext context = new GameContext(state);

        context.handleInput(key);

        verify(state).handleInput(key);
    }
}
