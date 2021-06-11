package po2.exercises;

import java.util.Iterator;

public class FibonacciIterator implements Iterator<Integer> {
    private int length, index = 0, fib1 = 0, fib2 = 1;;

        public FibonacciIterator(int length) {
            this.length = length;
        }

        @Override
        public boolean hasNext() {
            return index < length;
        }

        @Override
        public Integer next() {
            int tmp1 = fib1, tmp2 = fib2;
            fib1 = fib2;
            fib2 = tmp1 + tmp2;
            index++;
            return tmp1;
        }
}
