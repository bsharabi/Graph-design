package Departments;

import api.NodeData;

import java.util.LinkedList;
import java.util.List;

public class ResultsFormat {
    public double distance;
    public List<NodeData> path;

    public boolean isStrongConnected() {
        return strongConnected;
    }

    public void setStrongConnected(boolean strongConnected) {
        this.strongConnected = strongConnected;
    }

    public boolean strongConnected;

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setPath(List<NodeData> path) {
        this.path = path;
    }

    public double getDistance() {
        return distance;
    }

    public List<NodeData> getPath() {
        return path;
    }

    public ResultsFormat(double distance, LinkedList path) {
        this.distance = distance;
        this.path = path;
    }

}
