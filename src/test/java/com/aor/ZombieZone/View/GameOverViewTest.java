package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Model.GameOver;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class GameOverViewTest {
    private static final String GAME_OVER_TEXT = "GAME OVER";
    private static final String TRY_AGAIN = "Try Again";
    private static final String MENU = "Menu";
    private static final TextColor BACKGROUND_COLOR = TextColor.Factory.fromString("#000000");
    private static final TextColor GAME_OVER_COLOR = TextColor.Factory.fromString("#00FF00");
    private static final TextColor SELECTED_COLOR = TextColor.Factory.fromString("#FF0000");
    private static final TextColor DEFAULT_COLOR = TextColor.Factory.fromString("#FFFFFF");

    private GameOver gameOver;
    private GameOverView gameOverView;
    private TextGraphics textGraphics;

    @BeforeEach
    public void setUp() {
        gameOver = new GameOver(30, 21);
        gameOverView = new GameOverView(gameOver);
        textGraphics = Mockito.mock(TextGraphics.class);
    }

    @Test
    public void renderTest() {
        gameOverView.render(textGraphics);
        Mockito.verify(textGraphics, times(1)).setBackgroundColor(BACKGROUND_COLOR);
        Mockito.verify(textGraphics, times(1)).fill(' ');
        Mockito.verify(textGraphics, times(1)).setForegroundColor(GAME_OVER_COLOR);
        Mockito.verify(textGraphics, times(1)).putString(gameOver.getWidth() / 2 - GAME_OVER_TEXT.length() / 2,gameOver.getHeight() / 2 - 3, GAME_OVER_TEXT, SGR.BOLD);
        for (int i = 0; i < gameOver.getNumberEntries(); i++) {
            if (gameOver.isSelected(i)) {
                Mockito.verify(textGraphics, times(1)).setForegroundColor(SELECTED_COLOR);
            } else {
                Mockito.verify(textGraphics, times(1)).setForegroundColor(DEFAULT_COLOR);
            }
            String entry = gameOver.getEntry(i);
            Mockito.verify(textGraphics, times(1)).putString(gameOver.getWidth() / 2 - entry.length() / 2, gameOver.getHeight() / 2 + (3 * i), entry);
        }
    }
}
