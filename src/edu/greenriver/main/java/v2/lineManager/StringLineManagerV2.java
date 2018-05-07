package v2.lineManager;

import v2.interfaces.IAlphabetizableCollection;

import java.lang.reflect.Array;
import java.util.*;

public class StringLineManagerV2 implements IAlphabetizableCollection {
    private Collection<String> allLines;

    public StringLineManagerV2() {
        allLines = new ArrayList<>();
    }

    public StringLineManagerV2(Collection<String> collection){
        setCollection(collection);
    }

    public Collection<String> getAllLines() {
        return allLines;
    }

    public void add(String newLine){
        allLines.add(newLine);
    }

    @Override
    public Collection<String> getCollection() {
        return getAllLines();
    }

    public void setCollection(Collection<String> collection) {
        this.allLines = new ArrayList<>(collection);
    }

    @Override
    public Collection<String> getAlphabetizedCollection() {
        long timeAtStart = System.nanoTime();

        //TreeSet<String> sortedCollection = new TreeSet<>(this.allLines);
        ArrayList<String> sortedCollection = new ArrayList<>(allLines);
        Collections.sort(sortedCollection);

        long timeAtEnd = System.nanoTime();
        System.out.println("Time it took to alphabetize: " +
                (timeAtEnd - timeAtStart) / 1000000 + " milliseconds.");

        return sortedCollection;
    }
}
