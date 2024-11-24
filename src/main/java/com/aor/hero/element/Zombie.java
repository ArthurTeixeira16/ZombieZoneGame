package com.aor.hero.element;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Zombie extends Element{

    public Zombie(int x,int y){
        super(x,y);
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }

    public void draw(TextGraphics screen) {
        int baseX = getPosition().getX();
        int baseY = getPosition().getY();
        screen.putString(baseX, baseY, "  Z");
        screen.putString(baseX, baseY+1, " /|\\");
        screen.putString(baseX, baseY+2, " / \\");
        // Desenhando o "zumbi" usando um único caractere
        screen.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
    }
}
