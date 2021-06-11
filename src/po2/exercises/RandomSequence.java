package po2.exercises;

import java.util.Iterator;
import java.util.Random;

public class RandomSequence implements Iterable<Integer> {

    private int length, max;
    private Random random;

    public RandomSequence(int length, int max) {
        this.length = length;
        this.max = max;
        this.random = new Random();
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>(){
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public Integer next() {
                index++;
                return random.nextInt(max);
            }
            
        };
    }
    
}
