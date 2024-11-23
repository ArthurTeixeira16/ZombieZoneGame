import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int height;
    private int width;
    private Random random;
    private Soldier soldier;
    //private List<Zombie> zombies;
    private Spawn spawn;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.random = new Random();
        this.soldier = new Soldier(width/2,height/2);
        this.spawn = new Spawn(width,height);

    }

    public void draw(TextGraphics screen) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                TextColor backgroundColor = randomGrayShade();
                screen.setBackgroundColor(backgroundColor);
                char tile = (y == 0 || y == height - 1 || x == 0 || x == width - 1) ? '#' : ' ';
                TextColor foregroundColor = tile == '#' ? TextColor.Factory.fromString("#6A4E23") : backgroundColor;
                screen.setForegroundColor(foregroundColor);
                screen.putString(x, y, String.valueOf(tile));
            }
        }
        soldier.draw(screen);
        for(Zombie zombie:spawn.SpawnZombies()){
            zombie.draw(screen);
        }
    }
    private TextColor randomGrayShade() {
        int grayValue = 180 + random.nextInt(50);
        String hexGray = String.format("#%02X%02X%02X", grayValue, grayValue, grayValue);
        return TextColor.Factory.fromString(hexGray);
    }

}