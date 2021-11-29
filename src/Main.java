import Departments.*;
import GUI.*;
import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
//        new Thread(new GraphGUI(), "GUI.GUI-D").start();
        Map<Point2D, EdgeData> edgesMap = new HashMap<>();
        Map<Integer, NodeData> nodesMap = new HashMap<>();
        nodesMap.put(0, new Node(new GeoPosition(1, 2, 3), 0));
        nodesMap.put(1, new Node(new GeoPosition(7, 6, 3), 1));
        nodesMap.put(2, new Node(new GeoPosition(2, 2, 8), 2));
        nodesMap.put(3, new Node(new GeoPosition(7, 2, 0), 3));

        edgesMap.put(new Point(0, 3), new Edge(0, 48, 3));
        edgesMap.put(new Point(0, 1), new Edge(0, 5, 1));
        edgesMap.put(new Point(1, 0), new Edge(1, 4, 0));
        edgesMap.put(new Point(3, 2), new Edge(3, 5, 2));
        edgesMap.put(new Point(2, 1), new Edge(2, 4, 1));
        edgesMap.put(new Point(3, 0), new Edge(3, 6, 0));


        DirectedWeightedGraph g = new Graph(nodesMap, edgesMap);
        System.out.println(g);
        //Test for
        System.out.println("------------- getNode--------------");
        System.out.println(g.getNode(0));
        System.out.println(g.getNode(10));

        System.out.println("------------- getEdge--------------");
        System.out.println(g.getEdge(0, 3));
        System.out.println(g.getEdge(0, 16));

        //Problem
//        System.out.println("------------- addNode --------------");
//        g.addNode(new Node(new GeoPosition(1, 2, 3), 4));


        System.out.println("------------- nodeIter--------------");
        Iterator it = g.nodeIter();
        while (it.hasNext())
            System.out.println(it.next());

        System.out.println("------------- edgeIter--------------");
        it = g.edgeIter();
        while (it.hasNext())
            System.out.println(it.next());


        System.out.println("------------- edgeIter by node id--------------");
        it = g.edgeIter(0);
        while (it.hasNext())
            System.out.println(it.next());

//        System.out.println("------------- removeNode--------------");
//        NodeData d = g.removeNode(1);
//        System.out.println(g);
//        System.out.println(d);

//        System.out.println("------------- removeEdge--------------");
//        EdgeData e = g.removeEdge(0, 1);
//        System.out.println(g);
//        System.out.println(e);


        System.out.println("------------- nodeSize--------------");
        System.out.println(g.nodeSize());

        System.out.println("------------- edgeSize--------------");
        System.out.println(g.edgeSize());


//        Map<Point2D, String> map = new HashMap<>();
//        System.out.println(map);
//        Point2D p = new Point(1, 5);
//        Point2D p1 = new Point(1, 5);
//        Point2D p3 = new Point(1, 4);
//        map.put(p, "1");
//        map.put(p3, "2");
//        List l = map.values().stream().toList();
//        System.out.println(l);
//        JsonReader.getJson("src/data/G1.json");
//        System.out.println(p.getX());
//        p.setLocation(2,5);
//        System.out.println(p.getX());
//        System.out.println(Objects.equals(p3,p1));
//        System.out.println(map.get(p1));

//        Gson gson = new Gson();

//        try {
//
//            System.out.println("Reading JSON from a file");
//            System.out.println("----------------------------");
//            BufferedReader br = new BufferedReader(
//                    new FileReader("src/data/G1.json"));
//            //convert the json string back to object
//            Edge countryObj = gson.fromJson(br, Edge.class);
//            System.out.println("Name Of Country: "+countryObj);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}







