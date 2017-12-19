import java.io.*;

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
        generateInputs(10);
        TrainingInputs = loadInputs(10);
        Execute(10);
    }
    
    /**
    Function that trains until the network can guess all points' values correctly.
    
    Does not currently work.
     */
    static void Execute(int times) throws IOException {
        int incorrect;
        while(times > 0) {
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
            //save(iterations);
            times--;
            System.out.println(iterations);
        }
    }
    
    static void save(int toWrite) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("outputs.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        writer.write(toWrite + "\n");
        writer.flush();
        
    }
    
    /**
     Load all training points from a file
     @param amount The amount of inputs to generate
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
    
    /**
    Randomize a select number of points to generate into the file for loadInputs() to load later.
    @param amount The amount of inputs to generate
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
