package recommendation_system.edu.duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StorageResource {
    private List<String> myStrings;

    public StorageResource() {
        this.myStrings = new ArrayList<String>();
    }

    StorageResource(String ... data) {
        this.myStrings = new ArrayList<String>(Arrays.asList(data));
    }

    public StorageResource(StorageResource other) {
        this.myStrings = new ArrayList<String>(other.myStrings);
    }

    public void clear() {
        this.myStrings.clear();
    }

    public void add(String s) {
        this.myStrings.add(s);
    }

    public int size() {
        return this.myStrings.size();
    }

    public boolean contains(String s) {
        return this.myStrings.contains(s);
    }

    public Iterable<String> data() {
        return this.myStrings;
    }
}

