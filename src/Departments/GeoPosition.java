package Departments;

import api.GeoLocation;

public class GeoPosition implements GeoLocation {
    private double x;
    private double y;
    private double z;

    public GeoPosition(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
        return x + ", " + y + ", " + z;
    }
}
