package com.aor.ZombieZone.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LeadBoard {
    private List<Integer> ListOfScore = new ArrayList<Integer>();
    public LeadBoard(){}

    public void addToListOfScore(Integer value) {
        if (ListOfScore.size() >= 5) {
            List<Integer> copiaDolead = new ArrayList<>(ListOfScore);
            copiaDolead.sort(Comparator.reverseOrder());
            for (int i = 0; i < ListOfScore.size(); i++) {
                ListOfScore.set(i, copiaDolead.get(i));
            }
        }
        else{
            ListOfScore.add(value);
        }
    }
    public List<Integer> getListOfScore() {
        return ListOfScore;
    }
}
