package com.ZombieZoneTest.View;
import com.aor.ZombieZone.Model.Hud;
import com.aor.ZombieZone.Model.Round;
import com.aor.ZombieZone.Model.Score;
import com.aor.ZombieZone.Model.Soldier;
import com.aor.ZombieZone.View.HudView;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class HudViewTest {
    private Hud hud;
    private HudView hudView;
    private TextGraphics textGraphics;
    @BeforeEach
    public void setUp() {
        Score score = Mockito.mock(Score.class);
        Round round = Mockito.mock(Round.class);
        Soldier soldier = Mockito.mock(Soldier.class);

        hud = Mockito.mock(Hud.class);
        hudView = new HudView(hud);
        Mockito.when(hud.getScore()).thenReturn(score);
        Mockito.when(hud.getRound()).thenReturn(round);
        Mockito.when(hud.getSoldier()).thenReturn(soldier);

        textGraphics = Mockito.mock(TextGraphics.class);

    }
    @Test
    public void renderTest(){
        hudView.render(textGraphics);
        Mockito.verify(textGraphics , times(1)).setForegroundColor(TextColor.Factory.fromString("#0000FF"));
        Mockito.verify(textGraphics , times(1)).putString(0, hud.getHeight(), " Score:" + hud.getScore().getScore()+"  ");
        Mockito.verify(textGraphics , times(1)).putString(10, hud.getHeight(), "Round: " + hud.getRound().getRound()+"  ");
        Mockito.verify(textGraphics , times(1)).putString(20, hud.getHeight(), "Lives: " + hud.getSoldier().getLife()+"  ");
    }
}
