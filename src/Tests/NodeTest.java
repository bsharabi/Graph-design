package Tests;

import Departments.GeoPosition;
import Departments.Node;
import api.GeoLocation;
import api.NodeData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    NodeData node = new Node(new GeoPosition(1, 2, 3), 0);

    @Test
    void getEdgeListOut() {

    }

    @Test
    void getEdgeListIn() {
    }

    @Test
    void getEdgeMapOut() {
    }

    @Test
    void getEdgeMapIn() {
    }

    @Test
    void getKey() {
        assertEquals(node.getKey(),0);
    }

    @Test
    void getLocation() {
        assertEquals(node.getLocation().x(),1);
        assertEquals(node.getLocation().y(),2);
        assertEquals(node.getLocation().z(),3);
    }

    @Test
    void setLocation() {
    }

    @Test
    void getWeight() {
    }

    @Test
    void setWeight() {
    }

    @Test
    void getInfo() {
    }

    @Test
    void setInfo() {
    }

    @Test
    void getTag() {
    }

    @Test
    void setTag() {
    }

    @Test
    void print() {
    }

    @Test
    void testToString() {
    }
}