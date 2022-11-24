package recommendation_system.edu.duke;

import java.util.Iterator;

class TextIterable
implements Iterable<String> {
    private String[] myStrings;

    public TextIterable(String source, String regexp) {
        this.myStrings = source.split(regexp);
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>(){
            private int myCount = 0;

            @Override
            public boolean hasNext() {
                return this.myCount < TextIterable.this.myStrings.length;
            }

            @Override
            public String next() {
                String s = TextIterable.this.myStrings[this.myCount];
                ++this.myCount;
                return s;
            }
        };
    }
}

