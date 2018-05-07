package v2.model;

import v2.LineManager.StringLineManagerV3;
import v2.lineIO.LineIO;

public class KwikV3 extends IndexProductionSystem {

    public KwikV3(String inputFilePath, String outputFilePath) {
        super(inputFilePath, outputFilePath);
        setLineManager(new StringLineManagerV3());
        setLineIO(new LineIO(this.getLineManager(), inputFilePath, outputFilePath));
        readLines();
    }
}

