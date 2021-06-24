package po2;
import java.util.*;
import java.util.concurrent.Future;
import java.util.function.*;

import po2.App.Person.Teacher;
import po2.exercises.*;

public class App {
    private static int AMOUNT = 10;

    public static class Animal {
        private int age;
        private String name;
    
        public Animal(int age, String name) {
            this.age = age;
            this.name = name;
        }
    
        @Override
        public String toString() {
            return String.format("{ age: %d, name: %s}", age, name);
        }
    
        public static class Human extends Animal {
    
            protected final String surname;
    
            public Human(int age, String name, String surname) {
                super(age, name);
                this.surname = surname;
            }
    
        }
    
        public static class Dog extends Animal {
    
            public Dog(int age, String name) {
                super(age, name);
                //TODO Auto-generated constructor stub
            }
            
        }
    
        public static class Cat extends Animal {
    
            public Cat(int age, String name) {
                super(age, name);
                //TODO Auto-generated constructor stub
            }
            
        }
    
        public int getAge() {
            return age;
        }
    
        public String getName() {
            return name;
        }
    
    }

    public static class Person {
        int age;

        public Person(int age) {
            this.age = age;
        }

        public void speak(Person p) {
            System.out.println("Speak to person 1");
        }

        public static class Teacher extends Person {

            public Teacher(int age, int experience) {
                super(age);
                this.experience = experience;
            }
    
            int experience;
    
            @Override
            public void speak(Person p) {
                System.out.println("Speak to person 2");
            }
    
            public void speak(Teacher p) {
                System.out.println("Speak to teacher");
            }
    
            @Override
            public String toString() {
                return String.format("age: %d, experience: %d", age, experience);
            }
        }

    }

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

        System.out.println(Functional.sum(revArrayList, 0, new BiFunction<Integer,Integer,Integer>(){

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
        

        Iterator<Future<Integer>> it = Functional.mapIteratorMultiThreading(List.of(1,2,3,4,5,6,7,8,9,10).iterator(), new Function<Integer,Integer>(){

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

        LinkedList<Person> persons = new LinkedList<>();
        LinkedList<Person.Teacher> teachers = new LinkedList<>();

        persons.add(new Person(111));
        persons.add(new Person.Teacher(90, 15)); // covarianza

        teachers.add(new Person.Teacher(21, 15));
        teachers.add(new Person.Teacher(30, 13));

        // controvarianza
        teachers.sort(new Comparator<Person>(){
            public int compare(Person o1, Person o2) {
                return o1.age - o2.age;
            };
        });

        System.out.println(Arrays.toString(teachers.toArray()));

        teachers.sort(new Comparator<Teacher>(){
            public int compare(Teacher o1, Teacher o2) {
                return o1.experience - o2.experience;
            };
        });
    }

}
