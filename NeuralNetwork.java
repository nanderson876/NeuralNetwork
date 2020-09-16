import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;

/**
 * Abstract Neural Network covering the basics and allowing for simpler creation of more complicated and detailed neural networks
 * 
 * Date Last Modified: 09/16/20
 * @author Nathan Anderson
 */
public abstract class NeuralNetwork {
    private double[] inputs; // int array to contain the inputs
    private double[] weights; // double array that will contain the weights for the inputs

    /**
     * Basic constructor that randomly assigns weights
     *
     * @param numInputs : Sets the amount of inputs to be expected
     */
    public NeuralNetwork(int numInputs) {
        inputs = new double[numInputs];
        weights = new double[numInputs]; // Creates the array

        assignRandomWeights();
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
        inputs = new double[numInputs];
        weights = new double[numInputs]; // Creates the array

        if (file.exists()) {
            Scanner fileInput = new Scanner(file);
            String[] weightStrings = fileInput.nextLine().split(" ");
            fileInput.close();

            if (numInputs == weightStrings.length) {
                for (int i = 0; i < weightStrings.length; i++) {
                    weights[i] = Double.parseDouble(weightStrings[i]);
                }
            } else {
                assignRandomWeights();
            }
        } else {
            assignRandomWeights();
        }
    }

    /**
     * Assigns a random weight for each input. This is placed in a seperate method because it is used across both constructors.
     */
    private void assignRandomWeights() {
        for (int i = 0; i < weights.length; i++) {
            weights[i] = Math.random();
        }
    }

    /**
     * Takes a number of int values and stores them in the inputs array
     *
     * @param values : Variable arguments of int values to be collected into the inputs array
     */
    public void collectInputs(double ... values) {
        // Only collects into inputs array if the number of inputs matches the number specified in the constuctor
        if (inputs.length == values.length) {
            for (int i = 0; i < inputs.length; i++) {
                inputs[i] = values[i];
            }
        } else {
            return;
        }
    }

    /**
     * Maps every input to a specified range
     * 
     * @param inputRanges : 2-D array containing the ranges of each input.  
     * @param newRanges   : 2-D array containing the ranges for each output.
     * Note: Each parameter must have a length equal to the number of inputs, and each nested array must have exactly 2 values, a lower and upper bound.
     */
    public void mapInputs(double[][] inputRanges, double[][] newRanges) {
        // Verifying the parameter value
        if (inputRanges.length == inputs.length && newRanges.length == inputs.length &&
            inputRanges[0].length == 2 && newRanges[0].length == 2) {

            for (int i = 0; i < inputs.length; i++) {
                inputs[i] = mapValues(inputs[i], inputRanges[i][0], inputRanges[i][1], newRanges[i][0], newRanges[i][1]);
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
     * Overrides the Object toString method, returning basic information about the Object
     * 
     * @return : Information about the network such as number of inputs a String representation of the input and weight arrays
     */
    @Override
    public String toString() {
        String inputString = "[" + this.inputs[0];
        String weightString = "[" + this.weights[0];
        for (int i = 0; i < this.inputs.length; i++) {
            inputString += ", " + inputs[i];
            weightString += ", " + weights[i];
        }
        return String.format("Number of Inputs/Weights: %d\nInputs:\t%s\nWeights:\t%s\n", inputs.length, inputString, weightString);
    }
}
