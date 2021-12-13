package Tests;

import Departments.GeoPosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GeoPositionTest {
GeoPosition test = new GeoPosition(1,2,3);
    GeoPosition test2 = new GeoPosition(0,0,0);
    @Test
    void x() {
        assertEquals(1,test.x());
    }

    @Test
    void y() {
        assertEquals(2,test.y());
    }

    @Test
    void z() {
        assertEquals(3,test.z());
    }

    @Test
    void distance() {
        assertEquals(test.distance(test2),3.7416573867739413);
    }

    @Test
    void testToString() {
    }
}