package com.ZombieZoneTest.Model;

import com.aor.ZombieZone.Model.Game;
import com.aor.ZombieZone.Model.LeadBoard;
import com.aor.ZombieZone.Model.Score;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LeadBoardTester {
    private LeadBoard leadBoard;
    private List<Integer> listOfScore;
    private final String FILE_NAME = "LeaderBoardTest.txt";
    private Field FILENAMETESTER;
    private File file = new File(FILE_NAME);
    @BeforeEach
    public void setUp() throws IllegalAccessException, NoSuchFieldException, IOException {
        leadBoard = Mockito.spy(new LeadBoard());
        listOfScore = new ArrayList<>(List.of(75,
                60,
                56,
                23,
                20));
        leadBoard.setListOfScore(listOfScore);

        FILENAMETESTER = LeadBoard.class.getDeclaredField("FILE_NAME");
        FILENAMETESTER.setAccessible(true);
        FILENAMETESTER.set(leadBoard, FILE_NAME);
        file.createNewFile();

    }
    @Test
    public void setListOfScoreTest() {
        assertEquals(listOfScore, leadBoard.getListOfScore());
    }
    @Test
    public void addToListOfScoreTest_WithoutSort() {
        doNothing().when(leadBoard).sortListOfScore();
        leadBoard.addToListOfScore(100);
        leadBoard.addToListOfScore(100);
        leadBoard.addToListOfScore(100);
        leadBoard.addToListOfScore(100);
        assertEquals(List.of(75,60,56,23,20,100,100,100,100), leadBoard.getListOfScore());
        leadBoard.addToListOfScore(20);
        assertEquals(List.of(75,60,56,23,20,100,100,100,100,20), leadBoard.getListOfScore());
    }
    @Test
    public void addListOfScoreTest_WithSort() throws NoSuchFieldException, IllegalAccessException {
        FILENAMETESTER = LeadBoard.class.getDeclaredField("FILE_NAME");
        FILENAMETESTER.setAccessible(true);
        FILENAMETESTER.set(leadBoard, FILE_NAME);

        leadBoard.addToListOfScore(100);
        leadBoard.addToListOfScore(100);
        leadBoard.addToListOfScore(100);
        leadBoard.addToListOfScore(100);
        assertEquals(List.of(100,100,100,100,75), leadBoard.getListOfScore());
        leadBoard.addToListOfScore(20);
        assertEquals(List.of(100,100,100,100,75), leadBoard.getListOfScore());
    }
    @Test
    public void leadScoresTest() throws FileNotFoundException {
        String text = "110\n1996\n45\n104\n3\n1\n23";
        //tem de ser BuferredReader porque
        // usamos no teste do leadboard
        BufferedReader textReader = new BufferedReader(new StringReader(text));
        when(leadBoard.getReader()).thenReturn(textReader);
        leadBoard.setListOfScore(new ArrayList<>());
        leadBoard.loadScores();
        listOfScore = leadBoard.getListOfScore();
        assertEquals(5, listOfScore.size());
        assertEquals( List.of(1996,110,104,45,23) , listOfScore);
    }
    @Test
    public void leadScoresTest_WithErrors() throws FileNotFoundException {
        String text = "110\n1996\n45\nTEXTO\n3\n34x1\n23";
        BufferedReader textReader = new BufferedReader(new StringReader(text));
        when(leadBoard.getReader()).thenReturn(textReader);
        leadBoard.setListOfScore(new ArrayList<>());
        leadBoard.loadScores();
        listOfScore = leadBoard.getListOfScore();
        assertEquals(5, listOfScore.size());
        assertEquals( List.of(1996,110,45,23,3) , listOfScore);
    }
    @Test
    public void saveScoresTest() throws IOException, IllegalAccessException, NoSuchFieldException {
        leadBoard.setListOfScore(new ArrayList<>(List.of(500,4,350,200,100,200,300)));
        leadBoard.sortListOfScore();
        leadBoard.saveScores();
        assertTrue(file.exists());

        List<String> lines = readLinesFromFile();

        assertEquals(5, lines.size());
        assertEquals("500", lines.get(0));
        assertEquals("350", lines.get(1));
        assertEquals("300", lines.get(2));
    }
    private List<String> readLinesFromFile() throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println( e.getMessage());
            throw e;
        }
        return lines;
    }
}



