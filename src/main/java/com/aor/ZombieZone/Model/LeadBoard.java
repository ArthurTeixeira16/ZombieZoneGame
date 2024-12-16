package com.aor.ZombieZone.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.io.*;
import java.util.*;

public class LeadBoard implements ScoreObserver {
    private static final String FILE_NAME = "leaderboard.txt";
    private List<Integer> listOfScore;

    public LeadBoard() {
        listOfScore = new ArrayList<>();
        try {
            loadScores();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addToListOfScore(Integer value) {
        listOfScore.add(value);
        listOfScore.sort(Comparator.reverseOrder());
        if (listOfScore.size() > 5) {
            listOfScore = listOfScore.subList(0, 5);
        }
        try {
            saveScores();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> getListOfScore() {
        return new ArrayList<>(listOfScore);
    }

    @Override
    public void onGameEnd(int score) {
        this.addToListOfScore(score);
    }

    private void loadScores() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        while ((line = reader.readLine()) != null) listOfScore.add(Integer.parseInt(line));
        listOfScore.sort(Comparator.reverseOrder());
        if (listOfScore.size() > 5) {
            listOfScore = listOfScore.subList(0, 5);
        }
    }

    private void saveScores() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (int score : listOfScore) {
            writer.write(String.valueOf(score));
            writer.newLine();
        }
    }
}