package recommendation_system.edu.duke;

import java.util.ArrayList;
import java.util.List;

public class RangeResource {
    private int myStart;
    private int myEnd;
    private int myIncrement;
    private List<Integer> myValues;

    public RangeResource(int end) {
        this(0, end, 1);
    }

    public RangeResource(int start, int end) {
        this(start, end, 1);
    }

    public RangeResource(int start, int end, int increment) {
        if (increment == 0) {
            throw new ResourceException("RangeResource: invalid increment, cannot be 0");
        }
        if (end < start && increment > 0) {
            throw new ResourceException("RangeResource: invalid increment, cannot be positive when end (" + end + ") is less than start (" + start + ")");
        }
        if (end > start && increment < 0) {
            throw new ResourceException("RangeResource: invalid increment, cannot be negative when end (" + end + ") is greater than start (" + start + ")");
        }
        this.myStart = start;
        this.myEnd = end;
        this.myIncrement = increment;
        this.myValues = this.makeValues(start, end, increment);
    }

    public RangeResource(RangeResource other) {
        this(other.myStart, other.myEnd, other.myIncrement);
    }

    public String toString() {
        return this.myValues.toString();
    }

    public Iterable<Integer> sequence() {
        return this.myValues;
    }

    private List<Integer> makeValues(int start, int end, int increment) {
        ArrayList<Integer> result = new ArrayList<>();
        while (!(increment > 0 && start >= end || increment < 0 && start <= end)) {
            result.add(start);
            start += increment;
        }
        return result;
    }
}

