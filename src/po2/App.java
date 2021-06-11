package po2;
import java.util.*;
import java.util.function.*;
import po2.animali.*;
import po2.exercises.*;

public class App {
    private static int AMOUNT = 10;

    public static void main(String[] args) throws Exception {
        LinkedList<Animal> animals = new LinkedList<>();
        RandomIterator.RandomNumberIterator rage = new RandomIterator.RandomNumberIterator(AMOUNT, 0, 50);
        RandomIterator.RandomStringIterator rname = new RandomIterator.RandomStringIterator(AMOUNT, 10, "abcdefgjkhilmnopqrstuxwxyz");

        for(int i = 0; i < AMOUNT; i++) {
            animals.add(new Animal(((Number)rage.next()).intValue(), rname.next()));
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

        RandomSequence rs = new RandomSequence(AMOUNT, AMOUNT);

        for(Integer i : rs) {
            System.out.println(i);
        }
        
        System.out.println("");

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

    // scrivi un consumer producer senza BlockingQueue gestisci manualmente la concorrenza

}
