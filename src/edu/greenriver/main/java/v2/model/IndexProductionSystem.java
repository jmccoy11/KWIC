package v2.model;

import v2.interfaces.IAlphabetizableCollection;
import v2.interfaces.ICircularShift;
import v2.interfaces.ILineIO;
import v2.lineIO.LineIO;
import v2.lineManager.StringLineManagerV2;

import java.util.ArrayList;
import java.util.StringJoiner;

public abstract class IndexProductionSystem implements ICircularShift {

    private IAlphabetizableCollection lineManager;
    private ILineIO lineIO;

    public IndexProductionSystem(String inputFilePath, String outputFilePath) {
        this.lineManager = new StringLineManagerV2();
        this.lineIO = new LineIO(this.getLineManager(), inputFilePath, outputFilePath);
    }

    public IAlphabetizableCollection getLineManager() {
        return lineManager;
    }

    public void setLineManager(IAlphabetizableCollection lineManager) {
        if(lineManager != null) {
            this.lineManager = lineManager;
        } else {
            throw new NullPointerException("Alphabetizable collection cannot be null!");
        }
    }

    public ILineIO getLineIO() {
        return lineIO;
    }

    public void setLineIO(ILineIO lineIO) {
        if (lineIO != null) {
            this.lineIO = lineIO;
        } else {
            throw new NullPointerException("Line Input/Output cannot be null!");
        }
    }

    public void readLines(){
        this.lineIO.readLines();
    }

    public void writeLines(){
        this.lineIO.writeLines();
    }


    @Override
    public void circularShift() {
        circularShiftCollection();
    }

    private void circularShiftCollection() {
        long timeAtStart = System.nanoTime();

        ArrayList<String> collection = new ArrayList<>(getLineManager().getCollection());
        for (int i = 0; i < collection.size(); i++) {
            collection.set(i, circularShiftLine(collection.get(i)));
        }

        getLineManager().setCollection(collection);

        long timeAtEnd = System.nanoTime();
        System.out.println("Time to perform a circular shift took: " +
                (timeAtEnd - timeAtStart) / 1000000 + " milliseconds.");
    }

    private String circularShiftLine(String line) {
        String[] words = line.split(" ");
        String firstWord = words[0];

        StringJoiner sj = new StringJoiner(" ");

        for (int i = 1; i < words.length; i++) {
            if(words[i].length() > 0) {
                sj.add(words[i]);
            }
        }

        sj.add(firstWord);
        return sj.toString();
    }
}
