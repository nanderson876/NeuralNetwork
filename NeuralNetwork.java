import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.lang.Math;

/**
 * NeuralNetwork class. This holds almost all of the actual functions of this program
 */
public class NeuralNetwork {
    private double[] weights; // double array that will contain the weights for the inputs

    /**
     * Basic constructor that randomly assigns weights
     *
     * @param numInputs : Sets the amount of inputs to be expected
     */
    public NeuralNetwork(int numInputs) {
        weights = new double[numInputs]; // Creates the array
        for (int i = 0; i < weights.length; i++) {
            weights[i] = Math.random(); // Fills the array with random weights
        }
    }

    /**
     * Overloaded constructor that also trys to read weights from a provided file, if the file does not exist or
     * does not contain the correct number of weights, then the constructor sets the weights randomly
     *
     * @param numInputs : Sets the amount of inputs to be expected
     * @param file      : The File Object that will be read for the weights
     * @throws FileNotFoundException
     */
    public NeuralNetwork(int numInputs, File file) throws FileNotFoundException {
        weights = new double[numInputs]; // Creates the array

        if (file.exists()) {
            Scanner fileInput = new Scanner(file);
            String[] weightStrings = fileInput.nextLine().split(" ");

            if (numInputs == weightStrings.length) {
                for (int i = 0; i < weightStrings.length; i++) {
                    weights[i] = Double.parseDouble(weightStrings[i]);
                }
            }
        } else {
            for (int i = 0; i < weights.length; i++) {
                weights[i] = Math.random();
            }
        }
    }

    /**
     * Takes an initial value and it's range, and returns the value mapped to a different range
     *
     * This method is heavily influenced by the method map() in Processing
     * Link: https://processing.org/reference/map_.html
     *
     * @param initialValue : The value to be modified
     * @param min1         : The lower bound of the initial range
     * @param max1         : The upper bound of the initial range
     * @param min2         : The lower bound of the new range
     * @param max2         : The upper bound of the new range
     * @return             : The new value
     */
    private static double mapValues(double initialValue, double min1, double max1, double min2, double max2) {
        return min2 + ((max2 - min2) / (max1 - min1)) * (initialValue - min1);
    }

    /**
     * The main method in this class file is only used for testing, the real main method is in the Main class
     *
     * @param args : String array of command-line arguments
     */
    public static void main(String[] args) {
        // The main purpose of mapping values in this program is converting + to 1 and - to -1
        System.out.println(mapValues('+', '+', '-', 1, -1));
    }
}
