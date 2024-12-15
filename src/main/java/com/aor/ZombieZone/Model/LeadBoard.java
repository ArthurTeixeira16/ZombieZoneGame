package com.aor.ZombieZone.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LeadBoard implements ScoreObserver {
    private List<Integer> listOfScore;
    public LeadBoard() {
        listOfScore = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listOfScore.add(0);
        }
    }
    public void addToListOfScore(Integer value) {
        listOfScore.add(value);
        listOfScore.sort(Comparator.reverseOrder());
        if (listOfScore.size() > 5) {
            listOfScore = listOfScore.subList(0, 5);
        }
    }
    public List<Integer> getListOfScore() {
        return new ArrayList<>(listOfScore);
    }
    @Override
    public void onGameEnd(int score) {
        this.addToListOfScore(score);
    }
}