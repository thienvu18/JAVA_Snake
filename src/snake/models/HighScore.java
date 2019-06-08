package snake.models;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static snake.utils.constraints.Constrains.HIGH_SCORE_PATH;

public class HighScore {
    private final int MAXELEMENT = 3;

    private static HighScore instance = null;

    private ArrayList<String> highScore = new ArrayList<>();
    private File file;

    private HighScore() {
        readFile();
    }

    public static HighScore getInstance() {
        if (instance == null) {
            instance = new HighScore();
        }

        return instance;
    }

    private void checkFile() {

        String link = HIGH_SCORE_PATH;

        Path path = Paths.get(link);

        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);

            file = new File(link);

            try {
                FileWriter fileWriter = new FileWriter(file);
                for (int i = 0; i < MAXELEMENT; i++) {
                    fileWriter.write("0" + "\n");
                }
                fileWriter.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        } catch (IOException e) {
            file = new File(link);
        }

    }


    private void readFile() {
        checkFile();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bfReader = new BufferedReader(reader);
            for (int i = 0; i < MAXELEMENT; i++) {
                highScore.add(i, bfReader.readLine());
                System.out.println(highScore.get(i));
            }

            bfReader.close();
            reader.close();
        } catch (IOException ignored) {

        }
    }

    private void writeFile() {
        checkFile();
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < MAXELEMENT; i++) {
                fileWriter.write(highScore.get(i) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public ArrayList<String> get() {
        ArrayList<String> res = new ArrayList<>(MAXELEMENT);
        for (int i = 0; i < MAXELEMENT; i++) {
            res.add(i, highScore.get(i));
        }

        return res;
    }

    public void save(int score) {
        for (int i = 0; i < MAXELEMENT; i++) {
            if (score > (highScore.get(i).equalsIgnoreCase("null") ? 0 : Integer.parseInt(highScore.get(i)))) {
                highScore.add(i, score + "");
                break;
            }
        }
            writeFile();
    }
}