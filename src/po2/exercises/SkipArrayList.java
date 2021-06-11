package po2.exercises;

import java.util.ArrayList;
import java.util.Iterator;

public class SkipArrayList<T> extends ArrayList<T> {

    private int step;

    public SkipArrayList(int step) {
        this.step = step;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>(){
            private int index = step > 0? 0 : SkipArrayList.this.size() - 1;
            @Override
            public boolean hasNext() {
                return index >= 0 && index <= SkipArrayList.this.size() - 1;
            }

            @Override
            public T next() {
                T elem = SkipArrayList.this.get(index);
                index += step;
                return elem;
            }
        };
    }
}