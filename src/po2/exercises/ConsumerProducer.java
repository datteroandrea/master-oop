package po2.exercises;

import java.util.*;

public class ConsumerProducer {

    private static LinkedList<Integer> buffer = new LinkedList<>();

    public static class Producer extends Thread {

        @Override
        public void run() {
            int i = 0;
            while(true) {
                synchronized(buffer) {
                    System.out.println(String.format("Produco %d: %d", Thread.currentThread().getId(), i));
                    buffer.add(i++);
                    i = i % 10;
                    buffer.notifyAll();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    
    public static class Consumer extends Thread {

        @Override
        public void run() {
            while(true) {
                synchronized(buffer) {
                    if(buffer.isEmpty()) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(String.format("Consumo %d: %d", Thread.currentThread().getId(), buffer.remove(0)));
                    }
                }
            }
        }

    }

}
