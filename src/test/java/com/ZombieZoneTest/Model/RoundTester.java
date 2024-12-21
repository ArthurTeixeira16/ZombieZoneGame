package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.Round;
import com.aor.ZombieZone.Model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoundTester {
    private Round round;
    @BeforeEach
    public void setUp() { round = new Round();}
    @Test
    public void ScoreGetterTest() {
        assertEquals(1, round.getRound());
    }
    @Test
    public void addScoreTest() {
        round.nextRound();
        assertEquals(2, round.getRound());
    }
}
