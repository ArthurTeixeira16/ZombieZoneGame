package com.aor.ZombieZone;
import com.aor.ZombieZone.Controller.LeadBoardController;
import com.aor.ZombieZone.Controller.MenuController;
import com.aor.ZombieZone.View.LeadBoardView;
import com.aor.ZombieZone.View.MenuView;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.aor.ZombieZone.Controller.LeadBoardController;

public class Menu {
    private Screen screen;
    private List<String> entries;
    private int currentEntry = 0;
    private MenuView menuView;
    private MenuController menuController;
    public Menu() {
        try {
            URL resource = getClass().getClassLoader().getResource("square.ttf");
            if (resource == null) {
                throw new RuntimeException("Font resource not found!");
            }
            File fontFile = new File(resource.toURI());
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            DefaultTerminalFactory factory = new DefaultTerminalFactory();
            Font loadedFont = font.deriveFont(Font.PLAIN, 25);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
            factory.setTerminalEmulatorFontConfiguration(fontConfig);
            factory.setForceAWTOverSwing(true);
            factory.setInitialTerminalSize(new TerminalSize(40, 40));
            Terminal terminal = factory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            this.entries = Arrays.asList("Start", "Exit", "Lead");
            menuView = new MenuView(this);
            menuController= new MenuController(this,menuView,screen);

            LeadBoardView leadBoardView = new LeadBoardView();
            LeadBoardController leadBoardController = new LeadBoardController(leadBoardView, screen);

        } catch (URISyntaxException | FontFormatException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void run() {
        menuController.run();
    }


    public void moveDown() {
        currentEntry++;
        if (currentEntry > this.entries.size() - 1)
            currentEntry = 0;
    }

    public void moveUp() {
        currentEntry--;
        if (currentEntry < 0)
            currentEntry = this.entries.size() - 1;
    }

    public String getEntry(int i) {
        return entries.get(i);
    }

    public boolean isSelected(int i) {
        return currentEntry == i;
    }

    public boolean isSelectedExit() {
        return isSelected(1);
    }

    public boolean isSelectedStart() {
        return isSelected(0);
    }

    public boolean isSelectedLead() {return isSelected(2);}

    public int getNumberEntries() {
        return this.entries.size();
    }
}
