package com.ZombieZoneTest.Controller;

import com.aor.ZombieZone.Controller.GameOverController;
import com.aor.ZombieZone.Model.GameOver;
import com.aor.ZombieZone.State.StateObserver;
import com.aor.ZombieZone.View.GameOverView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameOverControllerTester {
    private GameOverController gameOverController;
    private GameOverView gameOverViewMock;
    private GameOver gameOverMock;
    private Screen screenMock;

    @BeforeEach
    public void setUp(){
        gameOverMock = mock(GameOver.class);
        gameOverViewMock = mock(GameOverView.class);
        screenMock = mock(Screen.class);
        gameOverController = new GameOverController(gameOverMock,gameOverViewMock,screenMock);
    }


    @Test
    public void testIsRunning(){
        assertTrue(gameOverController.isRunning());
    }
    @Test
    public void testSetRunningTrueState(){

        gameOverController.setRunningFalse();
        assertFalse(gameOverController.isRunning());

        gameOverController.setRunningTrue();
        assertTrue(gameOverController.isRunning());
    }

    @Test
    public void testSetRunningFalseState(){

        gameOverController.setRunningTrue();
        assertTrue(gameOverController.isRunning());

        gameOverController.setRunningFalse();
        assertFalse(gameOverController.isRunning());
    }

    @Test
    public void testAddObserver(){
        StateObserver stateObserverMock = mock(StateObserver.class);
        gameOverController.addObserver(stateObserverMock);

        assertTrue(gameOverController.getObservers().contains(stateObserverMock));

    }

    @Test
    public void testGetObserver(){
        StateObserver stateObserverMock = mock(StateObserver.class);
        gameOverController.addObserver(stateObserverMock);
        List<StateObserver> observers = gameOverController.getObservers();

        assertEquals(1,observers.size());
        assertEquals(observers.getFirst(),stateObserverMock);
    }

    @Test
    public void testRun() throws IOException{
        GameOverController spycontroller = spy(gameOverController);

        doReturn(true).doReturn(false).when(spycontroller).isRunning();

        doNothing().when(spycontroller).draw();
        doNothing().when(spycontroller).handleInput();

        assertDoesNotThrow(spycontroller::run);

        verify(spycontroller).draw();
        verify(spycontroller).handleInput();
    }

    @Test
    public void testDraw() throws IOException {
        GameOverController spycontroller = spy(gameOverController);

        spycontroller.draw();

        verify(screenMock).clear();
        verify(gameOverViewMock).render(screenMock.newTextGraphics());
        verify(screenMock).refresh();
    }

    @Test
    public void testHandleInput() throws IOException {
        GameOverController spycontroller = spy(gameOverController);
        KeyStroke arrowUp = mock(KeyStroke.class);
        StateObserver observerMock = mock(StateObserver.class);

        gameOverController.addObserver(observerMock);

        when(arrowUp.getKeyType()).thenReturn(KeyType.ArrowUp);
        when(screenMock.readInput()).thenReturn(arrowUp);
        spycontroller.handleInput();

        verify(gameOverMock).moveUp();

        KeyStroke arrowDown = mock(KeyStroke.class);

        when(arrowDown.getKeyType()).thenReturn(KeyType.ArrowDown);
        when(screenMock.readInput()).thenReturn(arrowDown);
        spycontroller.handleInput();

        verify(gameOverMock).moveDown();

        KeyStroke enter = mock(KeyStroke.class);

        when(enter.getKeyType()).thenReturn(KeyType.Enter);
        when(screenMock.readInput()).thenReturn(enter);

        when(gameOverMock.isSelectedMenu()).thenReturn(true);
        when(gameOverMock.isSelectedTryAgain()).thenReturn(false);

        spycontroller.handleInput();
        verify(spycontroller,times(1)).setRunningFalse();
        verify(gameOverMock).isSelectedMenu();
        verify(observerMock).changed(0);

        reset(gameOverMock);
        reset(observerMock);
        reset(spycontroller);// guys, tive que resetar aqui para poder testar sem que ele fosse chamado duas vezes

        when(gameOverMock.isSelectedMenu()).thenReturn(false);
        when(gameOverMock.isSelectedTryAgain()).thenReturn(true);

        spycontroller.handleInput();
        verify(spycontroller,times(1)).setRunningFalse();
        verify(gameOverMock).isSelectedTryAgain();
        verify(observerMock).changed(1);

    }



}
