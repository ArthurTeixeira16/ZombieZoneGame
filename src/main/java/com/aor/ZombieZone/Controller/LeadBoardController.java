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
    private static List<Integer> mapDoLead = new ArrayList<>();
    private static boolean continuaLead = true;
    public LeadBoardController(LeadBoardView leadBoardView, Screen screen) {
        this.leadBoardView = leadBoardView;
        this.screen = screen;
    }

    public void addToMapDoLead(Integer value) {
        List<Integer> copiaDolead = new ArrayList<>(mapDoLead);
        copiaDolead.sort(Comparator.naturalOrder());
        for(int i = 0; i < 5; i++) {
            mapDoLead.set(i, copiaDolead.get(i));
        }
    }
    public static List<Integer> getMapDoLead() {
        organizarMapDoLead();
        return mapDoLead;
    }
    public static void organizarMapDoLead(){
        mapDoLead.sort(Comparator.reverseOrder());

    }

    public static void setTruetoLead(){
        continuaLead = true;
    }
    public static void setFalsetoLead(){
        continuaLead = false;
    }

    public static void runLeadBoard() {
        try{
            mapDoLead.add(10);
            mapDoLead.add(100);
            mapDoLead.add(341);
            mapDoLead.add(43);
            mapDoLead.add(2);
            mapDoLead.add(912);
            mapDoLead.add(10023);
            mapDoLead.add(73);
            mapDoLead.add(77);
            mapDoLead.add(422);
            mapDoLead.add(333);


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
         if (key.getKeyType() == KeyType.Character) {
            if (key.getCharacter() == 'q') {
                continuaLead = false;
            }
        }
}

}
