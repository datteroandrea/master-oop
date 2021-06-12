package po2.patterns;

import java.util.*;
import java.util.concurrent.locks.Lock;

public class ResourceAllocator {
    private Lock lock;
    private List<Resource<Integer>> resources;

    public ResourceAllocator() {
        resources = new LinkedList<>();
    }


    public void allocateResources() {
        lock.lock();
        try {
            for(int i = 0; i < 10; i++) {
                resources.add(new Resource<Integer>(i % 5));
            }
        } finally {
            lock.unlock();
        }
    }

    private static class Resource<T> {
        private T elem;

        public Resource(T elem) {
            this.elem = elem;
        }

        public T getElem() {
            return elem;
        }
    }
    
}
