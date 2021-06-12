package po2.patterns;

public class Factory {
    
    public static interface Shape {

    }

    public static class Square implements Shape {

    }

    public static class Triangle implements Shape {
        
    }

    public static class Circle implements Shape {
        
    }

    public static class Line implements Shape {
        
    }

    public static Shape getShape(int lati) {
        switch(lati) {
            case 0:
                return new Circle();
            case 1:
                return new Line();
            case 3:
                return new Triangle();
            case 4:
                return new Square();
            default:
                return new Circle();
        }
    }

}
