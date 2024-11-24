package com.aor.ZombieZone;
import com.aor.ZombieZone.Model.Arena;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyType;
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

public class Game {
    private Screen screen;
    private Arena arena;

    public Game() {
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
        } catch (URISyntaxException | FontFormatException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        arena = new Arena(30, 20);//mudei a titulo de teste
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run() {
        try {
            while (true) {
                draw();
                com.googlecode.lanterna.input.KeyStroke key = screen.readInput();
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    screen.close();
                    break;
                }
                if (key.getKeyType() == KeyType.EOF)
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
