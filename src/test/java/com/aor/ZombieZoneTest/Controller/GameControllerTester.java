package com.aor.ZombieZoneTest.Controller;

import com.aor.ZombieZone.Controller.GameController;
import com.aor.ZombieZone.Model.Game;
import com.aor.ZombieZone.Model.GameListener;
import com.aor.ZombieZone.Model.Position;
import com.aor.ZombieZone.Model.Soldier;
import com.aor.ZombieZone.State.StateObserver;
import com.aor.ZombieZone.View.GameView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameControllerTester {
    private GameController gameController;
    private Game gameMock;
    private GameView gameViewMock;
    private Screen screenMock;
    private StateObserver observerMock;

    @BeforeEach
    public void setUp() {
        gameMock = mock(Game.class);
        gameViewMock = mock(GameView.class);
        screenMock = mock(Screen.class);
        observerMock = mock(StateObserver.class);
        gameController = new GameController(gameMock, gameViewMock, screenMock);
        gameController.addObserver(observerMock);
    }
    @Test
    public void testDraw() throws IOException{
        gameController.draw();

        verify(screenMock).clear();
        verify(gameViewMock).render(screenMock.newTextGraphics());
        verify(screenMock).refresh();
    }
    @Test
    public void testIsRunning() {
        assertTrue(gameController.isRunning());
    }
    @Test
    public void testSetRunningTrueState(){
        gameController.setRunningFalse();
        assertFalse(gameController.isRunning());
        gameController.setRunningTrue();
        assertTrue(gameController.isRunning());
    }
    @Test
    public void testSetRunningFalse(){
        gameController.setRunningTrue();
        assertTrue(gameController.isRunning());
        gameController.setRunningFalse();
        assertFalse(gameController.isRunning());
    }
    @Test
    public void testGetScreen(){
        gameController.setScreen(screenMock);
        Screen screen = gameController.getScreen();
        assertNotNull(screen);
        assertEquals(screenMock,screen);
    }
    @Test
    public void testSetScreen(){
        gameController.setScreen(screenMock);
        assertEquals(screenMock,gameController.getScreen());
    }
    @Test
    public void testGetObserver(){
        List<StateObserver> observers = gameController.getObservers();

        assertEquals(1,observers.size());
        assertEquals(observers.getFirst(),observerMock);
    }
    @Test
    public void testAddObserver(){
        StateObserver stateObserverMock = mock(StateObserver.class);
        gameController.addObserver(stateObserverMock);

        assertTrue(gameController.getObservers().contains(stateObserverMock));
    }

    @Test
    public void testHandleInputMovement() throws IOException{
        Soldier soldierMock = mock(Soldier.class);
        when(gameMock.getSoldier()).thenReturn(soldierMock);
        when(soldierMock.getPosition()).thenReturn(new Position(5, 5));

        KeyStroke keyStrokeMock = mock(KeyStroke.class);
        when(screenMock.pollInput()).thenReturn(keyStrokeMock);

        when(keyStrokeMock.getKeyType()).thenReturn(KeyType.Character);
        when(keyStrokeMock.getCharacter()).thenReturn('w');
        when(gameMock.getSoldier().getPosition()).thenReturn(new Position(5, 5));
        when(gameMock.canMoveTo(any(Position.class))).thenReturn(true);

        GameController spyController = spy(gameController);
        doReturn(true).doReturn(false).when(spyController).isRunning();

        spyController.handleInput();

        verify(gameMock.getSoldier()).setPosition(new Position(5, 4));
    }
    @Test
    public void testHandleInputShoot() throws IOException{
        Soldier soldierMock = mock(Soldier.class);
        when(gameMock.getSoldier()).thenReturn(soldierMock);
        when(soldierMock.getPosition()).thenReturn(new Position(5, 5));

        KeyStroke keyStrokeMock = mock(KeyStroke.class);
        when(screenMock.pollInput()).thenReturn(keyStrokeMock);

        when(keyStrokeMock.getKeyType()).thenReturn(KeyType.ArrowUp);
        when(gameMock.canShoot(anyLong())).thenReturn(true);

        GameController spyController = spy(gameController);
        doReturn(true).doReturn(false).when(spyController).isRunning();

        spyController.handleInput();

        verify(gameMock).shoot(new Position(0, -1));
    }
    @Test
    public void testHandleInputQuit() throws IOException{
        Soldier soldierMock = mock(Soldier.class);
        when(gameMock.getSoldier()).thenReturn(soldierMock);
        when(soldierMock.getPosition()).thenReturn(new Position(5, 5));

        KeyStroke keyStrokeMock = mock(KeyStroke.class);
        when(screenMock.pollInput()).thenReturn(keyStrokeMock);

        when(keyStrokeMock.getKeyType()).thenReturn(KeyType.Character);
        when(keyStrokeMock.getCharacter()).thenReturn('q');

        gameController.handleInput();

        assertFalse(gameController.isRunning());
        verify(observerMock).changed(0);
        verify(gameMock).resetGame();
    }

    @Test
    public void testHandleInputPause() throws IOException{
        Soldier soldierMock = mock(Soldier.class);
        when(gameMock.getSoldier()).thenReturn(soldierMock);
        when(soldierMock.getPosition()).thenReturn(new Position(5, 5));

        KeyStroke keyStrokeMock = mock(KeyStroke.class);
        when(screenMock.pollInput()).thenReturn(keyStrokeMock);

        when(keyStrokeMock.getKeyType()).thenReturn(KeyType.Character);
        when(keyStrokeMock.getCharacter()).thenReturn('p');

        gameController.handleInput();

        assertFalse(gameController.isRunning());
        verify(observerMock).changed(0);
    }

    @Test
    public void testEndGame(){
        gameController.EndGame();

        assertFalse(gameController.isRunning());
        verify(observerMock).changed(3);
        verify(gameMock).resetGame();
    }

    @Test
    public void testRun() throws IOException {
        Soldier soldierMock = mock(Soldier.class);
        when(gameMock.getSoldier()).thenReturn(soldierMock);
        when(soldierMock.getPosition()).thenReturn(new Position(5, 5));

        KeyStroke keyStrokeMock = mock(KeyStroke.class);
        when(screenMock.pollInput()).thenReturn(keyStrokeMock);
        when(keyStrokeMock.getKeyType()).thenReturn(KeyType.Character);
        when(keyStrokeMock.getCharacter()).thenReturn('w');
        when(gameMock.canMoveTo(any(Position.class))).thenReturn(true);

        GameController spyController = spy(gameController);
        doReturn(true).doReturn(false).when(spyController).isRunning();

        spyController.run();
        verify(spyController, times(1)).handleInput();
    }

}
