package com.aor.ZombieZoneTest.Controller;

import com.aor.ZombieZone.Controller.LeadBoardController;
import com.aor.ZombieZone.Model.LeadBoard;
import com.aor.ZombieZone.State.StateObserver;
import com.aor.ZombieZone.View.LeadBoardView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LeadBoardControllerTester {
    private LeadBoardController leadBoardController;
    private LeadBoardView leadBoardViewMock;
    private LeadBoard leadBoardMock;
    private Screen screenMock;

    @BeforeEach
    public void setUp(){
        leadBoardMock = mock(LeadBoard.class);
        leadBoardViewMock = mock(LeadBoardView.class);
        screenMock = mock(Screen.class);
        leadBoardController = new LeadBoardController(leadBoardViewMock,leadBoardMock,screenMock);
    }

    @Test
    public void testIsRunning(){
        assertTrue(leadBoardController.isRunning());
    }
    @Test
    public void setTrueLead(){
        leadBoardController.setFalseLead();
        assertFalse(leadBoardController.isRunning());
        leadBoardController.setTrueLead();
        assertTrue(leadBoardController.isRunning());
    }

    @Test
    public void setFalseLeadState(){
        leadBoardController.setTrueLead();
        assertTrue(leadBoardController.isRunning());
        leadBoardController.setFalseLead();
        assertFalse(leadBoardController.isRunning());
    }
    @Test
    public void setTrueRunningState(){
        leadBoardController.setRunningFalse();
        assertFalse(leadBoardController.isRunning());
        leadBoardController.setRunningTrue();
        assertTrue(leadBoardController.isRunning());
    }
    @Test
    public void setFalseRunningState(){
        leadBoardController.setRunningTrue();
        assertTrue(leadBoardController.isRunning());
        leadBoardController.setRunningFalse();
        assertFalse(leadBoardController.isRunning());
    }
    @Test
    public void testAddObserver(){
        StateObserver stateObserverMock = mock(StateObserver.class);
        leadBoardController.addObserver(stateObserverMock);

        assertTrue(leadBoardController.getObservers().contains(stateObserverMock));
    }

    @Test
    public void testGetObserver(){
        StateObserver stateObserverMock = mock(StateObserver.class);
        leadBoardController.addObserver(stateObserverMock);
        List<StateObserver> observers = leadBoardController.getObservers();

        assertEquals(1,observers.size());
        assertEquals(observers.getFirst(),stateObserverMock);
    }


    @Test
    public void testRun() throws IOException {
        LeadBoardController spycontroller = spy(leadBoardController);

        doReturn(true).doReturn(false).when(spycontroller).isRunning();

        doNothing().when(spycontroller).draw();
        doNothing().when(spycontroller).handleInput();

        assertDoesNotThrow(spycontroller::run);

        verify(spycontroller).draw();
        verify(spycontroller).handleInput();
    }

    @Test
    public void testDraw() throws IOException {
        LeadBoardController spycontroller = spy(leadBoardController);

        spycontroller.draw();

        verify(screenMock).clear();
        verify(leadBoardViewMock).render(screenMock.newTextGraphics());
        verify(screenMock).refresh();
    }

    @Test
    public void testHandleInput() throws IOException {
        LeadBoardController spycontroller = spy(leadBoardController);
        KeyStroke letterQ = mock(KeyStroke.class);
        StateObserver observerMock = mock(StateObserver.class);

        leadBoardController.addObserver(observerMock);

        List<StateObserver> observers = spycontroller.getObservers();

        when(letterQ.getKeyType()).thenReturn(KeyType.Character);
        when(letterQ.getCharacter()).thenReturn('q');
        when(screenMock.readInput()).thenReturn(letterQ);

        spycontroller.handleInput();
        verify(observers.getFirst()).changed(0);
        verify(screenMock).clear();
        verify(spycontroller,times(1)).setFalseLead();
    }




}
