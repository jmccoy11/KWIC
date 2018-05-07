package v2;

import v1.KwicV1;
import v2.model.IndexProductionSystem;
import v2.model.KwicV2;
import v2.model.KwicV3;

public class Launcher {
    private static IndexProductionSystem kwic;
    private static final String INPUT_FILE_PATH = "warAndPeace_modified.txt";
    private static final String OUTPUT_FILE_PATH = "KWIK_Output.txt";

    public static void main(String[] args) {
        System.out.println("KWIK Index Production System - Version 2");
        kwic = new KwicV2(INPUT_FILE_PATH, OUTPUT_FILE_PATH);
        kwic.circularShift();
        kwic.circularShift();
        kwic.writeLines();

        System.out.println();
        System.out.println("----------------------");
        System.out.println();

        System.out.println("KWIK Index Production System - Version 3");
        kwic = new KwicV3(INPUT_FILE_PATH, OUTPUT_FILE_PATH);
        kwic.circularShift();
        kwic.writeLines();
    }
}
