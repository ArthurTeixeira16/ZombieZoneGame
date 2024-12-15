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

    public LeadBoardView(int width, int height, LeadBoard leadBoard) {
        terminalHeight = height;
        terminalWidth = width;
        this.leadBoard = leadBoard;
    }

    public void render(TextGraphics screen) {
        String text = "Leadboard";
        int xPosition = (terminalWidth - text.length()) / 2;
        int yPosition = 3;
        screen.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        screen.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        screen.putString(xPosition, yPosition, text, SGR.BOLD);
        List<Integer> leads = leadBoard.getListOfScore();
        for (int i = 0; i < linesToShow && i < leads.size(); i++) {
            int lead = i + 1;
            int score = leads.get(i);
            String leadStr = '#'+String.valueOf(lead);
            String scoreStr = String.valueOf(score);
            int yScorePosition = 5 +  (terminalHeight / 7) * i;
            int xScorePosition = (terminalWidth / 2)-2;
            int xLeadPosition = xScorePosition - text.length();
            screen.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            screen.putString(xLeadPosition, yScorePosition, leadStr, SGR.BOLD);
            screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            screen.putString(xScorePosition, yScorePosition, scoreStr, SGR.BOLD);
        }
    }
}