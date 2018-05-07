package v2.model;

import v2.LineManager.StringLineManagerV2;
import v2.lineIO.LineIO;

public class KwikV2 extends IndexProductionSystem {

    public KwikV2(String inputFilePath, String outputFilePath) {
        super(inputFilePath, outputFilePath);
        setLineManager(new StringLineManagerV2());
        setLineIO(new LineIO(this.getLineManager(), inputFilePath, outputFilePath));
        readLines();
    }
}
