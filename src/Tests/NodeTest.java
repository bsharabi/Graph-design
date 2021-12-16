package Tests;

import Departments.AlgoDWG;
import Departments.GeoPosition;
import Departments.Node;
import api.DirectedWeightedGraph;
import api.EdgeData;
import api.GeoLocation;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class NodeTest {



  static  AlgoDWG a = new AlgoDWG();

    boolean t= a.load("src/data/Tests.json");


    DirectedWeightedGraph g1 = a.getGraph();
    Node node1 =(Node) g1.getNode(1);
    Map<Point2D,EdgeData> edgesOut = node1.getEdgeMapOut();
    Point2D p1 =new Point(1,2);
    Point2D p2 =new Point(1,3);
    Point2D p3 =new Point(1,7);
    Point2D p4 =new Point(1,15);




//}


    @Test
    void getEdgeListOut( ) {
        for (EdgeData e:node1.getEdgeListOut())
    assertTrue(node1.getEdgeListOut().containsAll(edgesOut.values()));


    }

    @Test
    void getEdgeListIn() {
    }

    @Test
    void getEdgeMapOut() {

        assertNotEquals(edgesOut.get(p1),null);
        assertNotEquals(edgesOut.get(p2),null);
        assertNotEquals(edgesOut.get(p3),null);
        assertTrue(edgesOut.get(p4)==null);


    }

    @Test
    void getEdgeMapIn() {
    }

    @Test
    void getKey() {
        assertTrue(node1.getKey()==1);
    }

    @Test
    void getLocation() {
        GeoLocation i= new GeoPosition(35.20319591121872,32.10318254621849,0.0);
        assertEquals(node1.getLocation().x(),(i).x());
        assertEquals(node1.getLocation().y(),(i).y());
        assertEquals(node1.getLocation().z(),(i).z());



    }

    @Test
    void setLocation() {
        GeoLocation i= new GeoPosition(0,0,0);
        node1.setLocation(i);
        assertEquals(node1.getLocation().x(),(i).x());
        assertEquals(node1.getLocation().y(),(i).y());
        assertEquals(node1.getLocation().z(),(i).z());
    }

    @Test
    void getWeight() {
        assertNotEquals(node1.getWeight(),0);
        node1.setWeight(0);
        assertEquals(node1.getWeight(),0);
    }

    @Test
    void setWeight() {
        node1.setWeight(5);
        assertEquals(node1.getWeight(),5);
    }

    @Test
    void print() {
        assertEquals(node1.toString(),"{\n" +
                "      \"pos\": \"35.20319591121872,32.10318254621849,0.0\",\n" +
                "      \"id\":1\n" +
                "    }");

    }

    @Test
    void testToString() {
        System.out.println(g1.toString());
        assertEquals(g1.toString(),"\"Edges\": [    {\n" +
                "      \"src\":9,\n" +
                "      \"w\":1.0,\n" +
                "      \"dest\":8\n" +
                "    },     {\n" +
                "      \"src\":5,\n" +
                "      \"w\":1.0,\n" +
                "      \"dest\":4\n" +
                "    },     {\n" +
                "      \"src\":1,\n" +
                "      \"w\":5.0,\n" +
                "      \"dest\":3\n" +
                "    },     {\n" +
                "      \"src\":4,\n" +
                "      \"w\":1.0,\n" +
                "      \"dest\":3\n" +
                "    },     {\n" +
                "      \"src\":3,\n" +
                "      \"w\":1.0,\n" +
                "      \"dest\":8\n" +
                "    },     {\n" +
                "      \"src\":2,\n" +
                "      \"w\":1.0,\n" +
                "      \"dest\":6\n" +
                "    },     {\n" +
                "      \"src\":7,\n" +
                "      \"w\":1.0,\n" +
                "      \"dest\":1\n" +
                "    },     {\n" +
                "      \"src\":1,\n" +
                "      \"w\":5.0,\n" +
                "      \"dest\":2\n" +
                "    },     {\n" +
                "      \"src\":2,\n" +
                "      \"w\":1.0,\n" +
                "      \"dest\":1\n" +
                "    },     {\n" +
                "      \"src\":2,\n" +
                "      \"w\":1.0,\n" +
                "      \"dest\":4\n" +
                "    },     {\n" +
                "      \"src\":4,\n" +
                "      \"w\":1.0,\n" +
                "      \"dest\":2\n" +
                "    },     {\n" +
                "      \"src\":1,\n" +
                "      \"w\":1.0,\n" +
                "      \"dest\":7\n" +
                "    },     {\n" +
                "      \"src\":3,\n" +
                "      \"w\":1.0,\n" +
                "      \"dest\":1\n" +
                "    },     {\n" +
                "      \"src\":3,\n" +
                "      \"w\":1.0,\n" +
                "      \"dest\":4\n" +
                "    },     {\n" +
                "      \"src\":8,\n" +
                "      \"w\":1.0,\n" +
                "      \"dest\":3\n" +
                "    },     {\n" +
                "      \"src\":6,\n" +
                "      \"w\":1.0,\n" +
                "      \"dest\":2\n" +
                "    },     {\n" +
                "      \"src\":4,\n" +
                "      \"w\":1.0,\n" +
                "      \"dest\":5\n" +
                "    }], \n" +
                "\"Nodes\": [{\n" +
                "      \"pos\": \"35.20319591121872,32.10318254621849,0.0\",\n" +
                "      \"id\":1\n" +
                "    }, {\n" +
                "      \"pos\": \"35.20752617756255,32.1025646605042,0.0\",\n" +
                "      \"id\":2\n" +
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
                "      \"pos\": \"3.21211116545601,7.10623562857143,0.0\",\n" +
                "      \"id\":6\n" +
                "    }, {\n" +
                "      \"pos\": \"51.21211116545602,22.10623562857143,0.0\",\n" +
                "      \"id\":7\n" +
                "    }, {\n" +
                "      \"pos\": \"75.21211116545601,12.10623562857143,0.0\",\n" +
                "      \"id\":8\n" +
                "    }, {\n" +
                "      \"pos\": \"25.21211116545602,37.10623562857143,0.0\",\n" +
                "      \"id\":9\n" +
                "    }]");
    }
}