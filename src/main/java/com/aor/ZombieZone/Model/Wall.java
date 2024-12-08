package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element {
    public Wall(int x, int y) {
        super(x, y);
    } // construtor da Função Wall

    @Override  //draw da Função Wall
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#38291A"));
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "█");
    }
    @Override
    public boolean equals(Object o) { // Override do Equals(função padrão usada pelo constains)
        if (this == o)
        {return true;}
        if (o == null || getClass() != o.getClass())
        {return false;}
        Wall wall = (Wall) o; // conversão do Objeto  para wall, não muito usado porque
        return getPosition().x == wall.getPosition().x && getPosition().y == wall.getPosition().y;
    }
}