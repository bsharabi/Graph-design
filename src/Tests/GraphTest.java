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

class GraphTest {

    static AlgoDWG a = new AlgoDWG();

    boolean t = a.load("src/data/Tests.json");


    public DirectedWeightedGraph g1 = a.getGraph();
    public Node node1 = (Node) g1.getNode(1);
    public Map<Point2D, EdgeData> edgesOut = node1.getEdgeMapOut();
    public Point2D p1 = new Point(1, 2);
    public Point2D p2 = new Point(1, 3);
    public Point2D p3 = new Point(1, 7);
    public Point2D p4 = new Point(1, 9);
    Edge e1 = (Edge) edgesOut.get(p1);
    GeoPosition test = new GeoPosition(1, 2, 3);
    GeoPosition test2 = new GeoPosition(12, 23, 31);



    @Test
    void getEdgesMap() {

        for(int i=1;i<9;i++){
            Node n = (Node) g1.getNode(i);
            for(EdgeData e:n.getEdgeMapOut().values()){
                assertEquals(e,g1.getEdge(n.getKey(),e.getDest()));

            }

        }

    }

    @Test
    void getNodesMap() {
       for(int i=1;i<9;i++){
           assertTrue(g1.getNode(i)!=null);
       }

    }

    @Test
    void getNode() {
        assertEquals(g1.getEdge((int)p1.getX(),(int)p1.getY()).getSrc(),g1.getNode((int)p1.getX()).getKey());
    }


    @Test
    void addNode() {
        Node n= new Node(test,30);
        g1.addNode(n);
        assertTrue(g1.getNode(30)==n);
    }

    @Test
    void connect() {
        g1.connect(1,9,5);
        assertTrue(g1.getEdge(1,9)!=null);

    }

    @Test
    void removeNode() {
        Node n= new Node(test,30);
        g1.addNode(n);
        assertTrue(g1.getNode(30)==n);
        g1.removeNode(30);
        assertFalse(g1.getNode(30)!=null);
    }

    @Test
    void removeEdge() {
        assertTrue(g1.getEdge(30,40)==null);
        Node n1= new Node(test,30);
        Node n2= new Node(test2,40);
//        Executable NullPointerException ;
//        assertThrows(g1.getEdge(30,40), NullPointerException);
        g1.addNode(n1);
        g1.addNode(n2);
        g1.connect(30,40,10);
        assertTrue(g1.getEdge(30,40)!=null);
        g1.removeEdge(30,40);
        assertTrue(g1.getEdge(30,40)==null);

    }

    @Test
    void nodeSize() {
        assertEquals(g1.nodeSize(),9);
    }

    @Test
    void edgeSize() {
        assertEquals(g1.edgeSize(),17);
    }

}