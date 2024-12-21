package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.Game;
import com.aor.ZombieZone.Model.GameOver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

public class GameOverTester {
    private GameOver gameOver;
    private Field entriesfild;

    @BeforeEach
    public void setUp() throws IllegalAccessException, NoSuchFieldException {
        //Entries diferentes para podermos testar
        List<String> entries = List.of(
                "Recomeçar",
                "tentar denovo",
                "ir para santarém",
                "boa noite",
                "menu"
        );
        gameOver = spy(new GameOver(40,41));// começa no Start vermelho
        entriesfild = GameOver.class.getDeclaredField("entries");
        entriesfild.setAccessible(true);
        entriesfild.set(gameOver , entries);
    }

    @Test
    public void setGameOver() {
        assertEquals(gameOver.getNumberEntries(), 5);
        assertEquals(gameOver.getWidth(), 40);
        assertEquals(gameOver.getHeight(), 41);
    }
    @Test
    public void movesTest() {
        assertFalse(gameOver.isSelected(1));
        assertEquals(gameOver.getEntry(gameOver.getCurrentEntry()),"Recomeçar");
        assertTrue(gameOver.isSelected(0));
        gameOver.moveDown();
        assertTrue(gameOver.isSelected(1));
        gameOver.moveDown();
        assertTrue(gameOver.isSelected(2));
        gameOver.moveUp();
        assertTrue(gameOver.isSelected(1));
    }
    @Test
    public void moveUplimit() {
        gameOver.moveUp();

        assertTrue(gameOver.isSelected(gameOver.getEntries().size()-1));
    }
    @Test
    public void moveDownLimit() {
        gameOver.moveDown();
        gameOver.moveDown();
        gameOver.moveDown();
        gameOver.moveDown();
        assertTrue(gameOver.isSelected(gameOver.getEntries().size()-1));
        gameOver.moveDown();
        assertTrue(gameOver.isSelected(0));
    }
    @Test
    public void moveDefault() throws IllegalAccessException {
        List<String> defaults = List.of(
                "Try Again", "Menu"
        );
        entriesfild.set(gameOver, defaults);
        assertTrue(gameOver.isSelectedTryAgain());
        gameOver.moveUp();
        assertTrue(gameOver.isSelectedMenu());
        gameOver.moveUp();
        assertTrue(gameOver.isSelectedTryAgain());

    }

}
