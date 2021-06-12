package po2.exercises;

import java.util.Iterator;
import java.util.Random;

public abstract class RandomSequence<T> implements Iterable<T> {

    private int length;
    private Random random;

    public RandomSequence(int length) {
        this.length = length;
        this.random = new Random();
    }

    public static class RandomIntegerSequence extends RandomSequence<Integer> {

        private int max;

        public RandomIntegerSequence(int length, int max) {
            super(length);
            this.max = max;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>(){
                private int index = 0;

                @Override
                public boolean hasNext() {
                    return index < RandomIntegerSequence.super.length;
                }

                @Override
                public Integer next() {
                    index++;
                    return RandomIntegerSequence.super.random.nextInt(RandomIntegerSequence.this.max);
                }
                
            };
        }

    }

    public static class RandomStringSequence extends RandomSequence<String> {

        private int max;
        private String seed;

        public RandomStringSequence(int length, int max) {
            super(length);
            this.max = max;
        }

        public RandomStringSequence(int length, int max, String seed) {
            super(length);
            this.max = max;
            this.seed = seed;
        }

        @Override
        public Iterator<String> iterator() {
            return new Iterator<String>(){
                private int index = 0;

                @Override
                public boolean hasNext() {
                    return index < RandomStringSequence.super.length;
                }

                @Override
                public String next() {
                    index++;
                    String result = "";
                    if(RandomStringSequence.this.seed == null && RandomStringSequence.this.seed.isEmpty()) {
                        for(int i = 0; i < max; i++) {
                            result += RandomStringSequence.super.random.nextInt(200) + 1;
                        }
                    } else {
                        for(int i = 0; i < max; i++) {
                            result += RandomStringSequence.this.seed.charAt(RandomStringSequence.super.random.nextInt(seed.length()));
                        }
                    }
                    return result;
                }
                
            };
        }

    }
    
}
