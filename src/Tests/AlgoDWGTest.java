package Tests;

import Departments.AlgoDWG;
import Departments.Edge;
import Departments.GeoPosition;
import Departments.Node;
import api.DirectedWeightedGraph;
import api.EdgeData;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AlgoDWGTest {


        static AlgoDWG a1 = new AlgoDWG();
        boolean t = a1.load("src/data/Tests.json");
        public DirectedWeightedGraph g1 = a1.getGraph();
        public Node node1 = (Node) g1.getNode(1);
        public Map<Point2D, EdgeData> edgesOut1 = node1.getEdgeMapOut();
        public Point2D p1 = new Point(1, 2);
        public Point2D p2 = new Point(1, 3);
        public Point2D p3 = new Point(1, 7);
        public Point2D p4 = new Point(1, 9);
        Edge e1 = (Edge) edgesOut1.get(p1);
        GeoPosition test = new GeoPosition(1, 2, 3);
        GeoPosition test2 = new GeoPosition(12, 23, 31);
        //----------------------------------------------------------------
        static AlgoDWG a2 = new AlgoDWG();
        boolean c = a2.load("src/data/connectedGraph.json");
        public DirectedWeightedGraph g2 = a2.getGraph();
        public Node node2 = (Node) g2.getNode(2);
        public Map<Point2D, EdgeData> edgesOut2 = node1.getEdgeMapOut();
        Edge e2 = (Edge) edgesOut2.get(p1);
        //------------------------------------
        static AlgoDWG a3 = new AlgoDWG();

         boolean k = a3.load("src/data/G1.json");


    public DirectedWeightedGraph g3 = a3.getGraph();
    public Node node3 = (Node) g3.getNode(3);
    public Map<Point2D, EdgeData> edgesOut3 = node3.getEdgeMapOut();
        //--------------------------------------------------------
    static AlgoDWG a4 = new AlgoDWG();
    boolean p = a4.load("src/data/G2.json");
    public DirectedWeightedGraph g4 = a4.getGraph();
    public Node node4 = (Node) g4.getNode(4);
    public Map<Point2D, EdgeData> edgesOut4 = node4.getEdgeMapOut();
    //-----------------------------------------------------------
    static AlgoDWG a5 = new AlgoDWG();
    boolean o = a5.load("src/data/test.json");
    public DirectedWeightedGraph g5 = a5.getGraph();
    public Node node5 = (Node) g5.getNode(5);
    public Map<Point2D, EdgeData> edgesOut5 = node5.getEdgeMapOut();


    @Test
    void isConnected() {
        assertTrue(a2.isConnected());
        assertFalse(a1.isConnected());

    }

    @Test
    void shortestPathDist() {
        assertEquals(a2.shortestPathDist(5,1),10);
    }


    @Test
    void center() {
        assertEquals(a4.center(),g4.getNode(0));
        assertEquals(a3.center(),g3.getNode(8));
        assertEquals(a2.center(),g2.getNode(3));
    }

    @Test
    void tsp() {
        assertEquals(a5.tsp(a5.shortestPath(1,5)).toString(),"[{\n" +
                "      \"pos\": \"35.20319591121872,32.10318254621849,0.0\",\n" +
                "      \"id\":1\n" +
                "    }, {\n" +
                "      \"pos\": \"35.21007339305892,32.10107446554622,0.0\",\n" +
                "      \"id\":3\n" +
                "    }, {\n" +
                "      \"pos\": \"35.21310882485876,32.10463639495799,0.0\",\n" +
                "      \"id\":4\n" +
                "    }, {\n" +
                "      \"pos\": \"35.21211116545602,32.10623562857143,0.0\",\n" +
                "      \"id\":5\n" +
                "    }, {\n" +
                "      \"pos\": \"35.21310882485876,32.10463639495799,0.0\",\n" +
                "      \"id\":4\n" +
                "    }, {\n" +
                "      \"pos\": \"35.21007339305892,32.10107446554622,0.0\",\n" +
                "      \"id\":3\n" +
                "    }, {\n" +
                "      \"pos\": \"35.20319591121872,32.10318254621849,0.0\",\n" +
                "      \"id\":1\n" +
                "    }]");
        assertEquals(a1.tsp(a1.shortestPath(1,9)),null);




    }

}