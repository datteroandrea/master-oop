package animali;

public class Animal {
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