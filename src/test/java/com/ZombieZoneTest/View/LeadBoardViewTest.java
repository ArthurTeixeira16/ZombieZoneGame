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

public class LeadBoardViewTest {
    private final int LINESTOSHOW = 5;
    private final int TERMINALWIDTH = 30;
    private final int TERMINALHEIGHT = 21;
    private LeadBoardView leadBoardView;
    private LeadBoard leadBoard;
    private TextGraphics textGraphics;
    private final String TEXT = "Leadboard";
    private final TextColor FOREGROUNDCOLOR = TextColor.Factory.fromString("#00FF00");
    private final TextColor BACKGROUNDCOLOR = TextColor.Factory.fromString("#000000");
    private final TextColor FOREGROUNDCOLOR2 = TextColor.Factory.fromString("#FFFF00");
    private final TextColor BACKGROUNDCOLOR2 = TextColor.Factory.fromString("#FFFFFF");
    private final int XPOSITION = (TERMINALWIDTH - (TEXT.length())) / 2;
    private final int YPOSITION = 3;


    @BeforeEach
    public void setUp() {
        leadBoard = Mockito.mock(LeadBoard.class);

        List<Integer> listOfScores = List.of(
                300,
                200,
                154,
                23,
                19,
                2
        );
        Mockito.when(leadBoard.getListOfScore()).thenReturn(listOfScores);


        leadBoardView = new LeadBoardView( TERMINALWIDTH , TERMINALHEIGHT ,leadBoard);
        textGraphics = Mockito.mock(TextGraphics.class);
    }
    @Test
    public void RenderTest(){
        leadBoardView.render(textGraphics);

        Mockito.verify(textGraphics , times(1)).setForegroundColor(FOREGROUNDCOLOR);
        Mockito.verify(textGraphics , times(1)).setBackgroundColor(BACKGROUNDCOLOR);
        Mockito.verify(textGraphics , times(1)).putString(XPOSITION, YPOSITION, TEXT, SGR.BOLD);
        for(int i = 0; i<LINESTOSHOW && i < (leadBoard.getListOfScore()).size(); i++){
            String leadStr = '#'+String.valueOf(i+1);
            String scoreStr = String.valueOf(leadBoard.getListOfScore().get(i));
            int yScorePosition = 5 +  (TERMINALHEIGHT / 7) * i;
            int xScorePosition = (TERMINALWIDTH / 2)-2;
            int xLeadPosition = xScorePosition - TEXT.length();
            Mockito.verify(textGraphics, times(5)).setForegroundColor(FOREGROUNDCOLOR2);
            Mockito.verify(textGraphics, times(1)).putString(xLeadPosition, yScorePosition, leadStr, SGR.BOLD);
            Mockito.verify(textGraphics, times(5)).setForegroundColor(BACKGROUNDCOLOR2);
            Mockito.verify(textGraphics, times(1)).putString(xScorePosition, yScorePosition, scoreStr, SGR.BOLD);
        }
    }
}
