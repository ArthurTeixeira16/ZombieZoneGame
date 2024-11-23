import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Soldier extends Element{//sem mistério, mesma coisa do hero por enquanto, o dificil vai ser implementar movimento com 3x3
//talvez tenhamos que mudar o 3x3 pra 1x1 e é isso

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