package Departments;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import com.sun.jdi.request.ExceptionRequest;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// error msg access
// add listener to Map
public class Graph implements DirectedWeightedGraph {

    private Map<Point2D, EdgeData> edgesMap;
    private Map<Integer, NodeData> nodesMap;
    private int mCount;

    // -------------------------- Constructor --------------------------------------
    public Graph() {
        this.edgesMap = new HashMap<>();
        this.nodesMap = new HashMap<>();
        this.mCount = 0;
    }

    public Graph(Map<Integer, NodeData> nodeMap, Map<Point2D, EdgeData> edgeMap) {
        this.edgesMap = edgeMap;
        this.nodesMap = nodeMap;
        this.mCount = 0;
        //O(edge.size())
        edgesMap.forEach((k, v) -> {
            int src = (int) k.getX();
            int dest = (int) k.getY();
            try {
                Node node = (Node) nodesMap.get(src);
                node.getEdgeMapOut().put(k, v);
                node = (Node) nodesMap.get(dest);
                node.getEdgeMapIn().put(k, v);
            } catch (Exception e) {
                System.out.println("Src or Dest node");
            }

        });
    }

    //--------------------------- Getter && Setter --------------------------------
    public void setEdgesMap(Map<Point2D, EdgeData> edgesMap) {
        this.edgesMap = edgesMap;
    }


    public void setNodesMap(Map<Integer, NodeData> nodesMap) {
        this.nodesMap = nodesMap;
    }

    //-------------------------------- Function -------------------------------------
    public void init(String path) {


    }

    //-------------------------------- Override -------------------------------------
    //O(1)
    @Override
    public NodeData getNode(int key) {
        return this.nodesMap.get(key);
    }

    //O(1)
    @Override
    public EdgeData getEdge(int src, int dest) {
        Point2D p = new Point(src, dest);
        return edgesMap.get(p);
    }

    //O(1)
    @Override
    public void addNode(NodeData n) {
        if (n != null && !nodesMap.containsValue(n))
            nodesMap.put(n.getKey(), n);
    }

    //O(1)
    @Override
    public void connect(int src, int dest, double w) {
        try {
            if (nodesMap.containsKey(src) && nodesMap.containsKey(dest)) {
                Point2D p = new Point(src, dest);
                EdgeData edge = new Edge(src, w, dest);
                edgesMap.put(new Point(src, dest), edge);
                Node node = (Node) nodesMap.get(src);
                node.getEdgeMapOut().put(p, edge);
                node = (Node) nodesMap.get(dest);
                node.getEdgeMapIn().put(p, edge);
            } else
                throw new Exception("not");
        } catch (Exception e) {
            System.out.println("not");
        }


    }

    @Override
    public Iterator<NodeData> nodeIter() {
        if (!nodesMap.isEmpty())
            return nodesMap.values().iterator();
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        if (!edgesMap.isEmpty())
            return edgesMap.values().iterator();
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        Node n = (Node) nodesMap.get(node_id);
        return n.getEdgeListOut().iterator();
    }

    //O(k)
    @Override
    public NodeData removeNode(int key) {

        Node temp = (Node) nodesMap.remove(key);
        Map<Point2D, EdgeData> mapIn = temp.getEdgeMapIn();
        Map<Point2D, EdgeData> mapOut = temp.getEdgeMapOut();

        mapIn.forEach((k, v) -> {
            Point2D p = k;
            int src = (int) p.getX();
            edgesMap.remove(p);
            Node node = (Node) nodesMap.get(src);
            node.getEdgeMapOut().remove(p);
        });
        mapOut.forEach((k, v) -> {
            Point2D p = k;
            int dest = (int) p.getY();
            edgesMap.remove(p);
            Node node = (Node) nodesMap.get(dest);
            node.getEdgeMapIn().remove(p);
        });
        return temp;
    }

    //O(1)
    @Override
    public EdgeData removeEdge(int src, int dest) {
        Point2D p = new Point(src, dest);
        if (edgesMap.containsKey(p)) {
            EdgeData edge = edgesMap.remove(p);
            Node node = (Node) nodesMap.get(src);
            node.getEdgeMapOut().remove(p);
            node = (Node) nodesMap.get(dest);
            node.getEdgeMapIn().remove(p);
            return edge;
        }
        return null;
    }

    //O(1)
    @Override
    public int nodeSize() {
        if (!nodesMap.isEmpty())
            return nodesMap.size();
        return 0;
    }

    //O(1)
    @Override
    public int edgeSize() {
        if (!edgesMap.isEmpty())
            return edgesMap.values().size();
        return 0;
    }

    @Override
    public int getMC() {
        return this.mCount;
    }

    @Override
    public String toString() {
        return "Graph{" + "edgesMap=" + edgesMap.values().stream().toList() + ", nodesMap=" + nodesMap.values().stream().toList() + "}\n";
    }
}
