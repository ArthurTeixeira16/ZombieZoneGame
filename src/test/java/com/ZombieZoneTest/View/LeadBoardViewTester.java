package com.ZombieZoneTest.View;

import com.aor.ZombieZone.Model.LeadBoard;
import com.aor.ZombieZone.View.LeadBoardView;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.times;

public class LeadBoardViewTester {
    private final int LINESTOSHOW = 5;
    private final int TERMINALWIDTH = 30;
    private final int TERMINALHEIGHT = 21;
    private final String TEXT = "Leadboard";
    private final TextColor FOREGROUNDCOLOR = TextColor.Factory.fromString("#00FF00");
    private final TextColor BACKGROUNDCOLOR = TextColor.Factory.fromString("#000000");
    private final TextColor FOREGROUNDCOLOR2 = TextColor.Factory.fromString("#FFFF00");
    private final TextColor BACKGROUNDCOLOR2 = TextColor.Factory.fromString("#FFFFFF");
    private LeadBoard leadBoard;
    private LeadBoardView leadBoardView;
    private TextGraphics textGraphics;

    @BeforeEach
    public void setUp() {
        leadBoard = Mockito.mock(LeadBoard.class);

        leadBoardView = new LeadBoardView(TERMINALWIDTH, TERMINALHEIGHT, leadBoard);
        textGraphics = Mockito.mock(TextGraphics.class);
    }

    @Test
    public void renderTest() {
        leadBoardView.render(textGraphics);
        Mockito.verify(textGraphics, times(1)).setForegroundColor(FOREGROUNDCOLOR);
        Mockito.verify(textGraphics, times(1)).setBackgroundColor(BACKGROUNDCOLOR);
        Mockito.verify(textGraphics, times(1)).putString(
                (TERMINALWIDTH - TEXT.length()) / 2,
                3,
                TEXT,
                SGR.BOLD
        );
    }
    @Test
    public void renderLeads() {
        List<Integer> listOfScores = List.of(300, 200, 154, 23, 19, 2);
        Mockito.when(leadBoard.getListOfScore()).thenReturn(listOfScores);
        leadBoardView.renderLeads(textGraphics);

        for (int i = 0; i < LINESTOSHOW && i < leadBoard.getListOfScore().size(); i++) {
            String leadStr = "#" + (i + 1);
            String scoreStr = String.valueOf(leadBoard.getListOfScore().get(i));
            int yScorePosition = 5 + (TERMINALHEIGHT / 7) * i;
            int xScorePosition = (TERMINALWIDTH / 2) - 2;
            int xLeadPosition = xScorePosition - TEXT.length();

            Mockito.verify(textGraphics, times(5)).setForegroundColor(FOREGROUNDCOLOR2);
            Mockito.verify(textGraphics, times(1)).putString(xLeadPosition, yScorePosition, leadStr, SGR.BOLD);
            Mockito.verify(textGraphics, times(5)).setForegroundColor(BACKGROUNDCOLOR2);
            Mockito.verify(textGraphics, times(1)).putString(xScorePosition, yScorePosition, scoreStr, SGR.BOLD);
        }
    }

    @Test
    public void renderTest_LeadBoardNull() {
        List<Integer> listOfScores = List.of();
        Mockito.when(leadBoard.getListOfScore()).thenReturn(listOfScores);

        Mockito.verify(textGraphics, times(0)).putString(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.any());
    }
}
