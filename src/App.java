import java.util.*;

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
                return (o1.age - o2.age);
            }
            
        });

        System.out.println("Ordinato crescente: \n");

        System.out.println(Arrays.toString(animals.toArray()));

        // ordina in ordine decrescente
        animals.sort(new Comparator<Animal>(){

            @Override
            public int compare(Animal o1, Animal o2) {
                return (o2.age - o1.age);
            }
            
        });

        System.out.println("\nOrdinato decrescente: \n");

        System.out.println(Arrays.toString(animals.toArray()));
        
        // ordina in ordine alfabetico decrescente
        animals.sort(new Comparator<Animal>(){

            @Override
            public int compare(Animal o1, Animal o2) {
                return o2.name.compareTo(o1.name);
            }
            
        });

        System.out.println("\nOrdinato Alfabeticamente decrescente: \n");

        System.out.println(Arrays.toString(animals.toArray()));

        // ordina in ordine alfabetico crescente
        animals.sort(new Comparator<Animal>(){

            @Override
            public int compare(Animal o1, Animal o2) {
                return o1.name.compareTo(o2.name);
            }
            
        });

        System.out.println("\nOrdinato Alfabeticamente crescente: \n");

        System.out.println(Arrays.toString(animals.toArray()));

        System.out.println("\n\n");

    }
}
