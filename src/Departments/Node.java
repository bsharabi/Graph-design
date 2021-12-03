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
    private double w;
    private Map<Point2D, EdgeData> edgeMapOut;
    private Map<Point2D, EdgeData> edgeMapIn;


    // -------------------------- Constructor --------------------------------------
    public Node(String pos, int id) {
        String[] p = pos.split(",");
        double x = Double.parseDouble(p[0]);
        double y = Double.parseDouble(p[1]);
        double z = Double.parseDouble(p[2]);
        this.position3D = new GeoPosition(x, y, z);
        this.id = id;
        this.edgeMapIn = new HashMap<>();
        this.edgeMapOut = new HashMap<>();
    }

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
        try {
            if (edgeMapOut == null)
                throw new NullPointerException("There is no edge object or the object is not defined or empty.");
            return edgeMapOut.values().stream().toList();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<EdgeData> getEdgeListIn() {
        try {
            if (edgeMapIn == null)
                throw new NullPointerException("There is no edge object or the object is not defined or empty.");
            return edgeMapIn.values().stream().toList();
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        return null;
    }

    public Map<Point2D, EdgeData> getEdgeMapOut() {
        try {
            if (edgeMapOut == null )
                throw new NullPointerException("The Map does not exist or the EdgesMapOut is not found");
            return edgeMapOut;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Map<Point2D, EdgeData> getEdgeMapIn() {
        try {
            if (edgeMapIn == null)
                throw new NullPointerException("The Map does not exist or the EdgesMapIn is not found");
            return edgeMapIn;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    //-------------------------------- Override -------------------------------------
    @Override
    public int getKey() {
        return this.id;
    }

    @Override
    public GeoLocation getLocation() {
        return this.position3D;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.position3D = p;
    }

    @Override
    public double getWeight() {
        return this.w;
    }

    @Override
    public void setWeight(double w) {
        this.w = w;
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


    public String print() {
        return "Node{" + id + " edgeMapOut=" + edgeMapOut.values().stream().toList() + "edgeMapIn=" + edgeMapIn.values().stream().toList() + "}\n";
    }

    @Override
    public String toString() {
        return "{\n" +
                "      \"pos\": " + position3D + ",\n" +
                "      \"id\":" + id + "\n" +
                "    }";
    }
}
