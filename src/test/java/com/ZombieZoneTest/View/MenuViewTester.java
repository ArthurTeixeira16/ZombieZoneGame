package com.ZombieZoneTest.View;

import com.aor.ZombieZone.Model.Menu;
import com.aor.ZombieZone.View.MenuView;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

public class MenuViewTester {
    private Menu menu;
    private MenuView menuView;
    private TextGraphics textGraphics;
    private final TextColor BACKGROUNDCOLOR = TextColor.Factory.fromString("#000000");
    private final TextColor FOREGROUNDCOLOR = TextColor.Factory.fromString("#00FF00");
    private final String TEXT = "Zombie Zone";
    private final TextColor BACKGROUNDCOLOR2 = TextColor.Factory.fromString("#FFFFFF");
    private final TextColor FOREGROUNDCOLOR2 = TextColor.Factory.fromString("#FF0000");

    @BeforeEach
    public void SetUp(){
        menu = Mockito.mock(Menu.class);
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
    }
    @Test
    public void renderTestEntries_VallidAll(){
        List<String> ListOfEntries = List.of("Online", "Offline" ,"Byebye i mogged u skibidi");
        Mockito.when(menu.getEntries()).thenReturn(ListOfEntries);
        Mockito.when(menu.getNumberEntries()).thenReturn(ListOfEntries.size());
        Mockito.when(menu.isSelected(0)).thenReturn(true);
        Mockito.when(menu.isSelected(1)).thenReturn(false);
        Mockito.when(menu.isSelected(2)).thenReturn(false);
        for (int i = 0; i < ListOfEntries.size(); i++) {
            Mockito.when(menu.getEntry(i)).thenReturn(ListOfEntries.get(i));
        }

        menuView.renderEntries(textGraphics);

        for(int i = 0; i< ListOfEntries.size(); i++) {
            if(menu.isSelected(i)) {
                Mockito.verify(textGraphics, times(1)).setForegroundColor(FOREGROUNDCOLOR2);
            }
            else {
                Mockito.verify(textGraphics, times(2)).setForegroundColor(BACKGROUNDCOLOR2);
            }
            Mockito.verify(textGraphics, times(1)).putString(Mockito.anyInt(), Mockito.anyInt(), eq(ListOfEntries.get(i)));
        }
    }

    @Test
    public void renderTestEntries_Null(){
        List<String> ListOfEntries = List.of();
        Mockito.when(menu.getEntries()).thenReturn(ListOfEntries);
        menuView.renderEntries(textGraphics);
        Mockito.verify(textGraphics, times(0)).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        Mockito.verify(textGraphics, times(0)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(textGraphics, times(0)).putString(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString());

    }
}
