import java.util.*;
import java.util.function.Function;

import animali.Animal;
import fabbrica.Factory;

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

        System.out.println("\n\n");

        LinkedList<Integer> integers = new LinkedList<>();
        
        for(int i = 0; i < AMOUNT; i++) {
            integers.add(i + 1);
        }

        Factory f = new Factory();

        RandomIterator.RandomNumberIterator rintegers = new RandomIterator.RandomNumberIterator(AMOUNT, 0, 50);

        List<Double> rdoubles = f.produce(integers, new Function<Integer, Double>(){

            @Override
            public Double apply(Integer t) {
                return Math.sqrt(Math.pow(t.intValue(), 2) + Math.pow(rintegers.next(), 2));
            }
            
        });

        System.out.println(Arrays.toString(rdoubles.toArray()));

    }
}
