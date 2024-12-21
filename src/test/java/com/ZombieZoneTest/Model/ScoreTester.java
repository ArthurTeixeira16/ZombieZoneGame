package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTester {
    private Score score;
    @BeforeEach
    public void setUp() { score = new Score();}
    @Test
    public void ScoreGetterTest() {
        assertEquals(0, score.getScore());
    }
    @Test
    public void addScoreTest() {
        score.addScore();
        assertEquals(1, score.getScore());
    }
}
