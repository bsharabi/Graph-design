package Departments;

import api.EdgeData;

import java.awt.*;
import java.awt.geom.Point2D;

public class Edge implements EdgeData, Comparable<Point2D> {

    private int src;
    private double w;
    private int dest;
    private String info;
    private int tag;
    private Point2D p;

    // -------------------------- Constructor --------------------------------------

    public Edge() {
        this.p = new Point();
    }

    public Edge(int src, double weight, int dest) {
        this.src = src;
        this.w = weight;
        this.dest = dest;
        this.p = new Point(src, dest);
    }

    //--------------------------- Getter && Setter --------------------------------
    public void setSrc(int src) {
        this.src = src;
        p.setLocation(src, this.dest);
    }

    public void setW(double weight) {
        this.w = weight;
    }

    public void setDest(int dest) {
        this.dest = dest;
        p.setLocation(this.src, dest);
    }

    public Point2D getP() {
        return p != null ? p : null;
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
        if (this.p.equals(o))
            return 0;
        return -1;
    }

    @Override
    public String toString() {
        return "Edge{" + "src=" + src + ", w=" + w + ", dest=" + dest + "}\n";
    }
}
