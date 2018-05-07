package v2.model;

import v2.lineIO.LineIO;
import v2.lineManager.StringLineManagerV2;

public class KwicV2 extends IndexProductionSystem {

    public KwicV2(String inputFilePath, String outputFilePath) {
        super(inputFilePath, outputFilePath);
        setLineManager(new StringLineManagerV2());
        setLineIO(new LineIO(this.getLineManager(), inputFilePath, outputFilePath));
        readLines();
    }
}
