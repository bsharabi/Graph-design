package Departments;

import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Node implements Comparator<Node>, NodeData {

    private GeoLocation position3D;
    private int id;
    private String info;
    private int tag;
    private double w;
    private Shape visualVertex;
    private Color vertexState;
    private Point2D pointDraw;
    private Map<Point2D, EdgeData> edgeMapOut;
    private Map<Point2D, EdgeData> edgeMapIn;

    // -------------------------- Constructor --------------------------------------

    public Node(String pos, int id) {
        super();
        String[] p = pos.split(",");
        double x = Double.parseDouble(p[0]);
        double y = Double.parseDouble(p[1]);
        double z = Double.parseDouble(p[2]);
        this.position3D = new GeoPosition(x, y, z);
        this.vertexState = Color.red;
        this.visualVertex = new Ellipse2D.Double(x - 9, y - 9, 20, 20);
        pointDraw = new Point((int) x, (int) y);
        this.id = id;
        this.edgeMapIn = new HashMap<>();
        this.edgeMapOut = new HashMap<>();
    }

    public Node(GeoLocation pos, int id) {
        super();
        this.position3D = pos;
        this.id = id;
        this.vertexState = Color.red;
        pointDraw = new Point((int) pos.x(), (int) pos.y());
        this.visualVertex = new Ellipse2D.Double(pos.x() - 9, pos.y() - 9, 20, 20);
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
            if (edgeMapOut == null)
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

    /*
     * @return the vertex representation on the canvas
     */
    public Shape getVisualVertex() {
        return visualVertex;
    }

    /*
     * @return the current vertex state
     */
    public Color getVertexState() {
        return vertexState;
    }

    /*
     * @param c sets the state of the vertex
     * Whether the user clicked on the vertex or not
     * for certain menu options
     */
    public void setVertexState(Color c) {
        vertexState = c;
    }

    /*
     * @param v the vertex representation in a canvas
     */
    public void setVisualVertex(Shape v) {
        visualVertex = v;
    }

    public Point2D getPointDraw() {
        return pointDraw;
    }

    public void setPointDraw(Point2D pointDraw) {
        this.pointDraw = pointDraw;
    }

    public void setId(int id) {
        this.id = id;
    }

    //-------------------------------- Function -------------------------------------
    public void initDrawColor() {
        setVertexState(Color.red);
        if(!edgeMapOut.isEmpty())
        edgeMapOut.forEach((K,V)->{
            ((Edge)V).setEdgeColor(Color.blue);
            ((Edge)V).setArrowColor(Color.black);
        });

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

    public int compare(Node node1, Node node2) {
        if (node1.getWeight() < node2.getWeight())
            return -1;
        if (node1.getWeight() > node2.getWeight())
            return 1;
        return 0;
    }
}
