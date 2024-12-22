package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Model.GameOver;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class GameOverView {
    private GameOver gameOver;

    public GameOverView( GameOver gameOver ) {
        this.gameOver = gameOver;
    }

    public void render(TextGraphics screen) {
        int text = ("GAME OVER").length();
        screen.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        screen.fill(' ');
        screen.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        screen.putString(gameOver.getWidth()/2-text/2, gameOver.getHeight()/2-3, "GAME OVER", SGR.BOLD);
        renderEntries(screen);
    }

    public void renderEntries(TextGraphics screen) {
        for (int i = 0; i < gameOver.getNumberEntries(); i++) {
            if (gameOver.isSelected(i)) {
                screen.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
            } else {
                screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            }
            int text2 = (gameOver.getEntry(i)).length();
            screen.putString(gameOver.getWidth()/2-text2/2,gameOver.getHeight()/2+(3*i) , gameOver.getEntry(i));
        }
    }
}
