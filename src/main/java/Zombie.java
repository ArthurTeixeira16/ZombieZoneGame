import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Zombie extends Element{
//mesmo b.o que eu escrevi em soldier se aplica a esta coisinha
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
        screen.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
    }
}
