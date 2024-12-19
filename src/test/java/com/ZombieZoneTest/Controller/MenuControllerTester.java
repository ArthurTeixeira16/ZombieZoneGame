package com.ZombieZoneTest.Controller;

import com.aor.ZombieZone.Controller.MenuController;
import com.aor.ZombieZone.Model.Menu;
import com.aor.ZombieZone.State.StateObserver;
import com.aor.ZombieZone.View.MenuView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MenuControllerTester {
    private MenuController menuController;
    private MenuView menuViewMock;
    private Menu menuMock;
    private Screen screenMock;

    @BeforeEach
    public void setUp(){
        menuMock = mock(Menu.class);
        menuViewMock = mock(MenuView.class);
        screenMock = mock(Screen.class);
        menuController = new MenuController(menuMock,menuViewMock,screenMock);
    }

    @Test
    public void testIsRunning(){
        assertTrue(menuController.isRunning());
    }
    @Test
    public void testSetRunningTrueState(){
        menuController.setRunningFalse();
        assertFalse(menuController.isRunning());
        menuController.setRunningTrue();
        assertTrue(menuController.isRunning());
    }
    @Test
    public void testSetRunningFalseState(){
        menuController.setRunningTrue();
        assertTrue(menuController.isRunning());
        menuController.setRunningFalse();
        assertFalse(menuController.isRunning());
    }
    @Test
    public void testAddObserver(){
        StateObserver stateObserverMock = mock(StateObserver.class);
        menuController.addObserver(stateObserverMock);

        assertTrue(menuController.getObservers().contains(stateObserverMock));
    }
    @Test
    public void testGetObserver(){
        StateObserver stateObserverMock = mock(StateObserver.class);
        menuController.addObserver(stateObserverMock);
        List<StateObserver> observers = menuController.getObservers();

        assertEquals(1,observers.size());
        assertEquals(observers.getFirst(),stateObserverMock);
    }
    @Test
    public void testRun() throws IOException {
        MenuController spycontroller = spy(menuController);

        doReturn(true).doReturn(false).when(spycontroller).isRunning();

        doNothing().when(spycontroller).draw();
        doNothing().when(spycontroller).handleInput();

        assertDoesNotThrow(spycontroller::run);

        verify(spycontroller).draw();
        verify(spycontroller).handleInput();
    }
    @Test
    public void testDraw() throws IOException {
        MenuController spycontroller = spy(menuController);

        spycontroller.draw();

        verify(screenMock).clear();
        verify(menuViewMock).render(screenMock.newTextGraphics());
        verify(screenMock).refresh();
    }
    @Test
    public void testHandleInputArrowUp() throws IOException {
        MenuController spycontroller = spy(menuController);
        KeyStroke arrowUp = mock(KeyStroke.class);

        when(arrowUp.getKeyType()).thenReturn(KeyType.ArrowUp);
        when(screenMock.readInput()).thenReturn(arrowUp);

        spycontroller.handleInput();

        verify(menuMock).moveUp();
    }
    @Test
    public void testHandleInputArrowDown() throws IOException {
        MenuController spycontroller = spy(menuController);
        KeyStroke arrowDown = mock(KeyStroke.class);

        when(arrowDown.getKeyType()).thenReturn(KeyType.ArrowDown);
        when(screenMock.readInput()).thenReturn(arrowDown);

        spycontroller.handleInput();

        verify(menuMock).moveDown();
    }
    @Test
    public void testHandleInputEnterStart() throws IOException {
        MenuController spycontroller = spy(menuController);
        KeyStroke enter = mock(KeyStroke.class);
        StateObserver observerMock = mock(StateObserver.class);

        menuController.addObserver(observerMock);
        List<StateObserver> observers = spycontroller.getObservers();

        when(enter.getKeyType()).thenReturn(KeyType.Enter);
        when(screenMock.readInput()).thenReturn(enter);

        when(menuMock.isSelectedLead()).thenReturn(false);
        when(menuMock.isSelectedExit()).thenReturn(false);
        when(menuMock.isSelectedStart()).thenReturn(true);


        spycontroller.handleInput();
        verify(spycontroller, times(1)).setRunningFalse();
        verify(observers.getFirst()).changed(1);
        verify(menuMock).isSelectedStart();
    }
    @Test
    public void testHandleInputEnterLead() throws IOException {
        MenuController spycontroller = spy(menuController);
        KeyStroke enter = mock(KeyStroke.class);
        StateObserver observerMock = mock(StateObserver.class);

        menuController.addObserver(observerMock);
        List<StateObserver> observers = spycontroller.getObservers();

        when(enter.getKeyType()).thenReturn(KeyType.Enter);
        when(screenMock.readInput()).thenReturn(enter);

        when(menuMock.isSelectedLead()).thenReturn(true);
        when(menuMock.isSelectedExit()).thenReturn(false);
        when(menuMock.isSelectedStart()).thenReturn(false);

        spycontroller.handleInput();
        verify(spycontroller,times(1)).setRunningFalse();
        verify(observers.getFirst()).changed(2);
        verify(menuMock).isSelectedLead();
    }
}
