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
        loadScores();
    }

    public void addToListOfScore(Integer value) {
        listOfScore.add(value);
        listOfScore.sort(Comparator.reverseOrder());
        if (listOfScore.size() > 5) {
            listOfScore = listOfScore.subList(0, 5);
        }
        saveScores();
    }

    public List<Integer> getListOfScore() {
        return new ArrayList<>(listOfScore);
    }

    @Override
    public void onGameEnd(int score) {
        this.addToListOfScore(score);
    }

    private void loadScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    listOfScore.add(Integer.parseInt(line));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid score in file: " + line);
                }
            }
            listOfScore.sort(Comparator.reverseOrder());
            if (listOfScore.size() > 5) {
                listOfScore = listOfScore.subList(0, 5);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int score : listOfScore) {
                writer.write(String.valueOf(score));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}