package com.aor.ZombieZone.Controller;

import com.aor.ZombieZone.Menu;
import com.aor.ZombieZone.Model.Game;
import com.aor.ZombieZone.View.MenuView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public class MenuController {
        private MenuView menuView;
        private Screen screen;
        private Menu menu;
        public MenuController(Menu menu, MenuView menuView, Screen screen) {
            this.menuView = menuView;
            this.screen = screen;
            this.menu = menu;
        }

        public void run() {
            try{
                while (true) {
                    draw();
                    handleInput();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void draw() throws IOException {
            screen.clear();
            menuView.render(screen.newTextGraphics());
            screen.refresh();
        }

        private void handleInput() throws IOException {
            KeyStroke key = screen.readInput();

            if(key.getKeyType()== KeyType.ArrowUp){
                menu.moveUp();
            } else if (key.getKeyType()==KeyType.ArrowDown) {
                menu.moveDown();

            } else if (key.getKeyType()==KeyType.Enter) {
                if (menu.isSelectedExit()) {
                    screen.close();
                    System.exit(0);
                    return;
                }
                if (menu.isSelectedStart()){
                    Game game = new Game();
                    game.run();
                }
            }
    }
}
