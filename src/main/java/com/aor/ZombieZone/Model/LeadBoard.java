package com.aor.ZombieZone.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.io.*;
import java.util.*;

public class LeadBoard implements ScoreObserver {
    private String FILE_NAME = "leaderboard.txt";
    private List<Integer> listOfScore;

    public LeadBoard() {
        listOfScore = new ArrayList<>();
        loadScores();
    }

    public void addToListOfScore(Integer value) {
        listOfScore.add(value);
        sortListOfScore();
        saveScores();
    }
    public void sortListOfScore() {
        listOfScore.sort(Comparator.reverseOrder());
        if(listOfScore.size() > 5) {
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
    public BufferedReader getReader() throws FileNotFoundException {
        return (new BufferedReader(new FileReader(FILE_NAME)));
    }
    public void loadScores() {
        try (BufferedReader reader = getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    listOfScore.add(Integer.parseInt(line));
                } catch (NumberFormatException e) {
                    System.err.println("Invalid score in file: " + line);
                }
            }
            sortListOfScore();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void saveScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int score : listOfScore) {
                writer.write(String.valueOf(score));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public void setListOfScore(List<Integer> list) {
        this.listOfScore = list;
    }
}