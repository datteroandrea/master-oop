package po2.exercises;

import java.util.ArrayList;
import java.util.Iterator;

public class RevArrayList<T> extends ArrayList<T> {

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>(){
            private int index = RevArrayList.this.size() - 1;
            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public T next() {
                return RevArrayList.this.get(index--);
            }
            
        };
    }

}