package v2.model;

import v2.lineManager.StringLineManagerV3;
import v2.lineIO.LineIO;

public class KwicV3 extends IndexProductionSystem {

    public KwicV3(String inputFilePath, String outputFilePath) {
        super(inputFilePath, outputFilePath);
        setLineManager(new StringLineManagerV3());
        setLineIO(new LineIO(this.getLineManager(), inputFilePath, outputFilePath));
        readLines();
    }
}

