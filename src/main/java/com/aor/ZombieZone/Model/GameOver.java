package com.aor.ZombieZone.Model;

import java.util.ArrayList;
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
        if (currentEntry > getEntries().size() - 1)
            currentEntry = 0;
    }

    public void moveUp() {
        currentEntry--;
        if (currentEntry < 0) currentEntry = getEntries().size() - 1;
    }

    public String getEntry(int i) {
        return getEntries().get(i);
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
        if(getEntries() !=null) {
        return getEntries().size(); }
        return 0;
    }

    public List<String> getEntries() {
        if(entries == null) {
            entries = new ArrayList<>();
        }
        return entries;
    }
}
