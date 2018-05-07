package tests;

import org.junit.Assert;
import org.junit.Test;
import v1.KwikV1;
import v2.model.IndexProductionSystem;
import v2.model.KwikV2;
import v2.model.KwikV3;

import static tests.TestHelperMethods.displayVersionTest;
import static tests.TestHelperMethods.getTimeToComplete;


public class TimingTests {
    private final long TARGET_TIME_TO_COMPLETE = 300; //milliseconds

    @Test
    public void testVersion1Timing() {
        displayVersionTest(1);
        long timeAtStart = System.nanoTime();

        KwikV1 kwikV1 = new KwikV1(TestHelperMethods.INPUT_FILE, TestHelperMethods.VER1_OUTPUT_FILE);
        kwikV1.circularShift(kwikV1.allLines);
        kwikV1.alphabetize(kwikV1.allLines);
        kwikV1.writeLines(kwikV1.allLines);

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

        IndexProductionSystem kwik = new KwikV2(TestHelperMethods.INPUT_FILE, TestHelperMethods.VER2_OUTPUT_FILE);
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

        IndexProductionSystem kwik = new KwikV3(TestHelperMethods.INPUT_FILE, TestHelperMethods.VER3_OUTPUT_FILE);
        kwik.circularShift();
        kwik.writeLines();

        long timeAtEnd = System.nanoTime();
        long timeToComplete = getTimeToComplete(timeAtStart, timeAtEnd);
        System.out.println("Test Finished in " + timeToComplete + " milliseconds");

        Assert.assertTrue("Time needs to be less than " + TARGET_TIME_TO_COMPLETE,
                timeToComplete <= TARGET_TIME_TO_COMPLETE);
    }
}
