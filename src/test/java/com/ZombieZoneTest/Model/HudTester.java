package com.ZombieZoneTest.Model;
import com.aor.ZombieZone.Model.*;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;

public class HudTester {
    private Soldier soldier;
    private Arena arena;
    private Hud hud;
    private TextGraphics textGraphics;
    private Score score;
    private Round round;
    @BeforeEach
    public void setUp() {
        soldier = Mockito.mock(Soldier.class);
        arena = Mockito.mock(Arena.class);
        textGraphics = Mockito.mock(TextGraphics.class);
        hud = new Hud(soldier,arena,score,round);
        hud.draw(textGraphics);
    }
    @Test
    public void testDraw() {
        Mockito.verify(textGraphics).setForegroundColor(Mockito.eq(TextColor.Factory.fromString("#0000FF")));
        /*Mockito.verify(textGraphics).putString(Mockito.eq(new TerminalPosition(1, arena.getHeight())), Mockito.eq("Score: " + hud.score));
        Mockito.verify(textGraphics).putString(Mockito.eq(new TerminalPosition(1, arena.getHeight())), Mockito.eq("Score: " + hud.score));
        Mockito.verify(textGraphics).putString(Mockito.eq(new TerminalPosition(1, arena.getHeight())), Mockito.eq("Score: " + hud.score));
        Mockito.verify(textGraphics).putString(Mockito.eq(new TerminalPosition(1, arena.getHeight())), Mockito.eq("Score: " + hud.score));*/

    }
}
