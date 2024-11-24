package com.aor.hero.element;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element{
    public Wall(int x,int y){
        super(x,y);
    }

    @Override
    public void draw(TextGraphics graphics){
        /* Criação de um Bloco morrom como o Wall */
        graphics.setForegroundColor(TextColor.Factory.fromString("#38291A")); // Cor Marrom da Wall
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "█");
    }
}