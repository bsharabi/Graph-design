package Departments;

import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Node implements NodeData {

    private GeoLocation position3D;
    private int id;
    private String info;
    private int tag;
    private Map<Point2D, EdgeData> edgeMapOut;
    private Map<Point2D, EdgeData> edgeMapIn;


    // -------------------------- Constructor --------------------------------------
    public Node(GeoLocation pos, int id) {
        this.position3D = pos;
        this.id = id;
        this.edgeMapIn = new HashMap<>();
        this.edgeMapOut = new HashMap<>();
    }

    public Node() {
        this.position3D = null;
        this.edgeMapIn = new HashMap<>();
        this.edgeMapOut = new HashMap<>();
    }

    //--------------------------- Getter && Setter --------------------------------
    public List<EdgeData> getEdgeListOut() {
        if (!edgeMapOut.isEmpty())
            return edgeMapOut.values().stream().toList();
        return null;
    }

    public List<EdgeData> getEdgeListIn() {
        if (!edgeMapIn.isEmpty())
            return edgeMapIn.values().stream().toList();
        return null;
    }

    public void setEdgeMapOut(Map<Point2D, EdgeData> edgeMapOut) {
        this.edgeMapOut = edgeMapOut;
    }

    public void setEdgeMapIn(Map<Point2D, EdgeData> edgeMapIn) {
        this.edgeMapIn = edgeMapIn;
    }

    public Map<Point2D, EdgeData> getEdgeMapOut() {
        return edgeMapOut;
    }

    public Map<Point2D, EdgeData> getEdgeMapIn() {
        return edgeMapIn;
    }

    //-------------------------------- Override -------------------------------------
    @Override
    public int getKey() {
        return this.id;
    }

    @Override
    public GeoLocation getLocation() {
        if (this.position3D == null)
            return null;
        else return this.position3D;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.position3D = p;

    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public void setWeight(double w) {

    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    @Override
    public String toString() {
        return "Node{"+id + " edgeMapOut=" + edgeMapOut.values().stream().toList() + "edgeMapIn=" + edgeMapIn.values().stream().toList() + "}\n";
    }
}
