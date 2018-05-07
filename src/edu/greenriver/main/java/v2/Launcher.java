package v2;

import v1.KwikV1;
import v2.model.IndexProductionSystem;
import v2.model.KwikV2;
import v2.model.KwikV3;

public class Launcher {
    private static IndexProductionSystem kwik;
    private static final String INPUT_FILE_PATH = "warAndPeace_modified.txt";
    private static final String OUTPUT_FILE_PATH = "KWIK_Output.txt";

    public static void main(String[] args) {
//        KwikV1 kwikV1 = new KwikV1(INPUT_FILE_PATH, OUTPUT_FILE_PATH);
//        kwikV1.circularShift(kwikV1.allLines);
//        kwikV1.alphabetize(kwikV1.allLines);
//        kwikV1.writeLines(kwikV1.allLines);

//        System.out.println();
//        System.out.println("----------------------");
//        System.out.println();
//
        System.out.println("KWIK Index Production System - Version 2");
        kwik = new KwikV2(INPUT_FILE_PATH, OUTPUT_FILE_PATH);
        kwik.circularShift();
        kwik.circularShift();
        kwik.writeLines();
//
//        System.out.println();
//        System.out.println("----------------------");
//        System.out.println();
//
//        System.out.println("KWIK Index Production System - Version 3");
//        kwik = new KwikV3(INPUT_FILE_PATH, OUTPUT_FILE_PATH);
//        kwik.circularShift();
//        kwik.writeLines();
    }
}
