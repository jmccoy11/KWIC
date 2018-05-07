package v2.lineManager;

import v2.interfaces.IAlphabetizableCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringLineManagerV3 implements IAlphabetizableCollection {

    private List<String> allLines;

    public StringLineManagerV3() {
        this.allLines = new ArrayList<>();
    }

    @Override
    public void add(String line) {
        this.allLines.add(line);
    }

    @Override
    public Collection<String> getCollection() {
        return this.allLines;
    }

    @Override
    public void setCollection(Collection<String> collection) {
        this.allLines = new ArrayList<>(collection);
    }

    @Override
    public Collection<String> getAlphabetizedCollection() {
        long timeAtStart = System.nanoTime();

        ArrayList<String> sortedList = new ArrayList<>(quickSort(this.allLines));

        long timeAtEnd = System.nanoTime();
        System.out.println("Time to alphabetize took: " +
                (timeAtEnd - timeAtStart) / 1000000 + " milliseconds.");

        return sortedList;
    }

    /**
     * Credit to: djitz (https://gist.github.com/djitz/2152957)
     *
     * This method sort the input ArrayList using quick sort algorithm.
     * @param input the ArrayList of integers.
     * @return sorted ArrayList of integers.
     */
    private List<String> quickSort(List<String> input){

        if(input.size() <= 1){
            return input;
        }

        int middle = (int) Math.ceil((double)input.size() / 2);
        String pivot = input.get(middle);

        List<String> less = new ArrayList<>();
        List<String> greater = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            if(input.get(i).compareTo(pivot) <= 0){
                if(i == middle){
                    continue;
                }
                less.add(input.get(i));
            }
            else{
                greater.add(input.get(i));
            }
        }

        return concatenate(quickSort(less), pivot, quickSort(greater));
    }

    /**
     * Credit to: djitz (https://gist.github.com/djitz/2152957)
     *
     * Join the less array, pivot integer, and greater array
     * to single array.
     * @param less integer ArrayList with values less than pivot.
     * @param pivot the pivot integer.
     * @param greater integer ArrayList with values greater than pivot.
     * @return the integer ArrayList after join.
     */
    private List<String> concatenate(List<String> less, String pivot, List<String> greater){

        List<String> list = new ArrayList<>();

        for (int i = 0; i < less.size(); i++) {
            list.add(less.get(i));
        }

        list.add(pivot);

        for (int i = 0; i < greater.size(); i++) {
            list.add(greater.get(i));
        }

        return list;
    }
}
