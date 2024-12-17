package com.ZombieZoneTest.View;

import com.aor.ZombieZone.Model.GameOver;
import com.aor.ZombieZone.View.GameOverView;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
public class GameOverViewTest {
    private final String GAME_OVER_TEXT = "GAME OVER";
    private final TextColor BACKGROUND_COLOR = TextColor.Factory.fromString("#000000");
    private final TextColor GAME_OVER_COLOR = TextColor.Factory.fromString("#00FF00");
    private final TextColor SELECTED_COLOR = TextColor.Factory.fromString("#FF0000");
    private final TextColor DEFAULT_COLOR = TextColor.Factory.fromString("#FFFFFF");

    private GameOver gameOver;
    private GameOverView gameOverView;
    private TextGraphics textGraphics;

    @BeforeEach
    public void setUp() {
        gameOver = Mockito.mock(GameOver.class);
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

    @Test
    public void renderTest_Entries() {
        List<String> listOfEntries = List.of(
                "Restart",
                "Exit",
                "Submit Score"
        );

        Mockito.when(gameOver.getEntries()).thenReturn(listOfEntries);
        Mockito.when(gameOver.getNumberEntries()).thenReturn(listOfEntries.size());
        Mockito.when(gameOver.isSelected(0)).thenReturn(true);
        Mockito.when(gameOver.isSelected(1)).thenReturn(false);
        Mockito.when(gameOver.isSelected(2)).thenReturn(false);
        for (int i = 0; i < listOfEntries.size(); i++) {
            Mockito.when(gameOver.getEntry(i)).thenReturn(listOfEntries.get(i));
        }

        gameOverView.renderEntries(textGraphics);
        for(int i = 0; i<gameOver.getNumberEntries(); i++) {
            if(gameOver.isSelected(i)) {
                Mockito.verify(textGraphics, times(1)).setForegroundColor(SELECTED_COLOR);
            } else {
                Mockito.verify(textGraphics, times(2)).setForegroundColor(DEFAULT_COLOR);
            }
            int text2 = (gameOver.getEntry(i)).length();
            Mockito.verify(textGraphics, times(1)).putString(Mockito.anyInt(),Mockito.anyInt() , eq(listOfEntries.get(i)));

            }
        }
    @Test
    public void renderTest_EntriesNull(){
        List<String> ListOfEntries = new ArrayList<>();
        Mockito.when(gameOver.getEntries()).thenReturn(ListOfEntries);
        gameOverView.renderEntries(textGraphics);
        for(int i = 0; i<gameOver.getEntries().size(); i++) {
            if(gameOver.isSelected(i)) {
                Mockito.verify(textGraphics, times(0)).setForegroundColor(SELECTED_COLOR);
            }
            else{
                Mockito.verify(textGraphics, times(0)).setForegroundColor(DEFAULT_COLOR);
            }
            int text2 = (gameOver.getEntry(i)).length();
            Mockito.verify(textGraphics, times(0)).putString(Mockito.anyInt(),Mockito.anyInt() , gameOver.getEntry(i));
        }

    }
}

