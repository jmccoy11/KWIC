package v2.lineIO;

import v2.interfaces.IAlphabetizableCollection;
import v2.interfaces.ILineIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LineIO implements ILineIO {

    private File inputFile;
    private File outputFile;
    private IAlphabetizableCollection lineManager;

    public LineIO(IAlphabetizableCollection lineManager, String inputFilePath, String outputFilePath) {
        inputFile = new File(inputFilePath);
        outputFile = new File(outputFilePath);
        this.lineManager = lineManager;
    }

    @Override
    public void readLines() {
        long timeAtStart = 0;

        try (Scanner reader = new Scanner(inputFile)) {
            timeAtStart = System.nanoTime();

            while (reader.hasNextLine()) {
                lineManager.add(reader.nextLine());
            }
        } catch (FileNotFoundException exc) {
            System.out.println("File not found: " + exc.getLocalizedMessage());
        }

        long timeAtEnd = System.nanoTime();
        System.out.println("Building the initial data structure took: " +
                (timeAtEnd - timeAtStart) / 1000000 + " milliseconds.");
    }

    @Override
    public void writeLines() {
        long timeAtStart = 0;

        try (PrintWriter writer = new PrintWriter(outputFile)) {
            timeAtStart = System.nanoTime();

            for (String line : lineManager.getAlphabetizedCollection()) {
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
