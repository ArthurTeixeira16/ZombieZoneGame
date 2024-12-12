package com.aor.ZombieZone.View;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.aor.ZombieZone.Controller.LeadBoardController.getMapDoLead;

public class LeadBoardView {
    private int scrollPosition = 0;
    private int linesToShow = 5;
    private int terminalWidth = 30;
    private int terminalHeight = 20;
    public void render(TextGraphics screen) {
        String text = "Leadboard";
        int xPosition = (terminalWidth - text.length()) / 2;
        int yPosition = 3;

        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        screen.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        screen.putString(xPosition, yPosition, text, SGR.BOLD);

        Map<Integer, String> leads = getMapDoLead();
        //Queremos apenas os Keys em uma list pq
        // acessamos o Map como bilioteca do python, com o key,
        // então as long nós temos o key temos o value
        List<Integer> keys = new ArrayList<>(leads.keySet());
        for(int i = 0; i<linesToShow && scrollPosition + i < leads.size() ; i++) {
            String lead = leads.get(keys.get(scrollPosition + i));
            int scoree = keys.get(scrollPosition + i);
            String scoreestr = String.valueOf(scoree);

            screen.putString(xPosition - text.length(), (int) (5+(((double)terminalHeight/4) * i)), scoreestr, SGR.BOLD);
            screen.putString(xPosition + text.length(), (int) (5+(((double)terminalHeight/4) * i)), lead);
        }
    }
    public int getScrollPosition() {
        return scrollPosition;
    }
    public void scrollUp() {
        if (scrollPosition > 0) {
            scrollPosition--;
        }
    }
    public void scrollDown() {
        if (scrollPosition + linesToShow < getMapDoLead().size()) {
            scrollPosition++;
        }
    }
}
