package com.ZombieZoneTest.Controller;

import com.aor.ZombieZone.Controller.GameController;
import com.aor.ZombieZone.Model.Game;
import com.aor.ZombieZone.View.GameView;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class GameControllerTester {
    private GameController gameController;
    private Game gameMock;
    private GameView gameviewMock;
    private Screen screenMock;

    @BeforeEach
    public void setUp(){
        gameMock = mock(Game.class);
        gameviewMock = mock(GameView.class);
        screenMock = mock(Screen.class);
        gameController = new GameController(gameMock,gameviewMock,screenMock);
    }
    @Test
    public void testDraw() throws IOException{
        GameController spycontroller = spy(gameController);

        spycontroller.draw();

        verify(screenMock).clear();
        verify(gameviewMock).render(screenMock.newTextGraphics());
        verify(screenMock).refresh();
    }
    @Test
    public void testIsRunning(){
        assertTrue(gameController.isRunning());
    }
    @Test
    public void testThreadIsRunning(){}
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
}
