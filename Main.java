/**
 * This is a single-layer Neural Network that will improve itself and (hopefully) eventually be able to solve simple
 * two digit addition and subtraction
 *
 * Date Last Modified: 09/16/20
 * @author Nathan Anderson
 */
public class Main {

    /**
     * Main method
     *
     * @param args : String array of command-line arguments
     */
    public static void main(String[] args) {
        NeuralNetwork network = new NeuralNetwork(3);
        network.collectInputs(3, '-', 2);
        System.out.println(network);
    }

}
