package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.Game;
import com.aor.ZombieZone.Model.Hud;
import com.aor.ZombieZone.Model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HudTester {
    private Hud hud;
    private Game game;
    @BeforeEach
    public void setUp() {
        game = Mockito.mock(Game.class);
        hud = new Hud(game);
    }
    @Test
    public void ScoreGetterTest() {
        assertEquals(new Score().getScore(),hud.getScore().getScore());
    }

}
