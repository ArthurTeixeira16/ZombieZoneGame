package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Model.*;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;


import java.util.List;

public class GameOverView {
    private GameOver gameOver;
    /*private char[][] letters;
    private TextColor[][] foregroundcolor;
    private TextColor[][] backgroundcolor;*/

    public GameOverView( GameOver gameOver ) {
        this.gameOver = gameOver;
    }
    /*
    public void captureScreen(Screen screen) {
        int height = gameOver.getHeight();
        int width = gameOver.getHeight();
        letters = new char[height][width];
        foregroundcolor = new TextColor[height][width];
        backgroundcolor =  new TextColor[height][width];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                var cell = screen.getBackCharacter(j , i);
                letters[i][j] = cell.getCharacter();
                foregroundcolor[i][j] = cell.getForegroundColor();
                backgroundcolor[i][j] = cell.getBackgroundColor();
            }
        }
    }*/

    public void render(TextGraphics screen) {
       /* for(int i = 0; i < gameOver.getHeight(); i++) {
            for(int j = 0; j < gameOver.getWidth(); j++) {
                if(foregroundcolor[i][j] != null) {
                    screen.setForegroundColor(foregroundcolor[i][j]);
                }
                if(backgroundcolor[i][j] != null) {
                    screen.setBackgroundColor(backgroundcolor[i][j]);
                }
                if(letters[i][j] != '\0') {
                    screen.putString(i, j, String.valueOf(letters[i][j]));
                }
            }
        }*/
        int text = ("GAME OVER").length();
        screen.putString(gameOver.getWidth()/2-text/2, gameOver.getHeight()/18, "GAME OVER", SGR.BOLD);
        for (int i = 0; i < gameOver.getNumberEntries(); i++) {
            if (gameOver.isSelected(i)) {
                screen.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
            } else {
                screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            }
            int text2 = (gameOver.getEntry(i)).length();
            screen.putString(gameOver.getWidth()/2-text2/2,gameOver.getHeight()/18+ 3*i , gameOver.getEntry(i));
        }

    }
}
