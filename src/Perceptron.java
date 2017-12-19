import java.util.Random;

public class Perceptron {
    
    float learningRate = 0.001f;    // Makes training slower to not make it "skip over" the perfect weights.
    
    /**
     Simplified signum function
     @param sum The sum of all weights multiplied with the inputs
     */
    int sign(float sum) {
        if(sum >= 0)
            return 1;
        else
            return -1;
    }
    
    float[] weights = new float[2];
    
    Random rnd = new Random();
    
    /*
    Constructor, randomizes all weights.
     */
    Perceptron() {
        for(int i = 0;i < weights.length; i++) {
            weights[i] = rnd.nextFloat() * 2 - 1;
        }
    }
    
    /**
     Perform a guess of the point's value
     @param point point to guess the value from
     */
    int guess(Point point) {
        float sum = 0;
        sum += weights[0] * point.getX();
        sum += weights[1] * point.getY();
        return sign(sum);
    }
    
    /**
     Train the perceptron with a point.
     @param point Point to train the perceptron from
     */
    void train(Point point) {
        float error = point.getValue() - guess(point);
    
        weights[0] += error * point.getX() * learningRate;
        weights[1] += error * point.getY() * learningRate;

        //System.out.println("Weight1 = " + weights[0]);
        //System.out.println("Weight2 = " + weights[1]);
    }
    
}
