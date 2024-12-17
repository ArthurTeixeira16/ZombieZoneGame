package com.aor.ZombieZone.View;

import com.aor.ZombieZone.Model.Menu;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class MenuViewTest {
    private Menu menu;
    private MenuView menuView;
    private TextGraphics textGraphics;
    private final TextColor BACKGROUNDCOLOR = TextColor.Factory.fromString("#000000");
    private final TextColor FOREGROUNDCOLOR = TextColor.Factory.fromString("#00FF00");
    private final String TEXT = "Zombie Zone";
    private final TextColor BACKGROUNDCOLOR2 = TextColor.Factory.fromString("#FF0000");
    private final TextColor FOREGROUNDCOLOR2 = TextColor.Factory.fromString("#FFFFFF");

    @BeforeEach
    public void SetUp(){
        menu = new Menu();
        menuView = new MenuView(menu);
        textGraphics = Mockito.mock(TextGraphics.class);
    }

    @Test
    public void renderTest(){
        menuView.render(textGraphics);

        Mockito.verify(textGraphics , times(1)).setBackgroundColor(BACKGROUNDCOLOR);
        Mockito.verify(textGraphics , times(1)).fill(' ');
        Mockito.verify(textGraphics , times(1)).setForegroundColor(FOREGROUNDCOLOR);
        Mockito.verify(textGraphics , times(1)).putString(10, 5, TEXT, SGR.BOLD);
        for(int i = 0; i< menu.getNumberEntries() ; i++){
        if (menu.isSelected(i)) {
            Mockito.verify(textGraphics , times(1)).setForegroundColor(BACKGROUNDCOLOR2);
        } else {
            Mockito.verify(textGraphics , times(2)).setForegroundColor(FOREGROUNDCOLOR2);
        }
            Mockito.verify(textGraphics , times(1)).putString(13, 8 + i * 3, menu.getEntry(i));
        }


    }
}
