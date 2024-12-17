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

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

public class HudViewTest {
    private Hud hud;
    private HudView hudView;
    private TextGraphics textGraphics;
    private final TextColor FOREGROUNDCOLOR = TextColor.Factory.fromString("#0000FF");

    @BeforeEach
    public void setUp() {

        hud = Mockito.mock(Hud.class);
        hudView = new HudView(hud);
        textGraphics = Mockito.mock(TextGraphics.class);

    }
    @Test
    public void renderTest_ValidAll(){
        Mockito.when(hud.getScore()).thenReturn(Mockito.mock(Score.class));
        Mockito.when(hud.getRound()).thenReturn(Mockito.mock(Round.class));
        Mockito.when(hud.getSoldier()).thenReturn(Mockito.mock(Soldier.class));

        Mockito.when(hud.getScore().getScore()).thenReturn(100);
        Mockito.when(hud.getRound().getRound()).thenReturn(60);
        Mockito.when(hud.getSoldier().getLife()).thenReturn(5);

        hudView.render(textGraphics);

        Mockito.verify(textGraphics , times(1)).setForegroundColor(FOREGROUNDCOLOR);
        Mockito.verify(textGraphics , times(1)).putString(0, hud.getHeight(), "Score: 100  ");
        Mockito.verify(textGraphics , times(1)).putString(10,hud.getHeight(), "Round: 60  ");
        Mockito.verify(textGraphics , times(1)).putString(20, hud.getHeight(), "Lives: 5  ");
    }
}
