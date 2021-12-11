package Departments;

import api.NodeData;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Tsp implements Comparator<Tsp> {
    private  List<NodeData> path;
    private double length;
    private int dest;


    public List<NodeData> getPath() {
        return path;
    }

    public double getLength() {
        return length;
    }

    public int getDest() {
        return dest;
    }

    public Tsp(List<NodeData> path, double length, int dest){
        this.path=path;
        this.length=length;
        this.dest=dest;
    }
    public Tsp(ResultsFormat resultsFormat, int dest){
        this.path=resultsFormat.path;
        this.length=resultsFormat.distance;
        this.dest=dest;
    }
    public Tsp(){
        this.path=new LinkedList<>();
        this.length=0;
        this.dest=0;
    }
    public int compare(Tsp route1, Tsp routh2)
    {
        if (route1.getLength() < routh2.length)
            return -1;
        if (route1.getLength() > routh2.length)
            return 1;
        return 0;
    }


}
