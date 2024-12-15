package com.aor.ZombieZone.Model;
import com.googlecode.lanterna.screen.Screen;

import java.util.Arrays;
import java.util.List;

public class Menu {
    private List<String> entries = Arrays.asList("Start" , "Lead" , "Exit");
    private int currentEntry = 0;

    public Menu() {

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

    public boolean isSelectedExit() {
        return isSelected(2);
    }

    public boolean isSelectedStart() {
        return isSelected(0);
    }

    public boolean isSelectedLead() {
        return isSelected(1);
    }

    public int getNumberEntries() {
        return this.entries.size();
    }
}
