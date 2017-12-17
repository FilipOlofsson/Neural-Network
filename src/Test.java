import java.io.*;
import java.util.Random;
import java.util.concurrent.Executor;

public class Test {
    
    static Point[] TrainingInputs;
    
    
    /*
    The proper inputs, not for training
     */
    static Point[] realInputs = {
            new Point(478,134),
            new Point(55,86),
            new Point(647,394),
            new Point(370,369),
            new Point(797,58),
            new Point(409,297),
            new Point(409,534),
            new Point(537,281),
            new Point(277,497),
            new Point(192,393),
    };
    
    public static void main(String[] args) throws IOException {
        generateInputs(100);
        Execute();
    }
    
    /*
    Function that trains until the network can guess all points' values correctly.
    
    Does not currently work.
     */
    static void Execute() throws IOException {
        TrainingInputs = loadInputs(100);
        int incorrect;
        int iterations = 0;
        do {
            incorrect = 0;
            Perceptron hidden = new Perceptron();
            for(int i = 0; i < TrainingInputs.length; i++) {
                hidden.train(TrainingInputs[i]);
            }
            for(int i = 0; i < realInputs.length; i++) {
                if(hidden.guess(realInputs[i]) != realInputs[i].getValue()) {
                    incorrect++;
                }
            }
            iterations++;
        } while(incorrect != 0);
        System.out.println(iterations);
        
    }
    
    /*
    Load all training points from a file
     */
    static Point[] loadInputs(int amount) throws IOException {
        File inputFile = new File("TrainingInputs.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        Point[] inputs = new Point[amount];
        
        for(int i = 0; i < inputs.length; i++) {
            String line = reader.readLine();
            String[] split = line.split(",");
            inputs[i] = new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }
        return inputs;
    }
    
    /*
    Randomize a select number of points to generate into the file for loadInputs() to load later.
     */
    private static void generateInputs(int amount) throws IOException {
        File inputsFile = new File("TrainingInputs.txt");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(inputsFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for(int i = 0; i < amount; i++) {
            Point point = new Point();
            writer.write(point.getX() + "," + point.getY() + "\n");
        }
        writer.close();
    }
    
}
