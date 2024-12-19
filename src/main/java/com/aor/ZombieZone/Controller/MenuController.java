package com.aor.ZombieZone.Controller;

import com.aor.ZombieZone.Model.Menu;
import com.aor.ZombieZone.State.StateObserver;
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
        List<StateObserver> observers = new ArrayList<>();

        public MenuController(Menu menu, MenuView menuView, Screen screen) {
            this.menuView = menuView;
            this.screen = screen;
            this.menu = menu;
        }
        public void addObserver(StateObserver observer) {
            observers.add(observer);
        }

        public List<StateObserver> getObservers() {
            return observers;
        }

        public void run() {
                try{
                    while (isRunning()) {
                        draw();
                        handleInput();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        public boolean isRunning() {
            return running;
        }

        public void setRunningTrue() {
            running = true;
        }

        public void setRunningFalse() {running = false;}

        public void draw() throws IOException {
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
                    setRunningFalse();
                }
                if(menu.isSelectedLead()){
                    observers.getFirst().changed(2);
                    setRunningFalse();
                }
            }
    }
}
