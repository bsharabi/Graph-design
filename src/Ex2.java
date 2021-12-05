import Departments.AlgoDWG;
import Departments.Node;
//import GUI.Graph_GUI;
//import oldGUI.GraphGUI;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;

import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    public static void main(String[] args) {
        DirectedWeightedGraphAlgorithms ans = getGrapgAlgo("src/data/out.json");
        ((AlgoDWG) ans).print();
        System.out.println(ans);

        ans.getGraph().removeNode(9);

        ((AlgoDWG) ans).print();
        System.out.println(ans);
//        runGUI("src/data/out.json");

    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph ans = getGrapgAlgo(json_file).getGraph();
        return ans;
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = new AlgoDWG();
        if (ans.load(json_file))
            return ans;
        return null;
    }

    /**
     * This static function will run your oldGUI.GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
//        new Thread(new Graph_GUI(alg), "oldGUI.GUI-D").start();
    }
}