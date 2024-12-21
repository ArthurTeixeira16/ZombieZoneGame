package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

public class MenuTester {
    private Menu menu;
    private Field entriesfild;

    @BeforeEach
    public void setUp() throws IllegalAccessException, NoSuchFieldException {
        //Entries diferentes para podermos testar
        List<String> entries = List.of(
                "Começar",
                "Pizzeria",
                "Porto",
                "Lisbon",
                "Exit"
        );
        menu = spy(new Menu());// começa no Start vermelho
        entriesfild = Menu.class.getDeclaredField("entries");
        entriesfild.setAccessible(true);
        entriesfild.set(menu , entries);
    }

    @Test
    public void menuTest() {
        assertEquals(menu.getNumberEntries(), 5);
    }
    @Test
    public void movesTest() {
        assertFalse(menu.isSelected(1));
        assertEquals(menu.getEntry(menu.getCurrentEntry()),"Começar");
        assertTrue(menu.isSelected(0));
        menu.moveDown();
        assertTrue(menu.isSelected(1));
        menu.moveDown();
        assertTrue(menu.isSelected(2));
        menu.moveUp();
        assertTrue(menu.isSelected(1));
    }
    @Test
    public void moveUplimit() {
        menu.moveUp();

        assertTrue(menu.isSelected(menu.getEntries().size()-1));
    }
    @Test
    public void moveDownLimit() {
        menu.moveDown();
        menu.moveDown();
        menu.moveDown();
        menu.moveDown();
        assertTrue(menu.isSelected(menu.getEntries().size()-1));
        menu.moveDown();
        assertTrue(menu.isSelected(0));
    }
    @Test
    public void moveDefault() throws IllegalAccessException {
        List<String> defaults = List.of(
                "Start", "Lead", "Exit"
        );
        entriesfild.set(menu, defaults);
        assertTrue(menu.isSelectedStart());
        menu.moveUp();
        assertTrue(menu.isSelectedExit());
        menu.moveUp();
        assertTrue(menu.isSelectedLead());

    }

}
