package com.aor.ZombieZone.Controller;

import com.aor.ZombieZone.Model.Menu;
import com.aor.ZombieZone.StatsObserver;
import com.aor.ZombieZone.View.MenuView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuController {
        private MenuView menuView;
        private Screen screen;
        private Menu menu;
        private boolean running = true;
        List<StatsObserver> observers = new ArrayList<>();
        public MenuController(Menu menu, MenuView menuView, Screen screen) {
            this.menuView = menuView;
            this.screen = screen;
            this.menu = menu;
        }
        public void addControllerObserver(StatsObserver observer) {
            observers.add(observer);
        }

        public void run() {
            try{
                while (running) {
                    draw();
                    handleInput();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void setRunningTrue() {
            running = true;
        }

        private void draw() throws IOException {
            screen.clear();
            menuView.render(screen.newTextGraphics());
            screen.refresh();
        }

        public void handleInput() throws IOException {
            KeyStroke key = screen.readInput();

            if(key.getKeyType()== KeyType.ArrowUp){
                menu.moveUp();
            } else if (key.getKeyType()==KeyType.ArrowDown) {
                menu.moveDown();

            } else if (key.getKeyType()==KeyType.Enter) {
                if (menu.isSelectedExit()) {
                    screen.close();
                    System.exit(0);
                }
                if (menu.isSelectedStart()){
                    observers.getFirst().changed(1);
                    running = false;
                }
                if(menu.isSelectedLead()){
                    observers.getFirst().changed(2);
                    running = false;
                }
            }
    }
}
