import Departments.*;
import GUI.*;
import api.DirectedWeightedGraph;

import org.json.JSONException;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException, JSONException {
//        new Thread(new GraphGUI(), "GUI.GUI-D").start();

        AlgoDWG a = new AlgoDWG();
        a.load("src/data/G1.json");
        DirectedWeightedGraph g = a.getGraph();
        System.out.println(g);

//////        //Test for
//        System.out.println("------------- getNode--------------");
//        System.out.println(g.getNode(0));
//        System.out.println(g.getNode(10));
//
//        System.out.println("------------- getEdge--------------");
//        System.out.println(g.getEdge(0, 2));
//        System.out.println(g.getEdge(0, 16));
//
//        // Problem
//        System.out.println("------------- addNode --------------");
//        g.addNode(new Node(new GeoPosition(1, 2, 3), 4));
//        System.out.println(g);
//
//        System.out.println("------------- connect --------------");
//        g.connect(4, 2, 6);
//        System.out.println(g);
//
//
//        System.out.println("------------- nodeIter--------------");
//        Iterator it = g.nodeIter();
//        while (it.hasNext())
//            System.out.println(it.next());
//
//        System.out.println("------------- edgeIter--------------");
//        it = g.edgeIter();
//        while (it.hasNext())
//            System.out.println(it.next());
//
//
//        System.out.println("------------- edgeIter by node id--------------");
//        it = g.edgeIter(0);
//        while (it.hasNext())
//            System.out.println(it.next());
//
//        //add error
//        System.out.println("------------- removeNode--------------");
//        NodeData d = g.removeNode(2);
//        System.out.println(g);
//
//        //add error
//        System.out.println("------------- removeEdge--------------");
//        EdgeData e = g.removeEdge(0, 1);
//        System.out.println(g);
//        System.out.println(e);
//
//
//        System.out.println("------------- nodeSize--------------");
//        System.out.println(g.nodeSize());
//
//        System.out.println("------------- edgeSize--------------");
//        System.out.println(g.edgeSize());




    }
}







