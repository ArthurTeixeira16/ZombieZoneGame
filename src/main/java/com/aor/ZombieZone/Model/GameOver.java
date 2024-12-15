package com.aor.ZombieZone.Model;
import com.googlecode.lanterna.screen.Screen;

import java.util.Arrays;
import java.util.List;

public class GameOver {
    private int height;
    private int width;
    private List<String> entries = Arrays.asList("Try Again" , "Menu");
    private int currentEntry = 0;

    public GameOver(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public void moveDown() {
        currentEntry++;
        if (currentEntry > this.entries.size() - 1)
            currentEntry = 0;
    }

    public void moveUp() {
        currentEntry--;
        if (currentEntry < 0) currentEntry = this.entries.size() - 1;
    }

    public String getEntry(int i) {
        return entries.get(i);
    }

    public boolean isSelected(int i) {
        return currentEntry == i;
    }

    public boolean isSelectedTryAgain() {
        return isSelected(0);
    }

    public boolean isSelectedMenu() {
        return isSelected(1);
    }

    public int getNumberEntries() {
        return this.entries.size();
    }
}
