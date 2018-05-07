package tests;

import org.junit.Assert;
import org.junit.Test;
import v1.KwicV1;
import v2.model.IndexProductionSystem;
import v2.model.KwicV2;
import v2.model.KwicV3;

import static tests.TestHelperMethods.displayVersionTest;
import static tests.TestHelperMethods.getTimeToComplete;


public class TimingTests {
    private final long TARGET_TIME_TO_COMPLETE = 300; //milliseconds

    @Test
    public void testVersion1Timing() {
        displayVersionTest(1);
        long timeAtStart = System.nanoTime();

        KwicV1 kwikV1 = new KwicV1();
        kwikV1.circularShift(kwikV1.allLines);
        kwikV1.alphabetize(kwikV1.allLines);
        kwikV1.writeLines(kwikV1.allLines, TestHelperMethods.VER1_OUTPUT_FILE);

        long timeAtEnd = System.nanoTime();
        long timeToComplete = getTimeToComplete(timeAtStart, timeAtEnd);
        System.out.println("Test Finished in " + timeToComplete + " milliseconds");

        Assert.assertTrue("Time needs to be less than " + TARGET_TIME_TO_COMPLETE,
                timeToComplete <= TARGET_TIME_TO_COMPLETE);
    }

    @Test
    public void testVersion2Timing() {
        displayVersionTest(2);

        long timeAtStart = System.nanoTime();

        IndexProductionSystem kwik = new KwicV2(TestHelperMethods.INPUT_FILE, TestHelperMethods.VER2_OUTPUT_FILE);
        kwik.circularShift();
        kwik.writeLines();

        long timeAtEnd = System.nanoTime();
        long timeToComplete = getTimeToComplete(timeAtStart, timeAtEnd);
        System.out.println("Test Finished in " + timeToComplete + " milliseconds");

        Assert.assertTrue("Time needs to be less than " + TARGET_TIME_TO_COMPLETE,
                timeToComplete <= TARGET_TIME_TO_COMPLETE);
    }

    @Test
    public void testVersion3Timing() {
        displayVersionTest(3);

        long timeAtStart = System.nanoTime();

        IndexProductionSystem kwik = new KwicV3(TestHelperMethods.INPUT_FILE, TestHelperMethods.VER3_OUTPUT_FILE);
        kwik.circularShift();
        kwik.writeLines();

        long timeAtEnd = System.nanoTime();
        long timeToComplete = getTimeToComplete(timeAtStart, timeAtEnd);
        System.out.println("Test Finished in " + timeToComplete + " milliseconds");

        Assert.assertTrue("Time needs to be less than " + TARGET_TIME_TO_COMPLETE,
                timeToComplete <= TARGET_TIME_TO_COMPLETE);
    }
}
