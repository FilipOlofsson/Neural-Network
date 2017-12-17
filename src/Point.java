import java.util.Random;

public class Point {
    
    private int x, y;
    private int value; // What the neural net tries to guess
    
    
    /*
    Constructor for the Point class, randomizes the x and y values and assigns it a value depending on the coordinates.
     */
    Point() {
        Random rnd = new Random();
        x = rnd.nextInt(800);
        y = rnd.nextInt(600);
        if((x % 2 == 0) && (y % 2 == 0)) {                        // If both x and y are divisible by two, set
            value = 1;                                            // value to 1.
        } else {
            value = 0;
        }
    }
    
    Point(int x, int y) {
        if((x % 2 == 0) && (y % 2 == 0)) {                        // If both x and y are divisible by two, set
            value = 1;                                            // value to 1.
        } else {
            value = 0;
        }
        this.x = x;
        this.y = y;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

}
