package v2.interfaces;

import java.util.Collection;

public interface IAlphabetizableCollection {

    void add(String newLine);

    void setCollection(Collection<String> collection);

    Collection<String> getCollection();

    Collection<String> getAlphabetizedCollection();
}
