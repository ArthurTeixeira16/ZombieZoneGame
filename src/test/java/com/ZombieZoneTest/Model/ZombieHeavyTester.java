package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.ZombieHeavy;
import com.aor.ZombieZone.Model.ZombieSpeed;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class ZombieHeavyTester {
    private ZombieHeavy zombieHeavy;
    private TextGraphics textGraphics;
    private final TextColor FOREGROUNDCOLOR = TextColor.Factory.fromString("#4B5320");

    @BeforeEach
    public void setUp() {
        zombieHeavy = new ZombieHeavy(3,3);
        textGraphics = Mockito.mock(TextGraphics.class);
    }
    @Test
    public void drawTest(){
        zombieHeavy.draw(textGraphics);
        Mockito.verify( textGraphics, times(1)).setForegroundColor(FOREGROUNDCOLOR);
        Mockito.verify( textGraphics, times(1)).putString(new TerminalPosition(zombieHeavy.getPosition().getX() , zombieHeavy.getPosition().getY() ), "T");

    }
}
