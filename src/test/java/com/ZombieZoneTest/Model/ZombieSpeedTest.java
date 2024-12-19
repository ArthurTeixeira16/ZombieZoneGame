package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.ZombieSpeed;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class ZombieSpeedTest {
    private ZombieSpeed zombieSpeed;
    private TextGraphics textGraphics;
    private final TextColor FOREGROUNDCOLOR = TextColor.Factory.fromString("#0000ff");

    @BeforeEach
    public void setUp() {
        zombieSpeed = new ZombieSpeed(3,3);
        textGraphics = Mockito.mock(TextGraphics.class);
    }
    @Test
    public void drawTest(){
        zombieSpeed.draw(textGraphics);
        Mockito.verify( textGraphics, times(1)).setForegroundColor(FOREGROUNDCOLOR);
        Mockito.verify( textGraphics, times(1)).putString(new TerminalPosition(zombieSpeed.getPosition().getX() , zombieSpeed.getPosition().getY() ), "S");

    }
}
