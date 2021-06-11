package po2;
import java.util.Iterator;
import java.util.Random;

public abstract class RandomIterator implements Iterator<Object> {

    private final int length;
    private final Random random;

    public RandomIterator(int length, Random random) {
        this.length = length;
        this.random = random;
    }

    @Override
    public boolean hasNext() {
        return length > 0;
    }

    public static class RandomNumberIterator extends RandomIterator {

        private Number min, max;

        public RandomNumberIterator(int length, Number min, Number max) {
            super(length, new Random());
            this.min = min;
            this.max = max;
        }

        @Override
        public Float next() {
            return min.floatValue() + super.random.nextFloat() * (max.floatValue() - min.floatValue());
        }

    }

    public static class RandomStringIterator extends RandomIterator {

        private int stringLength;
        private String characters;

        public RandomStringIterator(int length, int stringLength, String characters) {
            super(length, new Random());
            this.stringLength = stringLength;
            this.characters = characters;
        }

        @Override
        public String next() {
            String result = "";
            for(int i = 0; i < stringLength; i++) {
                result += characters.charAt(super.random.nextInt(characters.length()));
            }
            return result;
        }

    }

}
