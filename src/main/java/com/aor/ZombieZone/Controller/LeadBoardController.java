package com.aor.ZombieZone.Controller;
import com.aor.ZombieZone.Menu;
import com.aor.ZombieZone.Model.Game;
import com.aor.ZombieZone.Model.Position;
import com.aor.ZombieZone.Model.Projectile;
import com.aor.ZombieZone.View.GameView;
import com.aor.ZombieZone.View.LeadBoardView;
import com.aor.ZombieZone.View.MenuView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.*;

public class LeadBoardController {
    private static LeadBoardView leadBoardView;
    private static Screen screen;
    private static Map<Integer, String> mapDoLead = new TreeMap<>();
    private static boolean continuaLead = true;
    public LeadBoardController(LeadBoardView leadBoardView, Screen screen) {
        this.leadBoardView = leadBoardView;
        this.screen = screen;
    }

    public static void addToMapDoLead(Integer value, String key) {
        mapDoLead.put(value, key);
    }
    public static Map<Integer, String> getMapDoLead() {
        return mapDoLead;
    }

    public static void setTruetoLead(){
        continuaLead = true;
    }
    public static void setFalsetoLead(){
        continuaLead = false;
    }

    public static void runLeadBoard() {
        try{
            mapDoLead.put(10,"Divaldo");
            mapDoLead.put(11,"Divaldo");
            mapDoLead.put(54,"Arthur");
            mapDoLead.put(41,"Pedro");
            mapDoLead.put(102,"Gokturk Empire");
            mapDoLead.put(402,"Roman Empire");
            mapDoLead.put(82,"São Tomé e Príncipe");
            mapDoLead.put(200, "Brazil");
            while(continuaLead){
                LeadBoardController.draw();
                handleInput();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    private static void draw() throws IOException {
        screen.clear();
        leadBoardView.render(screen.newTextGraphics());
        screen.refresh();
    }
    private static void handleInput() throws IOException {
        KeyStroke key = screen.readInput();
        if (key.getKeyType() == KeyType.ArrowUp) {
            leadBoardView.scrollUp();
        } else if (key.getKeyType() == KeyType.ArrowDown) {
            leadBoardView.scrollDown();
        } else if (key.getKeyType() == KeyType.Character) {
            if (key.getCharacter() == 'q') {
                continuaLead = false;
            }
        }
}

}
