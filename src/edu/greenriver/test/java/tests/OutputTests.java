package tests;

import org.junit.Assert;
import org.junit.Test;
import v1.KwikV1;
import v2.model.IndexProductionSystem;
import v2.model.KwikV2;
import v2.model.KwikV3;

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

        KwikV1 kwikV1 = new KwikV1(TestHelperMethods.INPUT_FILE, TestHelperMethods.VER1_OUTPUT_FILE);

        System.out.println();
        System.out.println("Testing against Alphabetize");
        kwikV1.alphabetize(kwikV1.allLines);
        kwikV1.writeLines(kwikV1.allLines);
        testAgainstControl("alphabetize", loadFileToList(TestHelperMethods.VER1_OUTPUT_FILE));

        System.out.println();
        System.out.println("Testing against First Shift");
        kwikV1.circularShift(kwikV1.allLines);
        kwikV1.alphabetize(kwikV1.allLines);
        kwikV1.writeLines(kwikV1.allLines);
        testAgainstControl("firstShift", loadFileToList(TestHelperMethods.VER1_OUTPUT_FILE));

        System.out.println();
        System.out.println("Testing against Second Shift");
        kwikV1.circularShift(kwikV1.allLines);
        kwikV1.alphabetize(kwikV1.allLines);
        kwikV1.writeLines(kwikV1.allLines);
        testAgainstControl("secondShift", loadFileToList(TestHelperMethods.VER1_OUTPUT_FILE));
    }

    @Test
    public void testVersion2Output() {
        displayVersionTest(2);

        IndexProductionSystem kwikV2 = new KwikV2(TestHelperMethods.INPUT_FILE, TestHelperMethods.VER2_OUTPUT_FILE);

        runOutputTest(kwikV2, TestHelperMethods.VER2_OUTPUT_FILE);
    }

    @Test
    public void testVersion3Output() {
        displayVersionTest(3);

        IndexProductionSystem kwikV3 = new KwikV3(TestHelperMethods.INPUT_FILE, TestHelperMethods.VER3_OUTPUT_FILE);

        runOutputTest(kwikV3, TestHelperMethods.VER3_OUTPUT_FILE);
    }

    private void runOutputTest(IndexProductionSystem kwik, String outputFile) {

        System.out.println();
        System.out.println("Testing against Alphabetize");
        kwik.writeLines();
        testAgainstControl("alphabetized", loadFileToList(outputFile));


        System.out.println();
        System.out.println("Testing against First Shift");
        kwik.circularShift();
        kwik.writeLines();

        testAgainstControl("firstShift", loadFileToList(outputFile));


        System.out.println();
        System.out.println("Testing against Second Shift");
        kwik.circularShift();
        kwik.writeLines();
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
