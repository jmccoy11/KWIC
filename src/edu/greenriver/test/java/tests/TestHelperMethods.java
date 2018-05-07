package tests;

public class TestHelperMethods {
    public static final String INPUT_FILE = "warAndPeace_modified.txt";
    public static final String VER1_OUTPUT_FILE = "testOutput/version1_output.txt";
    public static final String VER2_OUTPUT_FILE = "testOutput/version2_output.txt";
    public static final String VER3_OUTPUT_FILE = "testOutput/version3_output.txt";

    static void displayVersionTest(int version) {
        System.out.println();
        System.out.println("----------------------");
        System.out.println();
        System.out.println("KWIC Index Production System - Version " + version);
    }

    static long getTimeToComplete(long start, long end) {
        return (end - start) / 1000000;
    }
}
