package Departments;

import api.GeoLocation;

import java.awt.*;
import java.awt.geom.Point2D;
import java.text.MessageFormat;

public class GeoPosition implements GeoLocation {
    private double x;
    private double y;
    private double z;
    private Point2D p;

    public GeoPosition(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.p = new Point((int) x, (int) y);
    }

    public Point2D getP() {
        return p;
    }

    public void setP(Point2D p) {
        this.x=p.getX();
        this.y=p.getY();
        this.p = p;
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(GeoLocation g) {
        double a = Math.abs(g.x() - x);
        double b = Math.abs(g.y() - y);
        double c = Math.abs(g.z() - z);
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(c, 2));
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}{1,number,#.##############},{2,number,#.##############},{3}.0{4}", '"', x, y, z, '"');
    }
}
