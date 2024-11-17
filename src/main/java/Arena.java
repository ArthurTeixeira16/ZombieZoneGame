import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
public class Arena {
    private int height;
    private int width;
    public Arena(int width, int height) {
        this.height = height;
        this.width = width;
    }
    public void draw(TextGraphics screen) {
        screen.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        screen.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width, height), ' ');
    }
}