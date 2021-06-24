package po2.exercises;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.*;

public class Functional {

    // creami una funzione mapIterator che mi prende un iteratore di A e mi ritorna un iteratore di B
    // applicandoci la funzione f
    public static <A, B> Iterator<B> mapIterator(Iterator<A> it, Function<? super A, ? extends B> f) {

        return new Iterator<B>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public B next() {
                return f.apply(it.next());
            }
        };
    }

    // Creami una funzione mapIterator che prende Iterator<A>
    // e ci applica la funzione f se lancia un'eccezione f.apply(x) ritorna un B da supply.
    public static <A,B> Iterator<B> mapIterator(Iterator<A> iterA, Function<A,B> func, Supplier<B> supp) {
        return new Iterator<B>(){
            @Override
            public boolean hasNext() {
                return iterA.hasNext();
            }

            @Override
            public B next() {
                try {
                    return func.apply(iterA.next());
                } catch(Exception e) {
                    return supp.get();
                }
            }
            
        };
    }

    // creami una funzione che prende un iteratore di A e esegue la somma dei vari elementi applicandoci funcA
    public static <A> A sum(Iterable<A> iterA, A first, BiFunction<A,A,A> funcA) {
        A res = first;
        for(A a : iterA) {
            res = funcA.apply(res, a);
        }
        return res;
    }

    // creami una funzione di mapping di un iteratore che esegue la next lanciando un thread
    public static <A,B> Iterator<Future<B>> mapIteratorMultiThreading(Iterator<A> iterA, Function<A,B> func) {
        return new Iterator<Future<B>>() {
            
            @Override
            public boolean hasNext() {
                return iterA.hasNext();
            }

            @Override
            public Future<B> next() {
                ExecutorService exs = Executors.newSingleThreadExecutor();
                Future<B> future = exs.submit(new Callable<B>(){

                    @Override
                    public B call() throws Exception {
                        return func.apply(iterA.next());
                    }
                    
                });
                return future;
            }
            
        };
    }

    // creami una funzione di mapping di un iteratore che esegue la next lanciando un thread
    @SuppressWarnings("unchecked")
    public static <A,B> Iterator<Supplier<B>> mapIteratorMultiThreading2(Iterator<A> iterA, Function<A,B> func) {
        return new Iterator<Supplier<B>>() {
            
            @Override
            public boolean hasNext() {
                return iterA.hasNext();
            }

            @Override
            public Supplier<B> next() {
                Supplier<B>[] supp = new Supplier[1];
                new Thread(() -> {
                    B val = func.apply(iterA.next());
                    supp[0] = new Supplier<B>(){

                        @Override
                        public B get() {
                            return val;
                        }
                        
                    };
                }).start();
                return supp[0];
            }
            
        };
    }
}
