package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Model.LeadBoard;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class LeadBoardView {
    private int linesToShow = 5;
    private int terminalWidth;
    private int terminalHeight;
    private LeadBoard leadBoard;
    public LeadBoardView(int height, int width, LeadBoard leadBoard) {
        terminalHeight = height;
        terminalWidth = width;
        this.leadBoard = leadBoard;
    }
    public void render(TextGraphics screen) {
        String text = "Leadboard";
        int xPosition = (terminalWidth - text.length()) / 2;
        int yPosition = 3;

        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        screen.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        screen.putString(xPosition, yPosition, text, SGR.BOLD);

        List<Integer> leads = leadBoard.getListOfScore();
        for(int i = 0; i< linesToShow && i < leads.size()  ; i++) {
            int lead = i+1;
            int scoree = leads.get(i);
            String scoreestr = String.valueOf(scoree);
            String leadstr = String.valueOf(lead);

            screen.putString(xPosition + text.length(), (int) (5+(((double)terminalHeight/7) * i)), scoreestr, SGR.BOLD);
            screen.putString(xPosition - text.length(), (int) (5+(((double)terminalHeight/7) * i)), leadstr, SGR.BOLD);
        }
    }
}