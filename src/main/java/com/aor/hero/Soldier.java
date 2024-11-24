package com.aor.hero;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Soldier extends Element{


    public Soldier(int x, int y) {
        super(x, y);
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }


    public void draw(TextGraphics screen) {
        int baseX = getPosition().getX();
        int baseY = getPosition().getY();
        screen.putString(baseX, baseY, "  O");
        screen.putString(baseX, baseY + 1, " /|\\");
        screen.putString(baseX, baseY+2, " / \\");
        screen.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
    }
}