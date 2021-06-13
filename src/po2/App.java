package po2;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.*;
import po2.animali.*;
import po2.exercises.*;
import po2.exercises.ConsumerProducer.Producer;

public class App {
    private static int AMOUNT = 10;


    public static void main(String[] args) throws Exception {
        LinkedList<Animal> animals = new LinkedList<>();
        Iterator<Integer> rage = new RandomSequence.RandomIntegerSequence(AMOUNT, 100).iterator();

        Iterator<String> rss = new RandomSequence.RandomStringSequence(AMOUNT, 10, "abcdefghijklmnopqrstuvwxyz").iterator();

        for(int i = 0; i < AMOUNT; i++) {
            animals.add(new Animal(rage.next(), rss.next()));
        }

        // ordina in ordine crescente
        animals.sort(new Comparator<Animal>(){

            @Override
            public int compare(Animal o1, Animal o2) {
                return (o1.getAge() - o2.getAge());
            }
            
        });

        System.out.println("Ordinato crescente: \n");

        System.out.println(Arrays.toString(animals.toArray()));

        // ordina in ordine decrescente
        animals.sort(new Comparator<Animal>(){

            @Override
            public int compare(Animal o1, Animal o2) {
                return (o2.getAge() - o1.getAge());
            }
            
        });

        System.out.println("\nOrdinato decrescente: \n");

        System.out.println(Arrays.toString(animals.toArray()));
        
        // ordina in ordine alfabetico decrescente
        animals.sort(new Comparator<Animal>(){

            @Override
            public int compare(Animal o1, Animal o2) {
                return o2.getName().compareTo(o1.getName());
            }
            
        });

        System.out.println("\nOrdinato Alfabeticamente decrescente: \n");

        System.out.println(Arrays.toString(animals.toArray()));

        // ordina in ordine alfabetico crescente
        animals.sort(new Comparator<Animal>(){

            @Override
            public int compare(Animal o1, Animal o2) {
                return o1.getName().compareTo(o2.getName());
            }
            
        });

        System.out.println("\nOrdinato Alfabeticamente crescente: \n");

        System.out.println(Arrays.toString(animals.toArray()));

        System.out.println("\n");

        LinkedList<Integer> integers = new LinkedList<>();
        
        for(int i = 0; i < AMOUNT; i++) {
            integers.add(i + 1);
        }

        FibonacciIterator fibit = new FibonacciIterator(10);

        while(fibit.hasNext()) {
            System.out.println(fibit.next());
        }

        System.out.println("");

        RevArrayList<Integer> revArrayList = new RevArrayList<>();

        for(int i = 0; i < 10; i++) {
            revArrayList.add(i + 1);
        }

        for(Integer i : revArrayList) {
            System.out.println(i);
        }

        System.out.println("");

        System.out.println(sum(revArrayList, 0, new BiFunction<Integer,Integer,Integer>(){

            @Override
            public Integer apply(Integer t, Integer u) {
                return t + u;
            }
            
        }));

        System.out.println("");
        
        SkipArrayList<Integer> arrList = new SkipArrayList<>(2);

        for(int i = 0; i < 10; i++) {
            arrList.add(i + 1);
        }

        for(Integer i : arrList) {
            System.out.println(i);
        }

        System.out.println("");
        
        for(int i = 0; i < 2; i++) {
            new ConsumerProducer.Consumer().start();
            new ConsumerProducer.Producer().start();
        }
        

        Iterator<Future<Integer>> it = mapIteratorMultiThreading(List.of(1,2,3,4,5,6,7,8,9,10).iterator(), new Function<Integer,Integer>(){

            @Override
            public Integer apply(Integer t) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(t);
                return t;
            }
            
        });

        while(it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("finito");
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

    public static <A> A sum(Iterable<A> iterA, A first, BiFunction<A,A,A> funcA) {
        A res = first;
        for(A a : iterA) {
            res = funcA.apply(res, a);
        }
        return res;
    }

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
