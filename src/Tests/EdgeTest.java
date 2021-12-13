package Tests;

import Departments.AlgoDWG;
import Departments.Edge;
import Departments.Node;
import api.DirectedWeightedGraph;
import api.EdgeData;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EdgeTest {

    static AlgoDWG a = new AlgoDWG();

    boolean t= a.load("src/data/Tests.json");


    DirectedWeightedGraph g1 = a.getGraph();
    Node node1 =(Node) g1.getNode(1);
    Map<Point2D, EdgeData> edgesOut = node1.getEdgeMapOut();
    Point2D p1 =new Point(1,2);
    Point2D p2 =new Point(1,3);
    Point2D p3 =new Point(1,7);
    Point2D p4 =new Point(1,15);
    Edge e1= (Edge) edgesOut.get(p1);




    @Test
    void setW() {
        e1.setW(9);
        assertEquals(e1.getWeight(),9);

    }


    @Test
    void getSrc() {
        assertEquals(e1.getSrc(),1);
    }

    @Test
    void getDest() {
        assertEquals(e1.getDest(),2);

    }

    @Test
    void getWeight() {
        assertEquals(e1.getWeight(),5);
    }

}