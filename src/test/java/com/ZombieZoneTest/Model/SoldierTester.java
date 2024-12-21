package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.Soldier;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

public class SoldierTester {
    private Soldier soldier;
    private TextGraphics textGraphics;
    private final TextColor FOREGROUNDCOLOR = TextColor.Factory.fromString("#000000");
    @BeforeEach
    private void SetUp() {
        soldier = new Soldier(2,2);
        textGraphics = Mockito.mock(TextGraphics.class);
    }


    @Test
    public void drawTest(){
        soldier.draw(textGraphics);
        Mockito.verify(textGraphics , times(1)).setForegroundColor(FOREGROUNDCOLOR);
        Mockito.verify(textGraphics , times(1)).putString(new TerminalPosition(soldier.getPosition().getX(), soldier.getPosition().getY()),"@");
    }
}
