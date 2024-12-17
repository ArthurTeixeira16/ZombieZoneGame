package com.ZombieZoneTest.View;

import com.aor.ZombieZone.Model.GameOver;
import com.aor.ZombieZone.View.GameOverView;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

//ERRADO
public class GameOverViewTest {
    private final String GAME_OVER_TEXT = "GAME OVER";
    private final String TRY_AGAIN = "Try Again";
    private final String MENU = "Menu";
    private final TextColor BACKGROUND_COLOR = TextColor.Factory.fromString("#000000");
    private final TextColor GAME_OVER_COLOR = TextColor.Factory.fromString("#00FF00");
    private final TextColor SELECTED_COLOR = TextColor.Factory.fromString("#FF0000");
    private final TextColor DEFAULT_COLOR = TextColor.Factory.fromString("#FFFFFF");

    private GameOver gameOver;
    private GameOverView gameOverView;
    private TextGraphics textGraphics;

    @BeforeEach
    public void setUp() {
        gameOver = new GameOver(30, 21);
        List<String> listOfEntries = List.of(
                "Restart",
                "Exit",
                "Submit Score"
        );
        when(gameOver.getEntries()).thenReturn(listOfEntries);
        gameOverView = new GameOverView(gameOver);
        textGraphics = Mockito.mock(TextGraphics.class);
    }

    @Test
    public void renderTest_GameOver() {
        gameOverView.render(textGraphics);
        Mockito.verify(textGraphics, times(1)).setBackgroundColor(BACKGROUND_COLOR);
        Mockito.verify(textGraphics, times(1)).fill(' ');
        Mockito.verify(textGraphics, times(1)).setForegroundColor(GAME_OVER_COLOR);
        Mockito.verify(textGraphics, times(1)).putString(gameOver.getWidth() / 2 - GAME_OVER_TEXT.length() / 2,gameOver.getHeight() / 2 - 3, GAME_OVER_TEXT, SGR.BOLD);
    }
}
