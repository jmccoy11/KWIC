package tests;

import org.junit.Assert;
import org.junit.Test;
import v1.KwicV1;
import v2.model.IndexProductionSystem;
import v2.model.KwicV2;
import v2.model.KwicV3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static tests.TestHelperMethods.displayVersionTest;

public class OutputTests {
    private final ArrayList<String> ALPHABETIZE_CONTROL = loadFileToList("testOutput/testControlAlphabetize.txt");
    private final ArrayList<String> FIRST_SHIFT_CONTROL = loadFileToList("testOutput/testControlFirstShift.txt");
    private final ArrayList<String> SECOND_SHIFT_CONTROL = loadFileToList("testOutput/testControlSecondShift.txt");
    private final String MISMATCH_DATA = "Data does not match";

    private ArrayList<String> loadFileToList(String filePath) {
        ArrayList<String> loadedList = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(filePath))) {
            while (reader.hasNextLine()) {
                loadedList.add(reader.nextLine());
            }
        } catch (FileNotFoundException exc) {
            System.out.println("File Not Found: " + exc.getLocalizedMessage());;
        }

        return loadedList;
    }

    @Test
    public void testVersion1Output() {
        displayVersionTest(1);

        KwicV1 kwicV1 = new KwicV1();

        System.out.println();
        System.out.println("Testing against Alphabetize");
        kwicV1.alphabetize(kwicV1.allLines);
        kwicV1.writeLines(kwicV1.allLines, TestHelperMethods.VER1_OUTPUT_FILE);
        testAgainstControl("alphabetize", loadFileToList(tests.TestHelperMethods.VER1_OUTPUT_FILE));

        System.out.println();
        System.out.println("Testing against First Shift");
        kwicV1.circularShift(kwicV1.allLines);
        kwicV1.alphabetize(kwicV1.allLines);
        kwicV1.writeLines(kwicV1.allLines, TestHelperMethods.VER1_OUTPUT_FILE);
        testAgainstControl("firstShift", loadFileToList(TestHelperMethods.VER1_OUTPUT_FILE));

        System.out.println();
        System.out.println("Testing against Second Shift");
        kwicV1.circularShift(kwicV1.allLines);
        kwicV1.alphabetize(kwicV1.allLines);
        kwicV1.writeLines(kwicV1.allLines, TestHelperMethods.VER1_OUTPUT_FILE);
        testAgainstControl("secondShift", loadFileToList(TestHelperMethods.VER1_OUTPUT_FILE));
    }

    @Test
    public void testVersion2Output() {
        displayVersionTest(2);

        IndexProductionSystem kwicV2 = new KwicV2(TestHelperMethods.INPUT_FILE, TestHelperMethods.VER2_OUTPUT_FILE);

        runOutputTest(kwicV2, TestHelperMethods.VER2_OUTPUT_FILE);
    }

    @Test
    public void testVersion3Output() {
        displayVersionTest(3);

        IndexProductionSystem kwicV3 = new KwicV3(TestHelperMethods.INPUT_FILE, TestHelperMethods.VER3_OUTPUT_FILE);

        runOutputTest(kwicV3, TestHelperMethods.VER3_OUTPUT_FILE);
    }

    private void runOutputTest(IndexProductionSystem kwic, String outputFile) {

        System.out.println();
        System.out.println("Testing against Alphabetize");
        kwic.writeLines();
        testAgainstControl("alphabetized", loadFileToList(outputFile));


        System.out.println();
        System.out.println("Testing against First Shift");
        kwic.circularShift();
        kwic.writeLines();

        testAgainstControl("firstShift", loadFileToList(outputFile));


        System.out.println();
        System.out.println("Testing against Second Shift");
        kwic.circularShift();
        kwic.writeLines();
        testAgainstControl("secondShift", loadFileToList(outputFile));
    }

    private void testAgainstControl(String control, List<String> outputToTest) {
        switch(control){
            case "alphabetized":
                testAgainstControl(ALPHABETIZE_CONTROL, outputToTest);
                break;
            case "firstShift":
                testAgainstControl(FIRST_SHIFT_CONTROL, outputToTest);
                break;
            case "secondShift":
                testAgainstControl(SECOND_SHIFT_CONTROL, outputToTest);
                break;
        }
    }

    private void testAgainstControl(ArrayList<String> controlList, List<String> toTest) {
        for(int i = 0; i < controlList.size(); i++) {
            String expected = controlList.get(i);
            String actual = toTest.get(i);
            Assert.assertEquals(MISMATCH_DATA, actual, expected);
        }
    }
}
