package v1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class KwicV1 {
    public ArrayList<String> allLines;
    private static final String INPUT_FILE_PATH = "warAndPeace_modified.txt";
    private static final String OUTPUT_FILE_PATH = "KWIC_Output.txt";

    public KwicV1() {
        this.allLines = readLines();
    }

    public static void main(String[] args) {
        KwicV1 kwicV1 = new KwicV1();
        kwicV1.circularShift(kwicV1.allLines);
        kwicV1.alphabetize(kwicV1.allLines);
        kwicV1.writeLines(kwicV1.allLines, OUTPUT_FILE_PATH);

        System.out.println();
        System.out.println("----------------------");
        System.out.println();
    }

    private ArrayList<String> readLines() {
        ArrayList<String> result = new ArrayList<>();
        long timeAtStart = 0;

        try (Scanner reader = new Scanner(new File(INPUT_FILE_PATH))) {
            timeAtStart = System.nanoTime();

            while (reader.hasNextLine()) {
                result.add(reader.nextLine());
            }
        } catch (FileNotFoundException exc) {
            System.out.println("File not found: " + exc.getLocalizedMessage());
        }

        long timeAtEnd = System.nanoTime();
        System.out.println("Building the initial data structure took: " +
                (timeAtEnd - timeAtStart) / 1000000 + " milliseconds.");
        return result;
    }

    public void circularShift(ArrayList<String> lines) {
        long timeAtStart = System.nanoTime();

        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, circularShiftLine(lines.get(i)));
        }

        long timeAtEnd = System.nanoTime();
        System.out.println("Time to perform a circular shift took: " +
                (timeAtEnd - timeAtStart) / 1000000 + " milliseconds.");
    }

    private String circularShiftLine(String line) {
        String[] words = line.split(" ");
        String firstWord = words[0];

        StringJoiner sj = new StringJoiner(" ");

        for (int i = 1; i < words.length; i++) {
            if(words[i].length() > 0) {
                sj.add(words[i]);
            }
        }

        sj.add(firstWord);
        return sj.toString();
    }

    public void alphabetize(ArrayList<String> lines) {
        long timeAtStart = System.nanoTime();

        Collections.sort(lines);

        long timeAtEnd = System.nanoTime();
        System.out.println("Time to alphabetize took: " +
                (timeAtEnd - timeAtStart) / 1000000 + " milliseconds.");
    }

    public void writeLines(ArrayList<String> lines, String outputFilePath) {
        long timeAtStart = System.nanoTime();

        try (PrintWriter writer = new PrintWriter(new File(outputFilePath))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (FileNotFoundException exc) {
            System.out.println("File not found: " + exc.getLocalizedMessage());
        }

        long timeAtEnd = System.nanoTime();
        System.out.println("Writing to output file took: " +
                (timeAtEnd - timeAtStart) / 1000000 + " milliseconds.");
    }
}
