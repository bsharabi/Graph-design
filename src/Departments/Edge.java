package Departments;

import api.EdgeData;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Edge implements EdgeData, Comparable<Point2D> {

    private int src;
    private double w;
    private int dest;
    private String info;
    private int tag;
    private Line2D lineDraw;
    private Color edgeColor;
    private Color arrowColor;
    private Point2D p;

    // -------------------------- Constructor --------------------------------------

    public Edge(int src, double w, int dest) {
        this.src = src;
        this.w = w;
        this.dest = dest;
        edgeColor = Color.BLUE;
        lineDraw = null;
        arrowColor = Color.BLACK;
        this.p = new Point(src, dest);
    }

    //--------------------------- Getter && Setter --------------------------------

    public void setW(double weight) {
        this.w = weight;
    }

    public Point2D getP() {
        return p != null ? p : null;
    }

    /*
     * @return the edge color
     */
    public Color getEdgeColor() {
        return edgeColor;
    }

    /*
     * Sets the color of the edge
     * @param c Color
     */
    public void setEdgeColor(Color c) {
        edgeColor = c;
    }

    public Line2D getLineDraw() {
        return lineDraw;
    }

    public void setLineDraw(Line2D lineDraw) {
        this.lineDraw = lineDraw;
    }

    public Color getArrowColor() {
        return arrowColor;
    }

    public void setArrowColor(Color arrowColor) {
        this.arrowColor = arrowColor;
    }

    //-------------------------------- Override -------------------------------------
    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.w;
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
    public int compareTo(Point2D o) {
        if (this.p.getX() == o.getY() && this.p.getY() == o.getX())
            return 0;
        return -1;
    }

    @Override
    public String toString() {
        return "    {\n" +
                "      \"src\":" + src + ",\n" +
                "      \"w\":" + w + ",\n" +
                "      \"dest\":" + dest + "\n" +
                "    }";
    }
}
