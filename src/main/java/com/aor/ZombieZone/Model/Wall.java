package com.aor.ZombieZone.Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
public class Wall extends Element {
    public Wall(int x, int y) {
        super(x, y);
    }
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#4B2E1F"));
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "█");
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
        {return true;}
        if (o == null || getClass() != o.getClass())
        {return false;}
        Wall wall = (Wall) o;
        return getPosition().x == wall.getPosition().x && getPosition().y == wall.getPosition().y;
    }
}