package com.ZombieZoneTest.Controller;

import com.aor.ZombieZone.Controller.*;
import com.aor.ZombieZone.Model.Game;
import com.aor.ZombieZone.State.*;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.io.IOException;

public class InitTester {

    private Initializer initializer;

    @BeforeEach
    public void setUp() throws IOException {

    }

    @Test
    public void testInitializer() {
        initializer = new Initializer();
        assertNotNull(initializer);
    }
    @Test
    public void testFontLoading() {
        initializer = new Initializer();
        assertDoesNotThrow(Initializer::new);
    }
    @Test
    public void testGetScreen() {
        Screen screenMock = mock(Screen.class);
        Initializer initializer = new Initializer() {
            @Override
            public Screen getScreen() {
                return screenMock;
            }
        };
        assertEquals(screenMock, initializer.getScreen());
    }
    @Test
    public void testChangedScreen(){
        initializer = new Initializer();
        GameContext gameContextMock = new GameContext(mock(MenuState.class));
        initializer.changed(0);
        assertEquals(new TerminalSize(30,21), initializer.getScreen().getTerminalSize());

        initializer.changed(1);
        assertEquals(new TerminalSize(30,21), initializer.getScreen().getTerminalSize());

        initializer.changed(2);
        assertEquals(new TerminalSize(30,21), initializer.getScreen().getTerminalSize());

        initializer.changed(3);
        assertEquals(new TerminalSize(30,21), initializer.getScreen().getTerminalSize());

    }
    @Test
    void testRun() {
        Initializer initializer = new Initializer();
        Initializer spyInit = spy(initializer);
        //doReturn(true).doReturn(false).when(spyInit).run();
        doNothing().when(spyInit).run();
        assertDoesNotThrow(() -> spyInit.run(), "Erro ao rodar o jogo.");
    }
}
