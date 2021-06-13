import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Function;

public class App2 {

    public static class Person {
        int age;

        public Person(int age) {
            this.age = age;
        }

        public void speak(Person p) {
            System.out.println("Speak to person 1");
        }

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
    
    public static void main(String[] args) {
        LinkedList<Person> persons = new LinkedList<>();
        LinkedList<Teacher> teachers = new LinkedList<>();

        persons.add(new Person(111));
        persons.add(new Teacher(90, 15)); // covarianza

        teachers.add(new Teacher(21, 15));
        teachers.add(new Teacher(30, 13));

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

        mapIterator(teachers.iterator(), (a) -> {
            
            return 1;
        });
    }

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



}
