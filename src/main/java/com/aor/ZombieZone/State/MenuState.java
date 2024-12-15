package com.aor.ZombieZone.State;

import com.aor.ZombieZone.Controller.MenuController;
import com.aor.ZombieZone.Model.Menu;
import com.aor.ZombieZone.View.MenuView;
import com.googlecode.lanterna.screen.Screen;

import javax.swing.*;
import java.io.IOException;

public class MenuState implements State{
    private MenuController menuController;
    private Screen screen;
    private Menu menu;
    private MenuView menuView;

    public MenuState(Screen screen){
        this.menu = new Menu();
        this.menuView = new MenuView(menu);

        menuController = new MenuController(menu,menuView,screen);
    }

    public MenuController getController() {
        return menuController;
    }

    @Override
    public void handleInput(KeyStroke key) throws IOException {
        getController().handleInput();
    }

    @Override
    public void run() throws IOException {
        getController().setRunningTrue();
        getController().run();

    }
}
